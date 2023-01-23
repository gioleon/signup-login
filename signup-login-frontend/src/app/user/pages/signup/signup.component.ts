import { Component } from '@angular/core';
import { UserDTO } from '../../interfaces/user.interface';
import { UserService } from '../../services/user.service';

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

  constructor(private userService: UserService) {}

  saveUser() {
    this.userService.saveUser(this.userDTO).subscribe({
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
  }
}
