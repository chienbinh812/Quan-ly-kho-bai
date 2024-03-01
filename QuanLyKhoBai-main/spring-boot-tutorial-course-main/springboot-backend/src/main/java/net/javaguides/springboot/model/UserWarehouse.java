package net.javaguides.springboot.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_warehouse")
public class UserWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    //    @Column(name = "user_id")
    //    private int userId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

}
