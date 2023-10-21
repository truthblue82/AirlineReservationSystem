import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';

import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { inject } from '@angular/core';
import { environment } from 'src/environments/environment';
export const adminGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const router: Router = inject(Router)  ;
  const userSvc: UserService = inject(UserService);
  let users:User[] = [];

  userSvc.getCurrentUser().subscribe((userData) => {
    users = userData;
  })
  if(users.length > 0) {
    if(users[0].roles[0].name === "ROLE_ADMIN") {
      return true;
    }
    else {
      router.navigate(['/']);
      return false;
    }
  } else {
    router.navigate(['/']);
    return false;
  }
};
