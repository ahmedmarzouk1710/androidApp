package com.ahmed.petapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ahmed.petapp.Module.Post;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert
    void insertOne(Post user);
    @Delete
    void delete(Post user);
    @Query("SELECT * FROM post")
    List<Post> getAll();
}
