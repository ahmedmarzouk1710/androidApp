package com.ahmed.petapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ahmed.petapp.database.AppDatabase;
import com.ahmed.petapp.entities.Category;
import com.ahmed.petapp.entities.Product;
import com.example.petapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button save, clear;
    EditText title, price, description;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    private AppDatabase database ;
    private List<Product> productList = new ArrayList<Product>();

    public AddProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        price = view.findViewById(R.id.price); // Add this line

        save = view.findViewById(R.id.save);
        clear = view.findViewById(R.id.clear);

        //Intent intent = new Intent(getActivity(), MarketplaceFragment.class);

        save.setOnClickListener(e -> {
            AppDatabase.getInstance(getActivity()).productDAO().addProduct(
                    new Product(title.getText().toString(), Double.parseDouble(price.getText().toString()), description.getText().toString())
            );

            //getActivity().finish();
        });


        clear.setOnClickListener(e -> {
            title.setText("");
            price.setText("");
            description.setText("");
        });

        return view;
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        price = view.findViewById(R.id.price);

        Spinner categorySpinner = view.findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireActivity(),
                R.array.categories_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        save = view.findViewById(R.id.save);
        clear = view.findViewById(R.id.clear);

        save.setOnClickListener(e -> {
            String titleStr = title.getText().toString();
            String priceStr = price.getText().toString();
            String descriptionStr = description.getText().toString();

            if (!titleStr.isEmpty() && !priceStr.isEmpty() && !descriptionStr.isEmpty()) {
                Category selectedCategory = Category.values()[categorySpinner.getSelectedItemPosition()];

                // Ne pas enregistrer l'URI de l'image
                // if (selectedImageUri != null) {
                //     String photoUrl = selectedImageUri.toString();
                // } else {
                //     Toast.makeText(requireActivity(), "Please select a photo", Toast.LENGTH_SHORT).show();
                //     return;
                // }

                // Enregistrer le produit sans l'URI de l'image
                AppDatabase.getInstance(requireActivity()).productDAO().addProduct(
                        new Product(titleStr, Double.parseDouble(priceStr), descriptionStr, selectedCategory)
                );

                title.setText("");
                price.setText("");
                description.setText("");
            } else {
                Toast.makeText(requireActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });

        clear.setOnClickListener(e -> {
            title.setText("");
            price.setText("");
            description.setText("");
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Handle the selected image URI here
            selectedImageUri = data.getData();
        }
    }







    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }*/
}