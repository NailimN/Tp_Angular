import { Component, OnInit } from '@angular/core';
import { CollectionDto } from '../../../dto/collection-dto';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CollectionService } from '../../../service/collection-service';

@Component({
  selector: 'app-collection-detail-page',
  imports: [],
  templateUrl: './collection-detail-page.html',
  styleUrl: './collection-detail-page.css',
})
export class CollectionDetailPage implements OnInit{
  protected collection! : CollectionDto | null;
  
    constructor(private route: ActivatedRoute, private title: Title, private collectionService: CollectionService) { }
  
    ngOnInit(): void {
      this.route.params.subscribe((params: any) => {
        this.collectionService.findById(params.id).subscribe(collection => {
          this.collection = collection;
  
          if (this.collection) {
            this.title.setTitle("Détail du Collection #" + this.collection.id);
          }
  
          else {
            this.title.setTitle("Collection inconnu");
          }
        });
      });
    }
}
