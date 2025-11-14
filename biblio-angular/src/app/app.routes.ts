import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { authGuard } from './guard/auth-guard';
import { LoginPage } from './page/login-page/login-page';
import { LivrePage } from './page/livre/livre-page/livre-page';
import { LivreDetailPage } from './page/livre/livre-detail-page/livre-detail-page';

export const routes: Routes = [
    {path: '', component: HomePage},
    {path: 'home', component: HomePage, canActivate: [authGuard]},
    {path: 'login', component: LoginPage},
    {path: 'livres', component: LivrePage, canActivate: [authGuard]},
    {path: 'livres/{:id}', component: LivreDetailPage, canActivate: [authGuard]}
];
