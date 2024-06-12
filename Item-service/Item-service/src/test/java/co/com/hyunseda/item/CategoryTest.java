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

@SpringBootTest
class CategoryTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        Long categoryId = 1L;
        String categoryName = "TestCategory";
        List<Product> productList = new ArrayList<>();

        // Act
        Category category = new Category(categoryId, categoryName, productList);

        // Assert
        assertNotNull(category);
        assertEquals(categoryId, category.getCategoryId());
        assertEquals(categoryName, category.getCategoryName());
        assertEquals(productList, category.getProductList());
    }

    @Test
    public void testDefaultConstructor() {
        // Act
        Category category = new Category();

        // Assert
        assertNotNull(category);
        assertNotNull(category.getProductList());
    }
}
