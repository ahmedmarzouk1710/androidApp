package com.ahmed.petapp.Module;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Category fromString(String value) {
        return value == null ? null : Category.valueOf(value);
    }

    @TypeConverter
    public static String categoryToString(Category category) {
        return category == null ? null : category.name();
    }
}

