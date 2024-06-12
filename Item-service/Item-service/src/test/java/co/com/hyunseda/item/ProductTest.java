package co.com.hyunseda.item;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import co.com.hyunseda.item.domain.entity.Category;
import co.com.hyunseda.item.domain.entity.Product;

public class ProductTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Long productId = 1L;
        String productName = "TestProduct";
        String productDescription = "Test Description";
        Double productPrice = 10.0;
        Double productStock = 100.0;

        // Act
        Product product = new Product(productId, productName, productDescription, productPrice, productStock);

        // Assert
        assertNotNull(product);
        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        assertEquals(productDescription, product.getProductDescription());
        assertEquals(productPrice, product.getProductPrice());
        assertEquals(productStock, product.getProductStock());
    }
}
