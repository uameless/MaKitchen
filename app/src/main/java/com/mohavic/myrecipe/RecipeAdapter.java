package com.mohavic.myrecipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    private OnRecipeClickListener onRecipeClickListener;

    public interface OnRecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    public RecipeAdapter(List<Recipe> recipeList, OnRecipeClickListener listener) {
        this.recipeList = recipeList;
        this.onRecipeClickListener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.tvRecipeTitle.setText(recipe.getName());
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecipeClickListener.onRecipeClick(recipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void updateRecipes(List<Recipe> newRecipes) {
        this.recipeList = newRecipes;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView tvRecipeTitle;
        Button btnDetails;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            tvRecipeTitle = itemView.findViewById(R.id.tvRecipeTitle);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
