import {
  Router,
  CanActivateFn,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { inject } from '@angular/core';
import { User } from '../models/user';


export const userGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  let users:User[] = [];
  let userSvc: UserService = inject(UserService);
  let router: Router = inject(Router);

  userSvc.getCurrentUser().subscribe((userData) => {
    users = userData;
  });
  if(users.length === 0) {
    //router.navigate(['/']);
    router.navigate(['/']);
    return false;
  } else {
    //if login true, then return true
    //check role, check exp by decode with jwt_decode
    return true;
  }
};
