package com.ahmed.petapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahmed.petapp.DAO.PostDAO;
import com.ahmed.petapp.Module.Post;

@Database(entities = {Post.class}
        , version = 1
        , exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;



    public abstract PostDAO postDao();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "room_db")

                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
