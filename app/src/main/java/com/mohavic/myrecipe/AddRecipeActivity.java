package com.mohavic.myrecipe;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText etRecipeName, etIngredients, etSteps, etTemps;
    private Button btnSaveRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        etRecipeName = findViewById(R.id.etRecipeName);
        etIngredients = findViewById(R.id.etIngredients);
        etSteps = findViewById(R.id.etSteps);
        etTemps = findViewById(R.id.etTemps);
        btnSaveRecipe = findViewById(R.id.btnSaveRecipe);

        btnSaveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });
    }

    private void saveRecipe() {
        String name = etRecipeName.getText().toString();
        String ingredients = etIngredients.getText().toString();
        String steps = etSteps.getText().toString();
        String temps = etTemps.getText().toString();

        if (name.isEmpty() || ingredients.isEmpty() || steps.isEmpty() || temps.isEmpty()) {
            Toast.makeText(AddRecipeActivity.this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("Recipes", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String recipeKey = "recipe_" + System.currentTimeMillis();
        String recipeData = name + ";" + ingredients + ";" + steps + ";" + temps;
        editor.putString(recipeKey, recipeData);
        editor.apply();

        Toast.makeText(AddRecipeActivity.this, "Recette ajoutée avec succès", Toast.LENGTH_SHORT).show();
        finish();
    }
}
