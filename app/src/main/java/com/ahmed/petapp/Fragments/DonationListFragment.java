package com.ahmed.petapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ahmed.petapp.Database.AppDatabase;
import com.ahmed.petapp.Module.Donation;
import com.ahmed.petapp.Module.Donation;
import com.example.petapp.R;
import java.util.List;
public class DonationListFragment extends Fragment {

    private AppDatabase appDatabase;

    public void DonationListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_donation_list, container, false);

        // Initialize the database
        appDatabase = AppDatabase.getAppDatabase(getContext());

        // Load donations from the database and add them to the UI
        loadAndDisplayDonations(rootView);

        return rootView;
    }

    private void loadAndDisplayDonations(View rootView) {
        LinearLayout donationContainer = rootView.findViewById(R.id.donation_container);

        // Retrieve donations from the database
        List<Donation> donations = appDatabase.donationDao().getAllDonations();

        // Loop through donations and add them to the UI
        for (Donation donation : donations) {
            addDonationItem(donationContainer, donation);
        }
    }

    private void addDonationItem(LinearLayout donationContainer, Donation donation) {
        View donationItemView = LayoutInflater.from(getContext()).inflate(R.layout.donation_item, donationContainer, false);

        // Find views in the donation item layout
        TextView donorNameTextView = donationItemView.findViewById(R.id.donorNameTextView);
        TextView donationTypeTextView = donationItemView.findViewById(R.id.donationTypeTextView);
        TextView donationAmountTextView = donationItemView.findViewById(R.id.donationAmountTextView);
        TextView paymentMethodTextView = donationItemView.findViewById(R.id.paymentMethodTextView);
        TextView donationDateTextView = donationItemView.findViewById(R.id.donationDateTextView);

        // Set values for the donation item
        donorNameTextView.setText("Donor Name: " + donation.getDonorName());
        donationTypeTextView.setText("Donation Type: " + donation.getDonationType());
        donationAmountTextView.setText("Donation Amount: $" + donation.getDonationAmount());
        paymentMethodTextView.setText("Payment Method: " + donation.getPaymentMethod());
        donationDateTextView.setText("Donation Date: " + donation.getDonationDate());

        // Add the donation item to the container
        donationContainer.addView(donationItemView);
    }

}
