import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionDetailPage } from './collection-detail-page';

describe('CollectionDetailPage', () => {
  let component: CollectionDetailPage;
  let fixture: ComponentFixture<CollectionDetailPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CollectionDetailPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CollectionDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
