package com.ahmed.petapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ahmed.petapp.DAO.DonationDAO;
import com.ahmed.petapp.DAO.PostDAO;
import com.ahmed.petapp.Module.Donation;
import com.ahmed.petapp.Module.Post;

@Database(entities = {Post.class, Donation.class}
        , version = 2
        , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;



    public abstract PostDAO postDao();
    public abstract DonationDAO donationDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "room_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
