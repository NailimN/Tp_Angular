import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LivreDetailPage } from './livre-detail-page';

describe('LivreDetailPage', () => {
  let component: LivreDetailPage;
  let fixture: ComponentFixture<LivreDetailPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LivreDetailPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LivreDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
