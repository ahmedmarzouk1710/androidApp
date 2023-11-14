package com.ahmed.petapp.entities;

import androidx.room.TypeConverter;

public enum Category {
    Feeding,
    Bedding,
    Accessories,
    Hygiene;

    @TypeConverter
    public static Category fromString(String value) {
        return value == null ? null : Category.valueOf(value);
    }

    @TypeConverter
    public static String categoryToString(Category category) {
        return category == null ? null : category.name();
    }
}
