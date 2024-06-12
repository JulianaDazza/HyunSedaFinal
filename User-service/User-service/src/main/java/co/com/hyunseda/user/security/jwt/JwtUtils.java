package co.com.hyunseda.user.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.Jwt.key}")
    private String privateKey;
    @Value("${security.Jwt.user.generator}")
    private String userGenerator;

    /**@brief Crear token de acceso
     * @param authentication última autenticación
     * @return token codificado
     */
    public String generateAccessToken(Authentication authentication)
    {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String userName = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(userName)
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+3600000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);

        return jwtToken;
    }
    /**@brief valida que el token sea válido
     * @param token token ingresado
     * @Return token decodificado
     */
    public DecodedJWT validateToken(String token)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            JWTVerifier verifier = JWT.require(algorithm)//Verificador del token
                    .withIssuer(this.userGenerator).build();

            DecodedJWT decodedJWT = verifier.verify(token);

            return  decodedJWT;
        }catch (JWTVerificationException e)
        {
            throw new JWTVerificationException("Token invalid, not authorized");
        }
    }

    /**@Brief Extraer el nombre de usuario
     * @param decoded Token decodificado
     * @return username
     */
    public String extractUserName(DecodedJWT decoded)
    {
        return decoded.getSubject().toString();
    }

    /**@brief Retorna un atributo específico del token
     * @param decodedJWT token decodificado
     * @param claimName nombre del atributo (Claim)
     * @return Claim específico
     */
    public Claim getEspecificClaim(DecodedJWT decodedJWT, String claimName)
    {
        return decodedJWT.getClaim(claimName);
    }
    /**@brief Retorna todos los atributos del token
     * @param decodedJWT token decodificado
     * @return Mapa de claims
     */
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT)
    {
        return  decodedJWT.getClaims();
    }

}

