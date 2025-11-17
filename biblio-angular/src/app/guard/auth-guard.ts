import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthService);

  if (authService.token) {
    console.log("AuthGuard: utilisateur authentifié");
     // Ici, si on a un token, c'est qu'on est connecté
     return true;
  }

   // Navigation à utiliser dans les guard
  router.createUrlTree([ '/login' ]);
  return false;
};
