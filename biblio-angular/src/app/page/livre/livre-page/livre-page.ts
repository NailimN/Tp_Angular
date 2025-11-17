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
    console.log(this.auteurCtrl.value);
    
    const editeurId = this.editeurCtrl.value;
    const collectionId = this.collectionCtrl.value;
    const genreId = this.genreCtrl.value;
    const auteurId = this.auteurCtrl.value;
    
    this.editeurs$.subscribe(editeurs => {
      const editeur = editeurs.find(e => e.id === editeurId);
      const editeurDto = editeur ? new EditeurDto(editeur.id, editeur.nom, editeur.pays) : new EditeurDto(0, '', '');
      
      this.collections$.subscribe(collections => {
        const collection = collections.find(c => c.id === collectionId);
        const collectionDto = collection ? new CollectionDto(collection.id, collection.nom) : new CollectionDto(0, '');
        
        this.genres$.subscribe(genres => {
          const genre = genres.find(g => g.id === genreId);
          const genreDto = genre ? new GenreDto(genre.id, genre.libelle) : new GenreDto(0, '');
          
          this.auteurs$.subscribe(auteurs => {
            const auteur = auteurs.find(a => a.id === auteurId);
            const auteurDto = auteur ? new AuteurDto(auteur.id, auteur.nom, auteur.prenom, auteur.nationalite) : new AuteurDto(0, '', '', '');
            
            if (this.editingLivre) {
              this.livreService.save(new LivreDto(
                this.editingLivre.id, 
                this.titreCtrl.value,
                this.anneeCtrl.value, 
                this.resumeCtrl.value, 
                editeurDto,
                collectionDto,
                genreDto,
                auteurDto
              ));
            } else {
              this.livreService.save(new LivreDto(
                0, 
                this.titreCtrl.value,
                this.anneeCtrl.value,  
                this.resumeCtrl.value, 
                editeurDto,
                collectionDto,
                genreDto,
                auteurDto
              ));
            }
            
            this.editingLivre = null;
            this.titreCtrl.setValue("");
            this.resumeCtrl.setValue("");
            this.anneeCtrl.setValue("");
            this.auteurCtrl.setValue("");
            this.genreCtrl.setValue("");
            this.editeurCtrl.setValue("");
            this.collectionCtrl.setValue("");
          });
        });
      });
    });
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
    this.auteurCtrl.setValue(livre.auteur.id);
    this.genreCtrl.setValue(livre.genre.id);
    this.editeurCtrl.setValue(livre.editeur.id);
    this.collectionCtrl.setValue(livre.collection.id);
    
  }
}
