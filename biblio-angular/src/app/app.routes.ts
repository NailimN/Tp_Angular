import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { authGuard } from './guard/auth-guard';
import { LoginPage } from './page/login-page/login-page';
import { LivrePage } from './page/livre/livre-page/livre-page';
import { LivreDetailPage } from './page/livre/livre-detail-page/livre-detail-page';
import { GenrePage } from './page/genre/genre-page/genre-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { EditeurDetailPage } from './page/editeur/editeur-detail-page/editeur-detail-page';

export const routes: Routes = [
    {path: '', component: HomePage},
    {path: 'home', component: HomePage, canActivate: [authGuard]},
    {path: 'login', component: LoginPage},
    {path: 'livres', component: LivrePage, canActivate: [authGuard]},
    {path: 'livres/:id', component: LivreDetailPage, canActivate: [authGuard]},
    {path: 'genre', component: GenrePage, canActivate: [authGuard]},
    {path: 'editeur', component: EditeurPage, canActivate: [authGuard]},
    {path: 'editeur/:id', component: EditeurDetailPage, canActivate: [authGuard]},
    {path: 'collection', component: CollectionPage, canActivate: [authGuard]},
    {path: 'auteur', component: AuteurPage, canActivate: [authGuard]},
    // {path: 'auteur/{:id}', component: AuteurPage, canActivate: [authGuard]}
];
