import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthService);

  if (authService.isAuthenticated()) {
  return true;
  }


   // Navigation à utiliser dans les guard
  router.createUrlTree([ '/login' ]);
  return false;
};
