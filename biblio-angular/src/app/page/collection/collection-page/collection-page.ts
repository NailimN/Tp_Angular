import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { CollectionDto } from '../../../dto/collection-dto';
import { CollectionService } from '../../../service/collection-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-collection-page',
  imports: [
    CommonModule,
    RouterLink
    FormsModule
  ],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {
  protected collection: CollectionDto = new CollectionDto(0, "")
  protected collections$!: Observable<CollectionDto[]>;

  constructor(private collectionService: CollectionService) {}

  ngOnInit(): void {
    this.collections$ = this.collectionService.findAll();
  }

    public ajouterCollection() {
      this.collectionService.save(this.collection);
  
      this.collection = new CollectionDto(0, "");
    }
  
    public modifierCollection() {
      this.collectionService.save(this.collection);
  
      this.collection = new CollectionDto(0, "");
    }
  
    public editCollection(collection: CollectionDto): void {
      // Clone du TODO pour l'édition
      this.collection = new CollectionDto(collection.id, collection.nom);
    }
  
    public deleteCollection(collection: CollectionDto): void {
      this.collectionService.deleteById(collection.id);
    }
  


  public trackCollection(index: number, value: CollectionDto) {
    return value.id; // Getter utilisé
  }
}
