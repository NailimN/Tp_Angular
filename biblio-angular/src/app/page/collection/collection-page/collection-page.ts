import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { CollectionDto } from '../../../dto/collection-dto';
import { CollectionService } from '../../../service/collection-service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-collection-page',
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {
  protected collection: CollectionDto = new CollectionDto(0, "")
  protected collections$!: Observable<CollectionDto[]>;
    protected collectionForm!: FormGroup;
    protected nomCtrl!: FormControl;
    protected editingCollection!: CollectionDto | null;

  constructor(private collectionService: CollectionService, private formBuilder: FormBuilder ) {}

  ngOnInit(): void {
    this.collections$ = this.collectionService.findAll();
        this.nomCtrl = new FormControl('', Validators.required);
    
        
    this.collectionForm = this.formBuilder.group({
      nom: this.nomCtrl,
    });
  }
 public ajouterModifierCollection() {

    if (this.editingCollection) {
      this.collectionService.save(new CollectionDto(this.editingCollection.id, this.nomCtrl.value));
    }

    else {
      this.collectionService.save(new CollectionDto(0, this.nomCtrl.value));
    }

    this.editingCollection = null;
    this.nomCtrl.reset();
  }
   
    public editCollection(collection: CollectionDto): void {
      // Clone du TODO pour l'édition
       this.editingCollection = collection;
    this.nomCtrl.setValue(collection.nom);
    }
  
    public deleteCollection(collection: CollectionDto): void {
      this.collectionService.deleteById(collection.id);
    }
  


  public trackCollection(index: number, value: CollectionDto) {
    return value.id; // Getter utilisé
  }
}
