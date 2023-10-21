import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user';
import { Role } from 'src/app/models/role';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthData } from '../models/authData';

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
        username: sessionStorage.getItem('firstName') || '',
        email: sessionStorage.getItem('email') || '',
        fullname: sessionStorage.getItem('lastName') || '',
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
    //return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signup`, user, {observe:'response'});
    return this.http.post(
      `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_USER_REGISTER_URI}`,
      user,
      {observe: 'response'}
      );
  }

  authenticate(code: string) {
    //return this.http.post(`${environment.BASE_SERVICE_URL}/api/auth/signin`, {username: user.username, password: user.password},{observe:'response'});
    let body = new URLSearchParams();
    body.set('grant_type', environment.GATEWAY_GRANT_TYPE);
    body.set('code', code);
    body.set('redirect_uri', environment.APP_BASE_URL);

    let authorizationData = 'Basic ' + btoa(environment.GATEWAY_OAUTH2_URI_USERNAME + ':' + environment.GATEWAY_OAUTH2_URI_PASSWORD);

    let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
                  .set('Authorization', authorizationData);
    let options = { headers: headers };

    return this.http.post(
      `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_OAUTH2_URI}`,
      body.toString(),
      options);
  }

  getLoginUser(data: AuthData):void {
    sessionStorage.setItem('token', data.access_token);
    sessionStorage.setItem('refresh_token', data.refresh_token);
    sessionStorage.setItem('expires_in', data.expires_in.toString());

    this.http.get(
      `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_USERINFO_URI}`,
      {observe: 'response'})
    .subscribe((result) => {
      console.log('getLoginUser result:', result);
      if(result.status === 200) {
        console.log('getLoginUser if result', result);
        //this.users.push(result.body: User);
      } else {
        console.log(result);
      }
    },
    error => {
      console.log(error);
    })

    //this.users.push(user);
  }
  clearSession():void {
    sessionStorage.clear();
  }
  logout() {
    sessionStorage.clear();
    this.users.splice(0,1);
  }
  updateAccountDetails(data: any) {
    return this.http.put(`${environment.BASE_SERVICE_URL}/api/users`, data, {observe: "response"});
  }
  changeUserPassword(data: any) {
    return this.http.put(`${environment.BASE_SERVICE_URL}/api/users/`, {
      data: {oldpassword: data.oldPassword, newpassword: data.newPassword},
    }, {observe: "response"});
  }
}
