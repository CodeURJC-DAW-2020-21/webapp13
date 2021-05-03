import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioItemComponent } from './portfolio-item.component';

describe('PortfolioItemComponent', () => {
  let component: PortfolioItemComponent;
  let fixture: ComponentFixture<PortfolioItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortfolioItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortfolioItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
