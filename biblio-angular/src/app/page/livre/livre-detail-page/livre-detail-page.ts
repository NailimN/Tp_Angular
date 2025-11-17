import { Component, OnInit } from '@angular/core';
import { LivreService } from '../../../service/livre-service';
import { ActivatedRoute } from '@angular/router';
import { LivreDto } from '../../../dto/livre-dto';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-livre-detail-page',
  imports: [],
  templateUrl: './livre-detail-page.html',
  styleUrl: './livre-detail-page.css',
})
export class LivreDetailPage implements OnInit  {
  protected livre! : LivreDto;
 constructor(private route: ActivatedRoute, private title: Title, private livreService: LivreService) { }
  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      this.livreService.findById(params.id).subscribe(livre => {
        this.livre = livre;
          this.title.setTitle("Détail du Livre #" + this.livre.id);
      });
    });
  }
}
