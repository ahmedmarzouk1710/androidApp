package com.ahmed.petapp.databaseApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ahmed.petapp.DAO.ProductDAO;
import com.ahmed.petapp.Module.Converters;
import com.ahmed.petapp.Module.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase instance;

    public abstract ProductDAO productDAO();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "room_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}




