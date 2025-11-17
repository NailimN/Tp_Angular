import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LivreDto } from '../../../dto/livre-dto';
import { LivreService } from '../../../service/livre-service';
import { Observable } from 'rxjs';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CollectionDto } from '../../../dto/collection-dto';
import { GenreDto } from '../../../dto/genre-dto';
import { AuteurDto } from '../../../dto/auteur-dto';
import { EditeurDto } from '../../../dto/editeur-dto';
import { AuteurService } from '../../../service/auteur-service';
import { GenreService } from '../../../service/genre-service';
import { EditeurService } from '../../../service/editeur-service';
import { CollectionService } from '../../../service/collection-service';

@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage {
  public livres$!: Observable<LivreDto[]>;
  public auteurs$!: Observable<AuteurDto[]>;
  public genres$!: Observable<GenreDto[]>;
  public editeurs$!: Observable<EditeurDto[]>;
  public collections$!: Observable<CollectionDto[]>;
  protected livreForm!: FormGroup;
  protected titreCtrl!: FormControl;
  protected resumeCtrl!: FormControl;
  protected anneeCtrl!: FormControl;
  protected auteurCtrl!: FormControl;
  protected genreCtrl!: FormControl;
  protected editeurCtrl!: FormControl;
  protected collectionCtrl!: FormControl;


  protected editingLivre!: LivreDto | null;

  constructor(private livreService: LivreService, private auteurService: AuteurService,private genreService: GenreService, private editeurService: EditeurService, private collectionService: CollectionService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.livres$ = this.livreService.findAll();
    this.auteurs$ = this.auteurService.findAll();
    this.genres$ = this.genreService.findAll();
    this.editeurs$ = this.editeurService.findAll();
    this.collections$ = this.collectionService.findAll();
    this.titreCtrl = this.formBuilder.control('', Validators.required);
    this.resumeCtrl = this.formBuilder.control('', Validators.required);
    this.anneeCtrl = this.formBuilder.control('', Validators.required);
    this.auteurCtrl = this.formBuilder.control('', Validators.required);
    this.genreCtrl = this.formBuilder.control('', Validators.required);
    this.editeurCtrl = this.formBuilder.control('', Validators.required);
    this.collectionCtrl = this.formBuilder.control('', Validators.required);

    this.livreForm = this.formBuilder.group({
      titre: this.titreCtrl,
      resume: this.resumeCtrl,
      annee: this.anneeCtrl,
      auteur: this.auteurCtrl,
      genre: this.genreCtrl,
      editeur: this.editeurCtrl,
      collection: this.collectionCtrl

    });
  }

  public creerOuModifier() {
    if (this.editingLivre) {
      this.livreService.save(new LivreDto(this.editingLivre.id, this.titreCtrl.value, this.resumeCtrl.value, this.anneeCtrl.value, this.auteurCtrl.value, this.genreCtrl.value, this.editeurCtrl.value, this.collectionCtrl.value));
    }

    else {
      this.livreService.save(new LivreDto(0, this.titreCtrl.value, this.resumeCtrl.value, this.anneeCtrl.value, this.auteurCtrl.value, this.genreCtrl.value, this.editeurCtrl.value, this.collectionCtrl.value));
    }

    this.editingLivre = null;
    this.titreCtrl.setValue("");
    this.resumeCtrl.setValue("");
    this.anneeCtrl.setValue("");
    this.auteurCtrl.setValue("");
    this.genreCtrl.setValue("");
    this.editeurCtrl.setValue("");
    this.collectionCtrl.setValue("");
  }

  public trackLivre(index: number, value: LivreDto) {
    return value.id;
  }

  public delete(livre: LivreDto): void {
    this.livreService.deleteById(livre.id);
  }

  public update(livre: LivreDto): void {
    this.editingLivre = livre;
    this.titreCtrl.setValue(livre.titre);
    this.resumeCtrl.setValue(livre.resume);
    this.anneeCtrl.setValue(livre.annee);
    this.auteurCtrl.setValue(livre.auteur);
    this.genreCtrl.setValue(livre.genre);
    this.editeurCtrl.setValue(livre.editeur);
    this.collectionCtrl.setValue(livre.collection);

  }

}
