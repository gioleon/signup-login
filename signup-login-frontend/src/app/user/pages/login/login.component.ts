import { Component } from '@angular/core';
import { UserDTO } from '../../interfaces/user.interface';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  error: boolean = false;

  userDTO: UserDTO = {
    email: '',
    password: '',
  };

  constructor(private loginService: LoginService) {}

  login() {
    this.loginService.login(this.userDTO).subscribe({
      next: (response) => {
        this.error = false;
      },
      error: () => (this.error = true),
    });
  }
}
