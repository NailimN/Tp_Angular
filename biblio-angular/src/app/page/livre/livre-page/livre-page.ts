import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LivreDto } from '../../../dto/livre-dto';
import { LivreService } from '../../../service/livre-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-livre-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage {
  public livres$!: Observable<LivreDto[]>;

  constructor(private livreService: LivreService) {}

  ngOnInit(): void {
    this.livres$ = this.livreService.findAll();
  }

  public trackLivre(index: number, value: LivreDto) {
    return value.id;
  }

  public deleteById(id: number): void {
    this.livreService.deleteById(id);
  }

   public updateById(id: number): void {
    
  }
}
