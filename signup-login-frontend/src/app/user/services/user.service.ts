import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../interfaces/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getUsers(): Observable<UserDTO[]> {
    const url: string = `${this.apiUrl}/api/user`;
    return this.http.get<UserDTO[]>(url);
  }
}
