package com.example.recipe;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class RecipeController {
    RecipeService recipeObj = new RecipeService();

    @DeleteMapping("/recipes/{recipeId}")
    public void deleteRecipe(@PathVariable int recipeId) {
        recipeObj.deleteRecipe(recipeId);
    }

    @PutMapping("/recipes/{recipeId}")
    public Recipe updateRecipe(@PathVariable int recipeId, @RequestBody Recipe recipe) {
        return recipeObj.updateRecipe(recipeId, recipe);
    }

    @PostMapping("/recipes")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeObj.addRecipe(recipe);
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getRecipeById(@PathVariable int recipeId) {
        return recipeObj.getRecipeById(recipeId);
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeObj.getRecipes();
    } 
}