package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;
    private Integer productId;
    private String description;
    private Double totalPrice;

    //Lógica del objeto


    public Order(Integer productId, String description, Double totalPrice) {
        this.productId = productId;
        this.description = description;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", product_id='" + productId + "', description='" + description + "', totalPrice=" + totalPrice + '}';
    }

}
