package co.com.hyunseda.item.domain.service;

import co.com.hyunseda.item.domain.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Se conecta al servicio web de producto y mapea el crud. Se utiliza Feign, que
 * es una forma declarativa de comunicar microservicios
 *
 * @author Libardo, Julio
 *
 */
@FeignClient(name = "Product-service", url = "localhost:8001") //El product-service sale del otro proyecto en applications properties y se pone el puerto donde esta el otro proyecto: 8001

public interface IProductClientRest {
    @GetMapping("/products")
    public List<Product> list();

    @GetMapping("/products/{id}")//{} para variable, sin estos seria la palabra id como tal
    public Product detail(@PathVariable Long id); //detail: detalle del producto

    @PostMapping("/products")
    public Product create(@RequestBody Product product);

    @PutMapping("/products/{id}")
    public Product update(@RequestBody Product producto, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public void delete(@PathVariable Long id);
}
