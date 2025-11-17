import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuteurDetail } from './auteur-detail';

describe('AuteurDetail', () => {
  let component: AuteurDetail;
  let fixture: ComponentFixture<AuteurDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuteurDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuteurDetail);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
