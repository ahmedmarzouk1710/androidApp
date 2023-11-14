package com.ahmed.petapp;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.petapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MarketplaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketplaceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MarketplaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MarketplaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MarketplaceFragment newInstance(String param1, String param2) {
        MarketplaceFragment fragment = new MarketplaceFragment();
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

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marketplace, container, false);
    }*/
    // Inside onCreateView() of MarketplaceFragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marketplace, container, false);

        CardView feedingCardView = view.findViewById(R.id.feedingCard);
        CardView beddingCardView = view.findViewById(R.id.beddingCard);
        CardView accessoriesCardView = view.findViewById(R.id.accessoriesCard);
        CardView hygieneCardView = view.findViewById(R.id.hygieneCard);
        ImageView addImageView = view.findViewById(R.id.add);


        feedingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedingFragment feedingFragment = new FeedingFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, feedingFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        beddingCardView.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                BeddingFragment beddingFragment = new BeddingFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, beddingFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        accessoriesCardView.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                AccessoriesFragment accessoriesFragment = new AccessoriesFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, accessoriesFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        hygieneCardView.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                HygieneFragment hygieneFragment = new HygieneFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, hygieneFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        addImageView.setOnClickListener(new View.OnClickListener() { // Add this block
            @Override
            public void onClick(View v) {
                AddProductFragment addProductFragment = new AddProductFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, addProductFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }



}