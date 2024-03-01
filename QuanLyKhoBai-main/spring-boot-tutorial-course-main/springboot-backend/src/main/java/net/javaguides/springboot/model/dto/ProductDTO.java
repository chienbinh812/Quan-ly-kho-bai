package net.javaguides.springboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private double price;
    private String dateOfManufacture;
}
