package com.ahmed.petapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ahmed.petapp.Module.Donation;

import java.util.List;

@Dao
public interface DonationDAO {
    @Insert
    void insertDonation(Donation donation);

    @Query("SELECT * FROM donations")
    List<Donation> getAllDonations();

    @Query("SELECT * FROM donations WHERE id = :donationId")
    Donation getDonationById(long donationId);


    @Update
    void updateDonation(Donation donation);

    @Query("DELETE FROM donations WHERE id = :donationId")
    void deleteDonationById(Long donationId);

}