package com.arulJD.controller;

import com.arulJD.entity.Recipe;
import com.arulJD.entity.User;
import com.arulJD.service.RecipeService;
import com.arulJD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController  {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId) throws Exception {
        User user = userService.findUserById(userId);
        return recipeService.createRecipe(recipe,user);
    }

    @GetMapping
    public List<Recipe> getAllRecipe(){
        return recipeService.findAllRecipes();
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable Long id) throws Exception{
        recipeService.deleteRecipeById(id);
        return "Recipe deleted successfully";
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception{
        return recipeService.updateRecipe(recipe,id);
    }

    @PutMapping("/{id}/like/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long id, @PathVariable Long userId) throws Exception{
        User user = userService.findUserById(userId);
        return recipeService.likeRecipe(id,user);
    }
}
