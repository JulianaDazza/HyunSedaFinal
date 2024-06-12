/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.access.ProductRestRepository;
import co.com.hyunseda.market.domain.Product;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class ProductRestRepositoryTest {

    @Test
    public void testFindPorName() {
        // Crear una instancia de ProductRestRepository
        IProductRepository repository = new ProductRestRepository();

        // Llamar al método findPorName con un nombre de producto existente
        Product product = repository.findPorName("Chal hueso");

        // Verificar si el producto retornado no es nulo
        assertNotNull(product);
    }

    @Test
    public void testFindById() {
        // Crear una instancia de ProductRestRepository
        IProductRepository repository = new ProductRestRepository();

        // Llamar al método findById con un ID de producto conocido
        int productId = 1; // ID de producto conocido
        Product expectedProduct = new Product(1,"Aretes hilo negro","Aretes con hilos y cortezas de capullos de seda 100% natural",35000,1,"https://storagemultimediapro.blob.core.windows.net/9ff1d81a-f5b9-4fe3-8268-8b9b2b601b00/IMG_20240223_121702-8d263f7f-fa97-445f-ad96-d57d02ca0e98.jpg");

        Product actualProduct = repository.findById(productId);

        // Verificar si el producto retornado no es nulo
        assertNotNull(actualProduct);

        // Verificar si los valores del producto retornado son correctos
        assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
        assertEquals(expectedProduct.getProductName(), actualProduct.getProductName());
        assertEquals(expectedProduct.getProductDescription(), actualProduct.getProductDescription());
        assertEquals(expectedProduct.getProductPrice(), actualProduct.getProductPrice(), 0.001); // Usar delta para comparar valores dobles
        assertEquals(expectedProduct.getProductStock(), actualProduct.getProductStock(), 0.001); // Usar delta para comparar valores dobles
        assertEquals(expectedProduct.getImage(), actualProduct.getImage());
    }

    @Test
    public void testList() {
        // Crear una instancia de ProductRestRepository
        IProductRepository repository = new ProductRestRepository();

        // Llamar al método list para obtener la lista de productos
        List<Product> productList = repository.list();

        // Verificar si la lista de productos no está vacía
        assertFalse(productList.isEmpty());

        // Verificar los valores de al menos un producto en la lista
        Product expectedProduct = new Product(2,"Chal hueso","Chal tejido a mano de color hueso con lana 100% natural",220000,1,"https://storagemultimediapro.blob.core.windows.net/9ff1d81a-f5b9-4fe3-8268-8b9b2b601b00/product-12-image-1-c38c8c03-8541-406c-8188-5bc4d32d2868.jpg");
        boolean productFound = false;

        for (Product product : productList) {
            if (product.getProductId() == expectedProduct.getProductId()) {
                assertEquals(expectedProduct.getProductName(), product.getProductName());
                assertEquals(expectedProduct.getProductDescription(), product.getProductDescription());
                assertEquals(expectedProduct.getProductPrice(), product.getProductPrice(), 0.001); // Usar delta para comparar valores dobles
                assertEquals(expectedProduct.getProductStock(), product.getProductStock(), 0.001); // Usar delta para comparar valores dobles
                assertEquals(expectedProduct.getImage(), product.getImage());
                productFound = true;
                break;
            }
        }

        assertTrue("Producto no encontrado en la lista", productFound);
    }

    @Test
    public void testFindByName() {
        // Crear una instancia de ProductRestRepository
        IProductRepository repository = new ProductRestRepository();

        // Llamar al método findByName con un nombre de producto existente
        List<Product> productList = repository.findByName("Ruana dorada");

        // Verificar si la lista de productos no está vacía
        assertFalse(productList.isEmpty());
    }
}