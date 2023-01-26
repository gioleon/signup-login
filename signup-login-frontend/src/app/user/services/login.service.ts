import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../interfaces/user.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(user: UserDTO): Observable<UserDTO> {
    const url: string = `${this.apiUrl}/api/login`;
    return this.http.post<UserDTO>(url, user);
  }
}
