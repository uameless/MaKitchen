package com.mohavic.myrecipe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditRecipeActivity extends AppCompatActivity {

    private EditText etRecipeName, etIngredients, etSteps,etTemps;
    private Button btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        etRecipeName = findViewById(R.id.etRecipeName);
        etIngredients = findViewById(R.id.etIngredients);
        etSteps = findViewById(R.id.etSteps);
        etTemps = findViewById(R.id.etTemps);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeIngredients = getIntent().getStringExtra("recipeIngredients");
        String recipeSteps = getIntent().getStringExtra("recipeSteps");
        String recipeTemps = getIntent().getStringExtra("recopeTemps");

        etRecipeName.setText(recipeName);
        etIngredients.setText(recipeIngredients);
        etSteps.setText(recipeSteps);
        etTemps.setText(recipeTemps);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etRecipeName.getText().toString();
                String newIngredients = etIngredients.getText().toString();
                String newSteps = etSteps.getText().toString();
                String newTemps = etTemps.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("Recipes", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                for (String key : sharedPreferences.getAll().keySet()) {
                    String recipeData = sharedPreferences.getString(key, "");
                    String[] parts = recipeData.split(";");
                    if (parts.length == 4 && parts[0].equals(recipeName)) {
                        String updatedData = newName + ";" + newIngredients + ";" + newSteps + ";" + newTemps;
                        editor.putString(key, updatedData);
                        editor.apply();
                        Toast.makeText(EditRecipeActivity.this, "Recette mise à jour", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
                Toast.makeText(EditRecipeActivity.this, "Recette non trouvée", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
