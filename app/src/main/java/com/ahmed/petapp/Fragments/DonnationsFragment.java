package com.ahmed.petapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ahmed.petapp.Database.AppDatabase;
import com.ahmed.petapp.Module.Donation;
import com.example.petapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DonnationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DonnationsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RadioGroup rgDonationAmount;
    private EditText etDonationCustom;
    private Button btnDonate;
    private ProgressBar pbDonationProgress;
    private ImageView ivDonationImpact;

    private int selectedDonationAmount = 10;

    public DonnationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DonnationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DonnationsFragment newInstance(String param1, String param2) {
        DonnationsFragment fragment = new DonnationsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donnations, container, false);
        rgDonationAmount = view.findViewById(R.id.rg_donation_amount);
        etDonationCustom = view.findViewById(R.id.et_donation_custom);
        btnDonate = view.findViewById(R.id.btn_donate);
        pbDonationProgress = view.findViewById(R.id.pb_donation_progress);
        ivDonationImpact = view.findViewById(R.id.iv_donation_impact);

        // Set default radio button selection
        rgDonationAmount.check(R.id.rb_donation_10);

        // Set up event listeners
        rgDonationAmount.setOnCheckedChangeListener(donationAmountChangeListener);
        etDonationCustom.addTextChangedListener(donationAmountTextWatcher);
        btnDonate.setOnClickListener(donationButtonClickListener);

        return view;
    }

    // RadioGroup listener to handle donation amount selection
    private RadioGroup.OnCheckedChangeListener donationAmountChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_donation_10) {
                selectedDonationAmount = 10;
            } else if (checkedId == R.id.rb_donation_25) {
                selectedDonationAmount = 25;
            } else if (checkedId == R.id.rb_donation_50) {
                selectedDonationAmount = 50;
            } else if (checkedId == R.id.rb_donation_100) {
                selectedDonationAmount = 100;
            }
        }
    };

    private TextWatcher donationAmountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Check if input is a valid decimal number
            if (!s.toString().isEmpty()) {
                try {
                    double customAmount = Double.parseDouble(s.toString());
                    if (customAmount < 1) {
                        etDonationCustom.setError("Invalid donation amount");
                    } else {
                        selectedDonationAmount = (int) customAmount;
                    }
                } catch (NumberFormatException e) {
                    etDonationCustom.setError("Invalid donation amount");
                }
            } else {
                selectedDonationAmount = 0; // Reset to default amount if input is empty
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    // Button listener to handle donation processing
    private View.OnClickListener donationButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Simulate donation processing
            pbDonationProgress.setVisibility(View.VISIBLE);
            ivDonationImpact.setVisibility(View.VISIBLE);

            // Create a new Donation object
            Donation donation = new Donation("John Doe", "Money", String.valueOf(selectedDonationAmount), "Credit Card", "2023-11-14");

            // Insert the donation into the database
            AppDatabase.getAppDatabase(getContext()).donationDao().insertDonation(donation);

            // Update UI after simulated donation processing
            Toast.makeText(getContext(), "Donation successful: $" + selectedDonationAmount, Toast.LENGTH_SHORT).show();
            pbDonationProgress.setVisibility(View.GONE);
            ivDonationImpact.setVisibility(View.GONE);
        }
    };
}
