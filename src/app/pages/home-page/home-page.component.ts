import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';


@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    RecipeCardComponent,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {
  recipes = [1,2,3,4,5,6,7,8];

  constructor( public dialog: MatDialog) { }
  handleRecipeForm(){
    this.dialog.open(CreateRecipeFormComponent);
  }
}
