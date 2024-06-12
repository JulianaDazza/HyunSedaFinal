package co.com.hyunseda.item.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;/**<! Id del item, único e irrepetible*/

    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;/**<! Producto relacionado con Item*/

    private Integer amount;/**<! Cantidad de productos registrados*/


    public Item(Product p, Integer amount) {
        this.product = p;
        this.amount = amount;
    }
}
