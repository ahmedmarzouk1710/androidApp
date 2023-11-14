package com.ahmed.petapp.Module;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "donations")
public class Donation {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String donorName;
    private String donationType;
    private String donationAmount;
    private String paymentMethod;
    private String donationDate;

    public Donation() {
    }

    public Donation(String donorName, String donationType, String donationAmount, String paymentMethod, String donationDate) {
        this.donorName = donorName;
        this.donationType = donationType;
        this.donationAmount = donationAmount;
        this.paymentMethod = paymentMethod;
        this.donationDate = donationDate;
    }

    public Donation(String donorName, String donationType, String donationAmount) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

}