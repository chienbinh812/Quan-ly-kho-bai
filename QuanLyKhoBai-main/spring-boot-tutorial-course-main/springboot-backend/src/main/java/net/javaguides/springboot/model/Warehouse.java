package net.javaguides.springboot.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<UserWarehouse> userWarehouse;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Set<WarehouseProduct> warehouseProduct;
}


