package net.javaguides.springboot.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="warehouse_product")
public class WarehouseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    //    @Column(name = "user_id")
    //    private int userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
