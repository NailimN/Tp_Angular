import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { AuteurDto } from '../../../dto/auteur-dto';
import { AuteurService } from '../../../service/auteur-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-auteur-page',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage implements OnInit {
protected auteur : AuteurDto = new AuteurDto(0,"","","") 
protected auteurs$!: Observable<AuteurDto[]>;

constructor(private auteurService: AuteurService) { }

  ngOnInit(): void {
    this.auteurs$ = this.auteurService.findAll();
  }

    public ajouterAuteur() {
      this.auteurService.save(this.auteur);
  
      this.auteur = new AuteurDto(0, "", "", "");
    }
  
    public modifierAuteur() {
      this.auteurService.save(this.auteur);
  
      this.auteur = new AuteurDto(0, "", "", "");
    }
  
    public editAuteur(auteur: AuteurDto): void {
      // Clone du TODO pour l'édition
      this.auteur = new AuteurDto(auteur.id, auteur.nom, auteur.prenom, auteur.nationalite);
    }
  
    public deleteAuteur(auteur: AuteurDto): void {
      this.auteurService.deleteById(auteur.id);
    }

  public trackAuteur(index: number, value: AuteurDto) {
    return value.id;
  }

}
