package net.javaguides.springboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseDTO {
    private int id;
    private String name;
    private String address;
}
