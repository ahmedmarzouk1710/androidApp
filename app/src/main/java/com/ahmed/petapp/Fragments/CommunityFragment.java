package com.ahmed.petapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.petapp.R;

import com.ahmed.petapp.Database.AppDataBase;
import com.ahmed.petapp.Module.Post;

import java.util.List;

public class CommunityFragment extends Fragment {

    private AppDataBase appDatabase;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        // Initialize the database
        appDatabase = AppDataBase.getAppDatabase(getContext());

        // Load posts from the database and add them to the UI
        loadAndDisplayPosts(rootView);

        return rootView;
    }

    private void loadAndDisplayPosts(View rootView) {
        LinearLayout postContainer = rootView.findViewById(R.id.post_container);

        // Retrieve posts from the database
        List<Post> posts = appDatabase.postDao().getAll();

        // Loop through posts and add them to the UI
        for (Post post : posts) {
            addPostItem(postContainer, post);
        }
    }

    private void addPostItem(LinearLayout postContainer, Post post) {
        View postItemView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, postContainer, false);

        // Find views in the post item layout
        TextView likesTextView = postItemView.findViewById(R.id.likes);
        TextView usernameTextView = postItemView.findViewById(R.id.username);
        TextView descriptionTextView = postItemView.findViewById(R.id.description);
        String likes = Integer.toString(post.getLikes());
        // Set values for the post item
        likesTextView.setText(likes);
        usernameTextView.setText(post.getUserName());
        descriptionTextView.setText(post.getDescription());

        // Add the post item to the container
        postContainer.addView(postItemView);
    }
}
