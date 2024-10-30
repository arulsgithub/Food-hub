package com.arulJD.service.impl;

import com.arulJD.entity.Recipe;
import com.arulJD.entity.User;
import com.arulJD.repository.RecipeRepository;
import com.arulJD.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {

        Recipe createdRecipe = new Recipe();
        createdRecipe.setName(recipe.getName());
        createdRecipe.setImage(recipe.getImage());
        createdRecipe.setDescription(recipe.getDescription());
        createdRecipe.setUser(user);
        createdRecipe.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(createdRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {

        Optional<Recipe> isExistRecipe= recipeRepository.findById(id);
        if(isExistRecipe.isPresent()) return isExistRecipe.get();

        throw new Exception("Recipe not found with id: "+id);
    }

    @Override
    public void deleteRecipeById(Long id) throws Exception {
        Optional<Recipe> isExistRecipe= recipeRepository.findById(id);
        if(isExistRecipe.isPresent()) recipeRepository.deleteById(id);
        else throw new Exception("Recipe not found with id: "+id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Optional<Recipe> isExistRecipe= recipeRepository.findById(id);
        if(isExistRecipe.isPresent()){
            Recipe updateRecipe = isExistRecipe.get();
            updateRecipe.setName(recipe.getName());
            updateRecipe.setImage(recipe.getImage());
            updateRecipe.setDescription(recipe.getDescription());
            return recipeRepository.save(updateRecipe);
        }
        throw new Exception("Recipe not found with id: "+id);
    }

    @Override
    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long id, User user) throws Exception {
        Recipe recipe = findRecipeById(id);

        if(recipe.getLikes().contains(user.getId()))
            recipe.getLikes().remove(user.getId());
        else
            recipe.getLikes().add(user.getId());
        return null;
    }
}
