import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { AuteurDto } from '../../../dto/auteur-dto';
import { AuteurService } from '../../../service/auteur-service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-auteur-page',
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage implements OnInit {

protected auteur : AuteurDto = new AuteurDto(0,"","","") 
protected auteurs$!: Observable<AuteurDto[]>;
protected auteurForm! : FormGroup;
protected nameCtrl!: FormControl;
protected preCtrl!:FormControl ;
protected natCtrl!: FormControl ;
protected editingAuteur!: AuteurDto | null;


constructor(private auteurService: AuteurService,  private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.auteurs$ = this.auteurService.findAll();

    this.nameCtrl = new FormControl('', Validators.required);
this.preCtrl = new FormControl();
this.natCtrl = new FormControl();

    this.auteurForm = this.formBuilder.group({
     nom : this.nameCtrl,
     prenom : this.preCtrl,
     nationalite : this.natCtrl
    });

  }

    public ajoutermodifierAuteur() {
      
       if (this.editingAuteur) {
            this.auteurService.save(new AuteurDto(this.editingAuteur.id, this.nameCtrl.value,this.preCtrl.value,this.natCtrl.value ));
          }
        
          else {
            this.auteurService.save(new AuteurDto(0, this.nameCtrl.value,this.preCtrl.value ?? "",this.natCtrl.value ?? ""));

          }
      
          this.editingAuteur = null;
          this.nameCtrl.reset();
           this.preCtrl.reset();
            this.natCtrl.reset();

        
    }
  
  
  
    public editAuteur(auteur: AuteurDto): void {
      // Clone du TODO pour l'édition
      this.editingAuteur = auteur;
     this.nameCtrl.setValue(auteur.nom);
     this.preCtrl.setValue(auteur.prenom);
     this.natCtrl.setValue(auteur.nationalite);
    }
  
    public deleteAuteur(auteur: AuteurDto): void {
      this.auteurService.deleteById(auteur.id);
    }

  public trackAuteur(index: number, value: AuteurDto) {
    return value.id;
  }

}
