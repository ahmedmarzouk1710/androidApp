package com.ahmed.petapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.petapp.DAO.ProductDAO;
import com.ahmed.petapp.database.AppDatabase;
import com.ahmed.petapp.entities.Category;
import com.ahmed.petapp.entities.Product;
import com.example.petapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeddingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeddingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BeddingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeddingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeddingFragment newInstance(String param1, String param2) {
        BeddingFragment fragment = new BeddingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Category category = Category.Bedding;
    private List<Product> productListBedding;
    private List<Product> productListFinding;
    private ProductDAO productDAO;
    private EditText searchEditText;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bedding, container, false);
        TextView textView = rootView.findViewById(R.id.textView);
        textView.setText(category.name());

        productDAO = AppDatabase.getInstance(requireContext()).productDAO();

        productListBedding = productDAO.getProductsByCategory(category);
        productListFinding = productDAO.getProductsByCategory(Category.Bedding);
        List<Product> combinedList = new ArrayList<>();
        combinedList.addAll(productListBedding);
        combinedList.addAll(productListFinding);
        searchEditText = rootView.findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the product list based on the search text
                filterProducts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        displayProducts(rootView, combinedList);

        return rootView;
    }

    private void filterProducts(String searchText) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productListBedding) {
            if (product.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(product);
            }
        }
        // Display the filtered product list
        displayProducts(getView(), filteredList);
    }


    private void displayProducts(View rootView, List<Product> productList) {
        GridLayout gridLayout = rootView.findViewById(R.id.gridLayout);
        gridLayout.removeAllViews();

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);

            View productItemView = LayoutInflater.from(requireContext()).inflate(R.layout.product_item_layout2, gridLayout, false);

            ImageView productImageView = productItemView.findViewById(R.id.productImageView2);
            TextView productNameTextView = productItemView.findViewById(R.id.productNameTextView2);
            TextView productPriceTextView = productItemView.findViewById(R.id.productPriceTextView2);  // New TextView for price
            ImageView deleteButton = productItemView.findViewById(R.id.delete2);

            productNameTextView.setText(product.getTitle());
            // Set the price text
            productPriceTextView.setText("Price:" + String.format("%.2f", product.getPrice())+ "TND");

            deleteButton.setOnClickListener(view -> {
                deleteProduct(product);
                // Refresh the product list
                refreshProductList();
            });

            gridLayout.addView(productItemView);
        }
    }

    private void deleteProduct(Product product) {
        productDAO.deleteById(product.getId());
        refreshProductList();
    }


    private void refreshProductList() {
        productListBedding = productDAO.getProductsByCategory(category);
        productListFinding = productDAO.getProductsByCategory(Category.Bedding);
        List<Product> combinedList = new ArrayList<>();
        combinedList.addAll(productListBedding);
        combinedList.addAll(productListFinding);

        View rootView = getView();
        if (rootView != null) {
            displayProducts(rootView, combinedList);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

}