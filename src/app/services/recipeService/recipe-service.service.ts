import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private baseUrl = 'http://localhost:6010'

  constructor(private http:HttpClient) { }

  recipeSub = new BehaviorSubject<any>({
    
    recipes:[],
    loading:false,
    newRecipe:null
  })

  private getHeaders():HttpHeaders{
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('jwt')}`
    })
  }

  getRecipes():Observable<any>{

    const headers = this.getHeaders()
    return this.http.get(`${this.baseUrl}/auth/recipe`, {headers})
    .pipe(
      tap((recipes)=>{
        const currentState = this.recipeSub.value;
        this.recipeSub.next({...currentState, recipes})
      }
    ));
  }
  createRecipes(recipe:any):Observable<any>{

    const headers = this.getHeaders()
    return this.http.post(`${this.baseUrl}/auth/recipe`, recipe,{headers})
    .pipe(
      tap((newRecipe)=>{
        const currentState = this.recipeSub.value;
        this.recipeSub.next({...currentState, recipes:
          [newRecipe,...currentState.recipes]
        });
      }
    ));
  }

  updateRecipes(recipe:any):Observable<any>{

    const headers = this.getHeaders()
    return this.http.put(`${this.baseUrl}/auth/recipe/${recipe.id}`, 
      recipe, {headers})
    .pipe(
      tap((updatedRecipe:any)=>{
        const currentState = this.recipeSub.value;
        const updatedRecipes = currentState.recipes.map
        ((item:any)=>
          item.id === updatedRecipe.id? updatedRecipe : item
        );
        this.recipeSub.next({...currentState, recipe:updatedRecipes})
      }
    ));
  }

  deleteRecipes(id:any):Observable<any>{

    const headers = this.getHeaders()
    return this.http
    .delete(`${this.baseUrl}/auth/recipe/${id}`,{headers})
    .pipe(
      tap((deletedRecipe:any)=>{
        const currentState = this.recipeSub.value;
        const updatedRecipes = currentState.recipes.filter
        ((item:any)=>
          item.id !== id
        );
        this.recipeSub.next({...currentState, recipe:updatedRecipes})
      }
    ));
  }

  likeRecipes(id:any):Observable<any>{

    const headers = this.getHeaders()
    return this.http.put(`${this.baseUrl}/auth/recipe/${id}/like`, {headers})
    .pipe(
      tap((updatedRecipe:any)=>{
        const currentState = this.recipeSub.value;
        const updatedRecipes = currentState.recipes.map
        ((item:any)=>
          item.id === updatedRecipe.id? updatedRecipe : item
        );
        this.recipeSub.next({...currentState, recipe:updatedRecipes})
      }
    ));
  }
}