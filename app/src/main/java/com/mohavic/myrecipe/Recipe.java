package com.mohavic.myrecipe;

import java.io.Serializable;

public class Recipe implements Serializable{
    private String name;
    private String ingredients;
    private String steps;
    private String temps;

    public Recipe(String name, String ingredients, String steps, String temps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.temps = temps;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public String getTemps(){
        return temps;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public void setTemps(String temps){
        this.temps = temps;
    }
}
