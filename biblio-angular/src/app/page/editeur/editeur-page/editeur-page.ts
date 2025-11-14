import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { EditeurDto } from '../../../dto/editeur-dto';
import { EditeurService } from '../../../service/editeur-service';

@Component({
  selector: 'app-editeur-page',
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit{
  protected editeurs$!: Observable<EditeurDto[]>;

  constructor(private editeurService: EditeurService) { }

  ngOnInit(): void {
    this.editeurs$ = this.editeurService.findAll();
  }

  public trackEditeur(index: number, value: EditeurDto) {
    return value.id;
  }
}
