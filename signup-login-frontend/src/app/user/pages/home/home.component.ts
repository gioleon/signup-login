import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { UserDTO } from '../../interfaces/user.interface';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  userList: UserDTO[] = [];

  constructor(private userService: UserService) {
    // this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe({
      next: (response) => (this.userList = response),
      error: () => console.log('an error has ocurred'),
    });
  }
}
