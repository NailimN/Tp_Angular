import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { EditeurDto } from '../../../dto/editeur-dto';
import { EditeurService } from '../../../service/editeur-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editeur-page',
  imports: [
    CommonModule,
    RouterLink,
    FormsModule
  ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit{
  protected editeur: EditeurDto = new EditeurDto(0, "", "");
  protected editeurs$!: Observable<EditeurDto[]>;

  constructor(private editeurService: EditeurService) { }

  ngOnInit(): void {
    this.editeurs$ = this.editeurService.findAll();
  }

  public ajouterEditeur() {
    this.editeurService.save(this.editeur);

    this.editeur = new EditeurDto(0, "", "");
  }

  public modifierEditeur() {
    this.editeurService.save(this.editeur);

    this.editeur = new EditeurDto(0, "", "");
  }

  public editEditeur(editeur: EditeurDto): void {
    // Clone du TODO pour l'édition
    this.editeur = new EditeurDto(editeur.id, editeur.nom, editeur.pays);
  }

  public deleteEditeur(editeur: EditeurDto): void {
    this.editeurService.deleteById(editeur.id);
  }

  public trackEditeur(index: number, value: EditeurDto) {
    return value.id;
  }
}
