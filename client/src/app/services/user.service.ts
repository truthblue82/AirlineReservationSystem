import { Inject, Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthData } from '../models/authData';
import { CookieService } from 'ngx-cookie-service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  users: User[];
  cookieSvc = inject(CookieService);

  constructor(
    private http: HttpClient,
    private router: Router
    ) {
    this.users = [];
    if (localStorage.getItem('token') !== null) {
      const data: any = {
        firstName: sessionStorage.getItem('firstName'),
        email: sessionStorage.getItem('email'),
        lastName: sessionStorage.getItem('lastName'),
        password: '',
        phone: sessionStorage.getItem('phone') ,
        roles: [sessionStorage.getItem('roles')]
      };
      this.users.push(data);
    }
  }

  getCurrentUser() {
    return of(this.users);
  }

  signUp(user: User) {
    return this.http.post(
      `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_USER_REGISTER_URI}`,
      user,
      {observe: 'response'}
      );
  }

  authenticate(code: string) {
    let body = new URLSearchParams();
    body.set('grant_type', environment.GATEWAY_GRANT_TYPE);
    body.set('code', code);
    body.set('redirect_uri', environment.APP_BASE_URL);

    let authorizationData = 'Basic ' + btoa(environment.GATEWAY_OAUTH2_URI_USERNAME
                          + ':' + environment.GATEWAY_OAUTH2_URI_PASSWORD);

    let headers = new HttpHeaders()
                  .set('Content-Type', 'application/x-www-form-urlencoded')
                  .set('Authorization', authorizationData);

    let options = { headers: headers };

    return this.http.post(
          `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_OAUTH2_URI}`,
          body.toString(),
          options
      );
  }

  getLoginUser(data: AuthData):void {
    localStorage.setItem('token', data.access_token);
    localStorage.setItem('refresh_token', data.refresh_token);
    localStorage.setItem('expires_in', data.expires_in.toString());

    this.http.get(
      `${environment.GATEWAY_BASE_URL}/${environment.GATEWAY_USERINFO_URI}`,
      {observe: 'response'})
      .subscribe((result) => {
      if(result.status === 200 && result.ok === true ) {
        let userData: User = result.body as User;
        let roles = userData.roles;
        userData = {
          ...userData,
          roles: [roles[0].name as string]
        }
        this.users.push(userData as User);
        this.storeSession(userData as User);
        this.router.navigate(['/'], {queryParams: null});
      } else {
        this.router.navigate(['/'], {queryParams: null});
      }
    },
    error => {
      this.router.navigate(['/'], {queryParams: null});
    });
  }
  clearSession():void {
    //cookie JSESSIONID come frome gateway service
    this.cookieSvc.deleteAll("/", environment.APP_BASE_URL, false, 'None');
    sessionStorage.clear();
    localStorage.clear();
  }
  storeSession(data: User):void {
    sessionStorage.setItem('firstName', data.firstName);
    sessionStorage.setItem('lastName', data.lastName);
    sessionStorage.setItem('email', data.email);
    sessionStorage.setItem('phone', data.phone);
    sessionStorage.setItem('roles', data.roles[0]);
  }
  logout() {
    this.clearSession();
    this.users.splice(0,1);
  }
  updateAccountDetails(data: any) {
    return this.http.put(
      `${environment.GATEWAY_BASE_URL}/users`,
      data,
      {observe: "response"}
    );
  }
  // changeUserPassword(data: any) {
  //   return this.http.put(`${environment.BASE_SERVICE_URL}/api/users/`, {
  //     data: {oldpassword: data.oldPassword, newpassword: data.newPassword},
  //   }, {observe: "response"});
  // }
}
