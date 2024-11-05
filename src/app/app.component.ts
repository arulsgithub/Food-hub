import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { FooterComponent } from './pages/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { RecipeCardComponent } from './pages/recipe-card/recipe-card.component';
import { AuthComponent } from "./pages/auth/auth.component";
import { AuthServiceService } from './services/AuthService/auth-service.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    FooterComponent,
    HomePageComponent,
    RecipeCardComponent,
    AuthComponent
],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'food-hub';

  user:any=null;

  constructor(public authService: AuthServiceService){}
  ngOnInit(){
    this.authService.getUserProfile().subscribe({
      next:data=>console.log("req user", data),
      error:err=>console.log("req user error", err)
    });
  }
}
