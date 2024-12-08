package com.mohavic.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    private List<Recipe> filteredRecipeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterRecipes(newText);
                return true;
            }
        });
        loadRecipes();

        recipeAdapter = new RecipeAdapter(recipeList, new RecipeAdapter.OnRecipeClickListener() {
            @Override
            public void onRecipeClick(Recipe recipe) {
                Intent intent = new Intent(RecipeListActivity.this, RecipeDetailActivity.class);
                intent.putExtra("recipeName", recipe.getName());
                intent.putExtra("recipeIngredients", recipe.getIngredients());
                intent.putExtra("recipeSteps", recipe.getSteps());
                intent.putExtra("recipeTemps",recipe.getTemps());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(recipeAdapter);
    }

    private void loadRecipes() {
        recipeList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Recipes", MODE_PRIVATE);
        for (String key : sharedPreferences.getAll().keySet()) {
            String recipeData = sharedPreferences.getString(key, "");
            String[] parts = recipeData.split(";");
            if (parts.length == 4) {
                String name = parts[0];
                String ingredients = parts[1];
                String steps = parts[2];
                String temps = parts[3];
                Recipe recipe = new Recipe(name, ingredients, steps,temps);
                recipeList.add(recipe);
            }
        }
        filteredRecipeList = new ArrayList<>(recipeList);
        if (recipeList.isEmpty()) {
            Toast.makeText(this, "Aucune recette disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void filterRecipes(String query) {
        filteredRecipeList.clear();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredRecipeList.add(recipe);
            }
        }
        recipeAdapter.updateRecipes(filteredRecipeList);
    }
}
