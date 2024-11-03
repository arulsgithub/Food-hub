import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioButton, MatRadioGroup } from '@angular/material/radio';


@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatButtonModule,
    MatRadioButton,
    MatRadioGroup
  ],
  templateUrl: './create-recipe-form.component.html',
  styleUrl: './create-recipe-form.component.scss'
})
export class CreateRecipeFormComponent {

  recipeItem:any = {
    title:"",
    description:"",
    foodType:"",
    image:""
  }

  onSubmit(){
    console.log("values", this.recipeItem);
  }
}
