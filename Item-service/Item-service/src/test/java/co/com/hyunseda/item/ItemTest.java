package co.com.hyunseda.item;

import co.com.hyunseda.item.domain.entity.Item;
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

public class ItemTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Long itemId = 1L;
        Product product = new Product();
        Integer amount = 10;

        // Act
        Item item = new Item(itemId, product, amount);

        // Assert
        assertNotNull(item);
        assertEquals(itemId, item.getItemId());
        assertEquals(product, item.getProduct());
        assertEquals(amount, item.getAmount());
    }
}
