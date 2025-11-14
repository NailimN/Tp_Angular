import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AuteurDto } from '../dto/auteur-dto';

@Injectable({
  providedIn: 'root',
})
export class AuteurService {

private apiUrl: string = '/auteur';
private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<AuteurDto[]> {
    return this.refresh$.pipe( // permet de transformer un flux / manipuler un flux
      // Forcer un premier chargement
      startWith(null),

      // Transformer le "void" que AuteurDto[] en allant chercher les informations
      switchMap(() => {
        return this.http.get<AuteurDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<AuteurDto> {
    return this.http.get<AuteurDto>(`${ this.apiUrl }/${ id }`);
  }

  public save(AuteurDto: AuteurDto): void {
    const payload = AuteurDto.toJson();

    if (!AuteurDto.id) {
      this.http.post<AuteurDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }

    this.http.put<AuteurDto>(`${ this.apiUrl }/${ AuteurDto.id }`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${ this.apiUrl }/${ id }`).subscribe(() => this.refresh());
  }
}





  

