import { Injectable } from '@angular/core';
import { startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LivreDto } from '../dto/livre-dto';

@Injectable({
  providedIn: 'root',
})
export class LivreService {
  private apiUrl: string = '/livre';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findAll(): Observable<LivreDto[]> {
    return this.refresh$.pipe(
      // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que MatiereDto[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<LivreDto[]>(this.apiUrl);
      })
    );
  }

  public findById(id: number): Observable<LivreDto> {
    return this.http.get<LivreDto>(`${this.apiUrl}/${id}`);
  }

  public save(livreDto: LivreDto): void {
    const payload = livreDto.toJson();

    if (!livreDto.id) {
      this.http.post<LivreDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<LivreDto>(`${this.apiUrl}/${livreDto.id}`, payload).subscribe(() => this.refresh());

  }

  public deleteById(id: number): void {
     this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());;
  }

}
