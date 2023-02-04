import { Component } from '@angular/core';
import { UserDTO } from '../../interfaces/user.interface';
import { SignUpService } from '../../services/signup.service';
import { LoginService } from '../../services/login.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  error: boolean = false;

  userDTO: UserDTO = {
    name: '',
    lastName: '',
    email: '',
    password: '',
  };

  confirmPassword:string = '';

  constructor(private signUpService:SignUpService) {}

  saveUser() {
    this.signUpService.signup(this.userDTO).subscribe({
      next: () => {
        this.error = false;
      },
      error: () => (this.error = true),
    });

    this.userDTO = {
      name: '',
      lastName: '',
      email: '',
      password: '',
    };

    this.confirmPassword = "";
  }
}
