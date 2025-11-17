import { Component, OnInit } from '@angular/core';
import { AuteurDto } from '../../../dto/auteur-dto';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { EditeurService } from '../../../service/editeur-service';
import { AuteurService } from '../../../service/auteur-service';

@Component({
  selector: 'app-auteur-detail',
  imports: [],
  templateUrl: './auteur-detail.html',
  styleUrl: './auteur-detail.css',
})
export class AuteurDetail implements OnInit{

 protected auteur! : AuteurDto | null;

  constructor(private route: ActivatedRoute, private title: Title, private auteurService: AuteurService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: any) => {
      this.auteurService.findById(params.id).subscribe(auteur => {
        this.auteur = auteur;


           if (this.auteur) {
          this.title.setTitle("Détail de l'Auteur #" + this.auteur.id);
        }

        else {
          this.title.setTitle("Auteur inconnu");
        }

     });
    });
  }
}
