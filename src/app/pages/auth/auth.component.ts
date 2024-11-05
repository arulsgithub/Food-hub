import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { AuthServiceService } from '../../services/AuthService/auth-service.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    CommonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatButtonModule,
    MatRadioModule,
    ReactiveFormsModule
  ],
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})
export class AuthComponent {

  constructor(public authService:AuthServiceService) { }

  isRegister = true;

  registrationForm = new FormGroup({
    fullname: new FormControl("", [Validators.required]),
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.required, 
      Validators.minLength(8), 
      Validators.maxLength(20)
    ]),
  })

 loginForm = new FormGroup({
    email: new FormControl("", [Validators.email, Validators.required]),
    password: new FormControl("", [Validators.required, 
    ]),
  })

  handleRegistration(){
    console.log("reister", this.registrationForm.value);
    this.authService.register(this.registrationForm.value).subscribe({
      
      next:(response)=>{

        localStorage.setItem("jwt",response.jwt)
        this.authService.getUserProfile().subscribe({});
        console.log("signup success", response)

      }
    })
  }

  handleLogin(){
    console.log("login", this.loginForm.value)
    this.authService.login(this.loginForm.value).subscribe({
      
      next:(response)=>{

        localStorage.setItem("jwt",response.jwt)
        this.authService.getUserProfile().subscribe({});
        console.log("login success", response)

      }
    })
    
  }

  togglePanel(){
    this.isRegister = !this.isRegister;
  }

}
