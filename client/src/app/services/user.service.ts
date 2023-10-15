import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/models/user';
import { Role } from 'src/app/models/role';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: any[];

  constructor(private http: HttpClient) {
    this.user = [];
    if (sessionStorage.getItem('token') !== null) {
      const data: User = {
        id: sessionStorage.getItem('userId') || '',
        username: sessionStorage.getItem('username') || '',
        email: sessionStorage.getItem('email') || '',
        fullname: sessionStorage.getItem('fullname') || '',
        password: '',
        phone: sessionStorage.getItem('phone') || '',
        roles: [sessionStorage.getItem('roles') || ''],
        token: sessionStorage.getItem('token') || '',
      };
      this.user.push(data);
    }
  }

  getCurrentUser() {
    return of(this.user);
  }

  signUp(user: User) {
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signup`, user);
  }

  authenticate(user: any) {
    console.log('login', user.username, user.password, environment.BASE_SERVICE_URL);
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signin`, {username: user.username, password: user.password});
  }

  storeSession(user: any):void {
    sessionStorage.setItem('token', user.accessToken);
    sessionStorage.setItem('username', user.username);
    sessionStorage.setItem('email', user.email);
    sessionStorage.setItem('role', user.roles[0]);
    sessionStorage.setItem('userId', user.id);
    sessionStorage.setItem('rememberMe', user.rememberMe);
    //refreshToken?
  }
  clearSession():void {
    sessionStorage.clear();
  }
}
