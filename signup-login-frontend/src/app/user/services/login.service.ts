import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { UserDTO, Credential } from '../interfaces/user.interface';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(credentials: Credential): Observable<void> {
    const url: string = `${this.apiUrl}/api/login`;
    return this.http
      .post<UserDTO>(url, credentials, {
        observe: 'response',
      })
      .pipe(
        map((response: HttpResponse<any>) => {
          const header = response.headers;

          const bearerToken: string = header.get('token')!;
          const token: string = bearerToken.replace('Bearer ', '');

          localStorage.setItem('Authentication', token);
        })
      );
  }

  getToken(): string {
    const token: string = localStorage.getItem('Authentication')!;

    return token;
  }
}
