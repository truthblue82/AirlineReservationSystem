import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { UserService } from '../services/user.service';
import { environment } from 'src/environments/environment';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private userSvc: UserService,
    private toastr: ToastrService
    ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let user = this.userSvc.getCurrentUser();
    let token = localStorage.getItem('token');

    if(user && token) {
      request = request.clone({
        setHeaders: {
          'Content-Type' : 'application/json',
          'Accept'       : 'application/json',
          'Authorization': `Bearer ${token}`,
          'Access-Control-Allow-Origin': environment.APP_BASE_URL
        }
      });

      return next.handle(request).pipe(
        tap({
          next: (event) => {
            if(event instanceof HttpResponse) {
              if(event.status === 401) {
                this.toastr.error('Unauthorized access!', 'Error');
                this.router.navigate(['/'], {queryParams: null});
              }
            }
            return event;
          },
          error: (error) => {
            if(error.status === 401) {
              this.toastr.error('Unauthorized access!');
            } else if(error.status === 404){
              this.toastr.error('Page not found!', 'Error');
            }
            this.router.navigate(['/'], {queryParams: null});
          }
        }));
    } else
      return next.handle(request);
  }
}
