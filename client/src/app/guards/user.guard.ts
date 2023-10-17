import {
  Router,
  CanActivateFn,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { inject } from '@angular/core';


export const userGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const userSvc: UserService = inject(UserService);
  const router: Router = inject(Router);
  let users:User[] = [];

  userSvc.getCurrentUser().subscribe((userData) => {
    users = userData;
  });
  if(users.length === 0) {
    //router.navigate(['/']);
    router.createUrlTree(['/']);
    return false;
  } else {
    //check role, check exp by decode with jwt_decode
    const role = users[0].roles[0];
    if(role === 'ROLE_CUSTOMER') {
      return true;
    }
    router.createUrlTree(['/']);
    return false;
  }
};
