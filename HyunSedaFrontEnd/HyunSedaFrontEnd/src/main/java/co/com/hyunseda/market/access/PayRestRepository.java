/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Pay;
import co.com.hyunseda.market.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ACER
 */
public class PayRestRepository implements IPayRepository{
    String apiUrl = "http://localhost:8004/pay";
    public PayRestRepository() {
    }

    @Override
    public boolean save(Pay newPay) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //List<Category> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de categorias
            String apiUrl = "http://localhost:8004/pay";
            
            // Convertir el objeto Category a JSON
            String jsonProduct = mapper.writeValueAsString(newPay);  
            
            // Crear una solicitud POST para obtener todas las categorias
            HttpPost request = new HttpPost(apiUrl);
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(jsonProduct));
            
            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                return true;
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al guardar el pago. Código de estado: " + statusCode);
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
            return false;
        }
    }

    @Override
    public List<Pay> list() {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        List<Pay> list = new ArrayList<>();
        try {

            // Definir la URL de la API REST de productos
            //String apiUrl = "http://localhost:8004/pay";
            // Crear una solicitud GET para obtener todos los productos
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a objetos Product
                Pay[] objPay = mapper.readValue(jsonResponse, Pay[].class);

                for(Pay pay : objPay) {
                    list.add(pay);
                }

            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, "Error al obtener pagos. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Pay findById(int id) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //Product product = null;
        try {
            // Definir la URL de la API REST de productos con el ID como parámetro de consulta

            // Crear una solicitud GET para buscar un producto por su ID
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a un objeto Product
                Pay[]  objPay = mapper.readValue(jsonResponse, Pay[].class);
                
                for (Pay pay : objPay) {
                  // for(Category cate :categories){
                   if(pay.getPayId()==id )
                    return pay;
                }
               
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener el Pago. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
        return null;
    }

    @Override
    public Pay findByACcount(int account) {
        HttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();
        //Product product = null;
        try {
            // Definir la URL de la API REST de productos con el ID como parámetro de consulta

            // Crear una solicitud GET para buscar un producto por su ID
            HttpGet request = new HttpGet(apiUrl);

            // Ejecutar la solicitud y obtener la respuesta
            HttpResponse response = httpClient.execute(request);

            // Verificar el código de estado de la respuesta
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // La solicitud fue exitosa, procesar la respuesta
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Mapear la respuesta JSON a un objeto Pay
                Pay[]  objPay = mapper.readValue(jsonResponse, Pay[].class);
                
                for (Pay pay : objPay) {
                  // for(Category cate :categories){
                   if(pay.getNumAcount()== account )
                    return pay;
                }
               
            } else {
                // La solicitud falló, mostrar el código de estado
                Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al obtener el Pago. Código de estado: " + statusCode);
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductRestRepository.class.getName()).log(Level.SEVERE, "Error al realizar la solicitud HTTP", ex);
        }
        return null;
    }
    
}
