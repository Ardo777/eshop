package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {
    int id;
    String name;
    String description;
    double price;
    int count;
    Category category;


}
//    public static void main(String[] args) {
//        Product.builder()
//                .name("Ardo")
//                .count(7)
//                .build();
//    }