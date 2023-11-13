package com.ahmed.petapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ahmed.petapp.Module.Category;
import com.ahmed.petapp.Module.Product;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    void addProduct(Product u);

    @Query("SELECT * FROM Product WHERE category = :category")
    List<Product> getProductsByCategory(Category category);

    @Query("DELETE FROM Product WHERE id = :productId")
    void deleteById(int productId);

    @Query("SELECT * FROM Product WHERE id = :productId")
    Product findProductById(int productId);


}
