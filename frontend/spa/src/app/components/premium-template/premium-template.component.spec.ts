import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PremiumTemplateComponent } from './premium-template.component';

describe('PremiumTemplateComponent', () => {
  let component: PremiumTemplateComponent;
  let fixture: ComponentFixture<PremiumTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PremiumTemplateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PremiumTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
