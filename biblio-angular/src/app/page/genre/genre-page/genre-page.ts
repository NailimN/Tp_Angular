import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { GenreDto } from '../../../dto/genre-dto';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { GenreService } from '../../../service/genre-service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-genre-page',
  imports: [ CommonModule, RouterLink, ReactiveFormsModule ],
  templateUrl: './genre-page.html',
  styleUrl: './genre-page.css',
})
export class GenrePage implements OnInit {

  protected genres$!: Observable<GenreDto[]>;
  protected genreForm!: FormGroup;
  protected libelleCtrl!: FormControl;
  protected editingGenre!: GenreDto | null;

  constructor(private genreService: GenreService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.genres$ = this.genreService.findAll();

    this.libelleCtrl = new FormControl('', Validators.required);

    this.genreForm = this.formBuilder.group({
      libelle: this.libelleCtrl
    });
  }

  public trackGenre(index: number, genre: GenreDto): number {
    return genre.id;
  }

  public creerOuModifier() {
    if (this.editingGenre) {
      this.genreService.save(new GenreDto(this.editingGenre.id, this.libelleCtrl.value));
    }
  
    else {
      this.genreService.save(new GenreDto(0, this.libelleCtrl.value));
    }

    this.editingGenre = null;
    this.libelleCtrl.reset();
  }

  public editer(genre: GenreDto) {
    this.editingGenre = genre;
    this.libelleCtrl.setValue(genre.libelle);
  }

  public supprimer(genre: GenreDto) {
    this.genreService.deleteById(genre.id);
  }

}
