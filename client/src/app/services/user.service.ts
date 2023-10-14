import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/models/user';
import { Role } from 'src/app/models/role';
import { of } from 'rxjs';
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
    console.log('User service: signup user', user);
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signup`, user);
  }

  authenticate(username: string, password: string) {
    console.log('login', username, password, environment.BASE_SERVICE_URL);
    return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signin`, {username, password})
    .subscribe(
      userData => {
        console.log('userData', userData)
        sessionStorage.setItem('username', username);
        //let token = `Bearer ${userData.accessToken ?? ""}`;
        // sessionStorage.setItem('token', token);
        // sessionStorage.setItem('userId', userData.id);
        // sessionStorage.setItem('email', userData.email);
        // sessionStorage.setItem('roles', userData.roles);
        // sessionStorage.setItem('fullname', userData.fullname);
        // sessionStorage.setItem('phone', userData.phone);
        return userData;
      }
    )
  }
}
