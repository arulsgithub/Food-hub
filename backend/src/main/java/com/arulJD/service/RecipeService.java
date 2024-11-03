package com.arulJD.service;

import com.arulJD.entity.Recipe;
import com.arulJD.entity.User;

import java.util.List;

public interface RecipeService {

    public Recipe createRecipe(Recipe recipe, User user);
    public Recipe findRecipeById(Long id) throws Exception;
    public void deleteRecipeById(Long id) throws Exception;
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception;
    public List<Recipe> findAllRecipes();
    public Recipe likeRecipe(Long id, User user) throws Exception;
}
