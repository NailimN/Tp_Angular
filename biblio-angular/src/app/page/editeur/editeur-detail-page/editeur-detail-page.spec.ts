import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditeurDetailPage } from './editeur-detail-page';

describe('EditeurDetailPage', () => {
  let component: EditeurDetailPage;
  let fixture: ComponentFixture<EditeurDetailPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditeurDetailPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditeurDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
