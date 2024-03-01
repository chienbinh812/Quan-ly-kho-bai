package net.javaguides.springboot.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
//@Column(name = "user_id")
//private int userId;

}
