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
  users: User[];

  constructor(private http: HttpClient) {
    this.users = [];
    if (sessionStorage.getItem('token') !== null) {
      const data: any = {
        id: sessionStorage.getItem('userId') || '',
        username: sessionStorage.getItem('username') || '',
        email: sessionStorage.getItem('email') || '',
        fullname: sessionStorage.getItem('fullname') || '',
        password: '',
        phone: sessionStorage.getItem('phone') || '',
        roles: [sessionStorage.getItem('roles') || ''],
        token: sessionStorage.getItem('token') || '',
        rememberMe: sessionStorage.getItem('rememberMe') ? sessionStorage.getItem('rememberMe') : false
      };
      this.users.push(data);
    }
  }

  getCurrentUser() {
    return of(this.users);
  }

  signUp(user: User) {
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signup`, user, {observe:'response'});
  }

  authenticate(user: any) {
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signin`, {username: user.username, password: user.password},{observe:'response'});
  }

  storeSession(user: any):void {
    sessionStorage.setItem('token', user.accessToken);
    sessionStorage.setItem('username', user.username);
    sessionStorage.setItem('fullname', user.fullname);
    sessionStorage.setItem('email', user.email);
    sessionStorage.setItem('roles', user.roles);
    sessionStorage.setItem('userId', user.id);
    sessionStorage.setItem('rememberMe', user.rememberMe);
    //refreshToken?
    this.users.push(user);
  }
  clearSession():void {
    sessionStorage.clear();
  }
  logout() {
    sessionStorage.clear();
    this.users.splice(0,1);
  }
}
