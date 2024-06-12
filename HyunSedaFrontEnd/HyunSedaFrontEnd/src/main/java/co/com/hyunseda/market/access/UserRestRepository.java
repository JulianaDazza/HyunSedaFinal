package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Pay;
import co.com.hyunseda.market.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ACER
 */
public class UserRestRepository implements  IUserRepository{
    String AuthUrl = "http://localhost:8005/auth";
    String UserUrl = "http://localhost:8005/user";
    public UserRestRepository() {
    }
    

    @Override
    public boolean sign_up() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean log_in(User user) {
        String logUrl = AuthUrl.concat("/log-in");
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //Convertir el usuario a JSON
            String jsonUser = mapper.writeValueAsString(user);
            
            //Crear solicitud post para iniciar sesión
            HttpPost postRequest = new HttpPost(logUrl);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(jsonUser));
            
            
            //Ejecutar solicitud y obtener respuesta
            HttpResponse response = httpClient.execute(postRequest);
            
            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode == HttpStatus.SC_OK) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return true;
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "No pudo iniciar sesión. Código de estado: " + statusCode);
                return false;
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
            return false;
        }
    }

    @Override
    public User getUserByUserName(String prmName) {
        String getUserUrl = UserUrl.concat("/").concat(prmName);
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //Hacer petición get
            HttpGet getUserRequest = new HttpGet(getUserUrl);
            
            //Ver la respuesta
            HttpResponse userResponse = httpClient.execute(getUserRequest);
            
             // Verificar el código de estado de la respuesta
            int statusCode = userResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(userResponse.getEntity());
                
                // Mapear la respuesta JSON a un objeto User
                User userMapped = mapper.readValue(jsonResponse, User.class);
                
                return userMapped;
            }else
            {
                Logger.getLogger(UserRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener el usuario. Código de estado: " + statusCode);
            }
             
        } catch (IOException ex) {
            Logger.getLogger(UserRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
       return null;
    }
    
}
