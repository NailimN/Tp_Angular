import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  return true;

  // const router = inject(Router);
  // const authService = inject(AuthService);

  // if (authService.token) {
  //   // Ici, si on a un token, c'est qu'on est connecté
  //   return true;
  // }

  // // Navigation à utiliser dans les guard
  // return router.createUrlTree([ '/login' ]);
};
