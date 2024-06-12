package co.com.hyunseda.item;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.hyunseda.item.domain.entity.Item;
import co.com.hyunseda.item.domain.entity.Product;
import co.com.hyunseda.item.domain.service.IItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IItemService itemService;

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        // Define el comportamiento esperado para el servicio
        when(itemService.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        Long itemId = 1L;
        Integer amount = 1;
        Product product = new Product(1L, "Product1", "Description1", 10.0, 100.0);
        Item item = new Item(itemId, product, amount);
        when(itemService.findById(itemId, amount)).thenReturn(item);

        // Act & Assert
        mockMvc.perform(get("/items/{id}/amount/{amount}", itemId, amount)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.productId").value(product.getProductId()))
                .andExpect(jsonPath("$.product.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.product.productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$.product.productPrice").value(product.getProductPrice()))
                .andExpect(jsonPath("$.product.productStock").value(product.getProductStock()))
                .andExpect(jsonPath("$.amount").value(amount));
    }

    @Test
    public void testCreate() throws Exception {
        // Arrange
        Product product = new Product(1L, "Product1", "Description1", 10.0, 100.0);
        String requestBody = "{\"productId\": 1, \"amount\": 1}"; // Example JSON request body
        when(itemService.create(any(Product.class))).thenReturn(product);

        // Act & Assert
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(product.getProductId()))
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$.productPrice").value(product.getProductPrice()))
                .andExpect(jsonPath("$.productStock").value(product.getProductStock()));
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        Long itemId = 1L;
        Product product = new Product(1L, "Product1", "Description1", 10.0, 100.0);
        String requestBody = "{\"productId\": 1, \"amount\": 1}"; // Example JSON request body
        when(itemService.update(any(Product.class), eq(itemId))).thenReturn(product);

        // Act & Assert
        mockMvc.perform(put("/items/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(product.getProductId()))
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.productDescription").value(product.getProductDescription()))
                .andExpect(jsonPath("$.productPrice").value(product.getProductPrice()))
                .andExpect(jsonPath("$.productStock").value(product.getProductStock()));
    }

    @Test
    public void testDelete() throws Exception {
        // Arrange
        Long itemId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/items/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
