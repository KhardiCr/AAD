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
public class Product implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private Integer stock;
    private Double price;
    //Lógica del objeto
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", title='" + title + "', description='" + description + "', stock=" + stock + ", price=" + price + '}';
    }
}
