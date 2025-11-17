import { Component, OnInit } from '@angular/core';
import { EditeurDto } from '../../../dto/editeur-dto';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { EditeurService } from '../../../service/editeur-service';

@Component({
  selector: 'app-editeur-detail-page',
  imports: [],
  templateUrl: './editeur-detail-page.html',
  styleUrl: './editeur-detail-page.css',
})
export class EditeurDetailPage implements OnInit {
  protected editeur! : EditeurDto | null;

  constructor(private route: ActivatedRoute, private title: Title, private editeurService: EditeurService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      this.editeurService.findById(params.id).subscribe(editeur => {
        this.editeur = editeur;

        if (this.editeur) {
          this.title.setTitle("Détail du Éditeur #" + this.editeur.id);
        }

        else {
          this.title.setTitle("Éditeur inconnu");
        }
      });
    });
  }
}
