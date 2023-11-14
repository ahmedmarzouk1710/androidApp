package com.ahmed.petapp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity
@TypeConverters(Converters.class)
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public double price;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public Category category;


    public Product() {
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public Product(String title, double price, String description, Category category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
