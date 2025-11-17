import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { AuthResponseDto } from '../dto/auth-response-dto';

@Injectable({
  providedIn: 'root',
})

export class AuthService {
  private _token: string = "";

  constructor(private http: HttpClient) {
    // On récupère le token de la sessionStorage, si ce token n'existe pas, on met un token vide
    this._token = sessionStorage.getItem("token") ?? "";
  }

  public get token(): string {
    return this._token;
  }

  public auth(authRequest: AuthRequestDto): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post<AuthResponseDto>('/auth', authRequest.toJson()).subscribe({
        // next => si la réponse est OK
        next: resp => {
          this._token = resp.token;

          // Stocker le jeton dans le navigateur, dans le sessionStorage, avec la clé "token"
          sessionStorage.setItem("token", this._token);

          // Quand le resolve va s'exécuter ... côté appelant, on pourra savoir quand c'est terminé
          resolve();
        },

        // error => si la réponse est KO (30X, 40X, 50X)
        error: err => reject(err)
      });
    })
  }

    isAuthenticated(): boolean {
    const token = this.token;
    if (!token) return false;

    return !this.isJwtExpired(token);
  }

  isJwtExpired(token: string): boolean {
    try {
      const payload = token.split('.')[1];
      const decoded = JSON.parse(this.base64UrlDecode(payload));
      const exp = decoded.exp;
      if (!exp) return true;

      const now = Math.floor(Date.now() / 1000);
      return exp < now;
    } catch {
      return true;
    }
  }

  base64UrlDecode(str: string): string {
    str = str.replace(/-/g, '+').replace(/_/g, '/');
    const pad = str.length % 4;
    if (pad) str += '='.repeat(4 - pad);
    return atob(str);
  }
}

