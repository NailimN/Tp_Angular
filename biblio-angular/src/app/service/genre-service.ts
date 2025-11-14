import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { GenreDto } from '../dto/genre-dto';

@Injectable({
  providedIn: 'root',
})
export class GenreService {

  private apiUrl: string = '/genre';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<GenreDto[]> {
  
      return this.refresh$.pipe(

        startWith(null),
        switchMap(() => {
          return this.http.get<GenreDto[]>(this.apiUrl)
        })
      );
  }
  
  public refresh(){
    this.refresh$.next();
  }

  public findById(id: number): Observable<GenreDto> {
    return this.http.get<GenreDto>(`${this.apiUrl}/${id}`);
  }

  public save(genreDto: GenreDto): void {

    const payload = genreDto.toJson();
    if (genreDto.id===0) {
      this.http.post<GenreDto>(this.apiUrl, payload).subscribe(() => this.refresh());
    }
    
    else {
      this.http.put<GenreDto>(`${this.apiUrl}/${genreDto.id}`, payload).subscribe(() => this.refresh());
    }
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}

