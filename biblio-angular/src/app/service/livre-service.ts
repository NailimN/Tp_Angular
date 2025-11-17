import { Injectable } from '@angular/core';
import { map, startWith, Subject, switchMap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LivreDto } from '../dto/livre-dto';
import { EditeurDto } from '../dto/editeur-dto';
import { CollectionDto } from '../dto/collection-dto';
import { GenreDto } from '../dto/genre-dto';
import { AuteurDto } from '../dto/auteur-dto';


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
    startWith(null),
    switchMap(() =>
      this.http.get<LivreDto[]>(this.apiUrl).pipe(   // ✔️ Réponse typée
        map((list: LivreDto[]) =>
          list.map((data: LivreDto) =>
            new LivreDto(
              data.id,
              data.titre,
              data.annee,
              data.resume,
              new EditeurDto(data.editeur.id, data.editeur.nom, data.editeur.pays),
              new CollectionDto(data.collection.id, data.collection.nom),
              new GenreDto(data.genre.id, data.genre.libelle),
              new AuteurDto(data.auteur.id, data.auteur.nom, data.auteur.nom, data.auteur.nationalite)
            )
          )
        )
      )
    )
  );
}


  public findById(id: number): Observable<LivreDto> {
    return this.http.get<LivreDto>(`${this.apiUrl}/${id}`);
  }

  public save(livreDto: LivreDto): void {

    console.log(livreDto.toJson())
    const payload = livreDto.toJson();

    if (!livreDto.id) {
      console.log("Creating new livre");
      this.http.post<LivreDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }
    console.log("Updating existing livre");
    this.http.put<LivreDto>(`${this.apiUrl}/${livreDto.id}`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
     this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());;
  }

}
