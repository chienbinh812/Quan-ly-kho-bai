package net.javaguides.springboot.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class  Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "date_of_manufacture")
    private String dateOfManufacture;

    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<WarehouseProduct> warehouseProduct;
}


