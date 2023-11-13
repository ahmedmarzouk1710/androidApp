package com.ahmed.petapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ahmed.petapp.Database.AppDataBase;
import com.ahmed.petapp.Module.Post;
import com.example.petapp.R;

public class addPost extends AppCompatActivity {

    private EditText editTextDescription;
    private Button buttonAddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // Initialize views
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAddPost = findViewById(R.id.buttonAddPost);

        // Set click listener for the "Add Post" button
        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the description entered by the user
                String description = editTextDescription.getText().toString();

                // Create a new Post object with the retrieved values
                Post newPost = new Post();
                newPost.setDescription(description);

                // Insert the new post into the database
                insertPostIntoDatabase(newPost);

                // Optionally, you can finish the activity or navigate to another screen
                finish();
            }
        });
    }

    private void insertPostIntoDatabase(final Post post) {
        // Use a background thread or AsyncTask to perform database operations
        // For simplicity, we are using allowMainThreadQueries() in the AppDataBase class
        // In a production environment, consider using AsyncTask or other background execution methods
        AppDataBase appDataBase = AppDataBase.getAppDatabase(getApplicationContext());
        appDataBase.postDao().insertOne(post);
    }
}
