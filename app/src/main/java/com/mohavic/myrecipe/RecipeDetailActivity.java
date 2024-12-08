package com.mohavic.myrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView tvRecipeName, tvIngredients, tvSteps, tvTemps;
    private Button btnDeleteRecipe, btnEditRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        tvRecipeName = findViewById(R.id.tvRecipeName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvSteps = findViewById(R.id.tvSteps);
        tvTemps = findViewById(R.id.tvTemps);
        btnDeleteRecipe = findViewById(R.id.btnDeleteRecipe);
        btnEditRecipe = findViewById(R.id.btnEditRecipe);

        String name = getIntent().getStringExtra("recipeName");
        String ingredients = getIntent().getStringExtra("recipeIngredients");
        String steps = getIntent().getStringExtra("recipeSteps");
        String temps = getIntent().getStringExtra("recipeTemps");

        tvRecipeName.setText(name);
        tvIngredients.setText(ingredients);
        tvSteps.setText(steps);
        tvTemps.setText(temps);

        btnEditRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecipeDetailActivity.this, EditRecipeActivity.class);
                intent.putExtra("recipeName", name);
                intent.putExtra("recipeIngredients", ingredients);
                intent.putExtra("recipeSteps", steps);
                intent.putExtra("recipeTemps",temps);
                startActivity(intent);
            }
        });

        btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecipe(name);
            }
        });
    }

    private void deleteRecipe(String recipeName) {
        SharedPreferences sharedPreferences = getSharedPreferences("Recipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (String key : sharedPreferences.getAll().keySet()) {
            String recipeData = sharedPreferences.getString(key, "");
            String[] parts = recipeData.split(";");
            if (parts.length == 4 && parts[0].equals(recipeName)) {
                editor.remove(key);
                editor.apply();
                Toast.makeText(RecipeDetailActivity.this, "Recette supprimée", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        Toast.makeText(RecipeDetailActivity.this, "Recette introuvable", Toast.LENGTH_SHORT).show();
    }
}
