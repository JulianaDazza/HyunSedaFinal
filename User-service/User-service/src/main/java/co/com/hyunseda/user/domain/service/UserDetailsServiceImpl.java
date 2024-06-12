package co.com.hyunseda.user.domain.service;

import co.com.hyunseda.user.access.RoleRepository;
import co.com.hyunseda.user.access.UserRepository;
import co.com.hyunseda.user.controller.dto.AuthCreateUserRequest;
import co.com.hyunseda.user.controller.dto.AuthLoginRequest;
import co.com.hyunseda.user.controller.dto.AuthResponse;
import co.com.hyunseda.user.domain.entity.RoleEntity;
import co.com.hyunseda.user.domain.entity.UserEntity;
import co.com.hyunseda.user.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserEntity userEntity = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+ username+ " no existe"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles()//Convertir los roles del usuario, para que spring security entienda
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permissions -> authorities.add(new SimpleGrantedAuthority(permissions.getName())));

        return new User(userEntity.getUserName()//Retornar al usuario, en lenguaje de spring security
        , userEntity.getPassword(), userEntity.isEnabled()
        ,userEntity.isAccountNotExpired()
        ,userEntity.isCredentialNoExpired()
        ,userEntity.isAccountNotLocked(),authorities);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest)
    {
        String userName = authLoginRequest.userName();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(userName,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateAccessToken(authentication);

        AuthResponse authResponse = new AuthResponse(userName, "User Logger is correct", accessToken,true);

        return authResponse;
    }

    public AuthResponse createUser(AuthCreateUserRequest createUserRequest)
    {
        String email = createUserRequest.email();
        String userName = createUserRequest.userName();
        String password = createUserRequest.password();

        List<String> roleRequest = createUserRequest.roleRequest().roleListName();
        //Validar los roles en BD
        Set<RoleEntity> roleEntitySet =  roleRepository.findRoleEntitiesByNameIn(roleRequest).stream()
                .collect(Collectors.toSet());
        if(roleEntitySet == null)
        {
            throw new IllegalArgumentException("Roles specified doesn't exist");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNotLocked(true)
                .accountNotExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles()
                .forEach(role ->
                        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));

        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUserName(),
                userCreated.getPassword(),authorityList);

        String accessToken = jwtUtils.generateAccessToken(authentication);

        AuthResponse authResponse = new AuthResponse(userCreated.getUserName(),
                "Usuario creado correctamente", accessToken, true);

        return authResponse;
    }

    public  Authentication authenticate(String userName, String password)
    {
        UserDetails userDetails = this.loadUserByUsername(userName);

        if (userDetails == null)
        {
            throw new BadCredentialsException("Invalid userName or password");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userName,userDetails.getPassword(),userDetails.getAuthorities());
    }
}
