import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { UserService } from '../services/user.service';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private userSvc: UserService,) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const user = this.userSvc.getCurrentUser();
    let token = sessionStorage.getItem('token');
    if(token) {
      const cloned = request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + token)
        .set('Access-Control-Request-Headers', 'Origin, X-Auth-Token, Content-Type')
        .set('Access-Control-Allow-Credentials', '*')
        //.set('Access-Control-Request-Method', 'GET, POST, PATCH, PUT')
        //.set('Access-Control-Allow-Origin', '*')
      });

      return next.handle(cloned).pipe(tap(
        () => {}, (err: any) => {
          if(err instanceof HttpErrorResponse) {
            if(err.status === 401) {
              this.router.navigate(['login']);
            }
          }
        }
      ));
    } else
      return next.handle(request);
  }
}
