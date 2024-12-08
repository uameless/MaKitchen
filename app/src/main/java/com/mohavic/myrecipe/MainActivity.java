package com.mohavic.myrecipe;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAddRecipe, btnViewRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnViewRecipes = findViewById(R.id.btnViewRecipes);

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        btnViewRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });
    }
}
