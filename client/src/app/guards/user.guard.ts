import {
  Router,
  CanActivateFn,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';


export const userGuard: CanActivateFn = (route, state) => {
  //route = Inject(Router);
  //const service = Inject(UserService);

  // return () => {
  //   const oauthService: AuthService = inject(AuthService);

  //   if (oauthService.hasAccess() ) {
  //     return true;
  //   }
  //   oauthService.login();
  //   return false;
  // };
  // return () => {
  //   const oauthService: AuthService = inject(AuthService);

  //   if (oauthService.hasAccess() ) {
  //     return true;
  //   }
  //   oauthService.login();
  //   return false;
  // };
  return true;
};
