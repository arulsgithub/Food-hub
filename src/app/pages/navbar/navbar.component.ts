import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AuthServiceService } from '../../services/AuthService/auth-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  user:any=null;

  constructor(public authService: AuthServiceService,private router: Router){}
  ngOnInit(){
    this.authService.authSub.subscribe(
    (auth)=>{
        console.log("auth state", auth)
        this.user = auth.user
    })
  }

  handleLogout = () => {
    this.authService.logout();
    this.router.navigate(["/"])
  }
}
