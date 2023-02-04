import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Credential } from '../../interfaces/user.interface';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  error: boolean = false;

  credentials: Credential = {
    email: '',
    password: '',
  };

  constructor(
    private loginService: LoginService,
    private router: Router
  ) {
  }

  login() {
    this.loginService.login(this.credentials).subscribe({
      next: () => {
        this.error = false;
        this.router.navigate(['/']);
      },
      error: () => {
        this.error = true;
      },
    });
    
  }
}
