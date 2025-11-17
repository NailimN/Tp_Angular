import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { EditeurDto } from '../../../dto/editeur-dto';
import { EditeurService } from '../../../service/editeur-service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-editeur-page',
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  protected editeur: EditeurDto = new EditeurDto(0, "", "");
  protected editeurs$!: Observable<EditeurDto[]>;
  protected editeurForm!: FormGroup;
  protected nomCtrl!: FormControl;
  protected paysCtrl!: FormControl;
  protected editingEditeur!: EditeurDto | null;

  constructor(private editeurService: EditeurService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.editeurs$ = this.editeurService.findAll();

    this.nomCtrl = new FormControl('', Validators.required);
    this.paysCtrl = new FormControl('', Validators.required);

    this.editeurForm = this.formBuilder.group({
      nom: this.nomCtrl,
      pays: this.paysCtrl
    });
  }

  public ajouterModifierEditeur() {

    if (this.editingEditeur) {
      this.editeurService.save(new EditeurDto(this.editingEditeur.id, this.nomCtrl.value, this.paysCtrl.value));
    }

    else {
      this.editeurService.save(new EditeurDto(0, this.nomCtrl.value, this.paysCtrl.value));
    }

    this.editingEditeur = null;
    this.nomCtrl.reset();
    this.paysCtrl.reset();
  }

  public editEditeur(editeur: EditeurDto): void {
    // Clone du TODO pour l'édition
    this.editingEditeur = editeur;
    this.nomCtrl.setValue(editeur.nom);
    this.paysCtrl.setValue(editeur.pays);
  }

  public deleteEditeur(editeur: EditeurDto): void {
    this.editeurService.deleteById(editeur.id);
  }

  public trackEditeur(index: number, value: EditeurDto) {
    return value.id;
  }
}
