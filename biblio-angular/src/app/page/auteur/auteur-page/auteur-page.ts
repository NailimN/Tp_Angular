import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { AuteurDto } from '../../../dto/auteur-dto';
import { AuteurService } from '../../../service/auteur-service';

@Component({
  selector: 'app-auteur-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage implements OnInit {
protected auteurs$!: Observable<AuteurDto[]>;

constructor(private auteurService: AuteurService) { }

  ngOnInit(): void {
    this.auteurs$ = this.auteurService.findAll();
  }

  public trackAuteur(index: number, value: AuteurDto) {
    return value.id;
  }

}
