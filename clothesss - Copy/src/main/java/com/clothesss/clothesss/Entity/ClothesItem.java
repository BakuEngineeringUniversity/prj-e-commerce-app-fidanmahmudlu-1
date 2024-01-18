package com.clothesss.clothesss.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClothesItem {

    @Id
    private String id; // Use String for ID

    private Date createDate = new Date();
    private String clothess;
    private String category;
    private String name;
    private double cost;
    private int star;
    private int time;
    private String imageUrl;
}

