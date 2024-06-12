package co.com.hyunseda.item.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data @AllArgsConstructor
public class Category {
    private Long categoryId;/*<! Id de la categoría, agrua productos*/
    private String categoryName;/*<! Nombre de la categoría, Obligatorio*/
    private List<Product> productList;/*<! Listado de categorías*/
    public Category(){
        this.productList = new ArrayList<>();/*<! Inicializar el ArrayList*/
    }
}
