package com.arulJD.controller;

import com.arulJD.entity.Recipe;
import com.arulJD.entity.User;
import com.arulJD.service.RecipeService;
import com.arulJD.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController  {

    private RecipeService recipeService;
    private UserService userService;

    @PostMapping("/api/recipe/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        return recipeService.createRecipe(recipe,user);
    }

    @GetMapping("/api/recipe")
    public List<Recipe> getAllRecipe(){
        return recipeService.findAllRecipes();
    }

    @DeleteMapping("/api/recipe/{id}")
    public String deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteRecipeById(id);
        return "Recipe deleted successfully";
    }

    @PutMapping("/api/recipe/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{
        return recipeService.updateRecipe(recipe,id);
    }

    @PutMapping("/api/recipe/{id}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId) throws Exception{
        User user = userService.findUserById(userId);
        return recipeService.likeRecipe(id,user);
    }
}
