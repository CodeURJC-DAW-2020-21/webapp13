import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeActiveTemplateConfirmationComponent } from './change-active-template-confirmation.component';

describe('ChangeActiveTemplateConfirmationComponent', () => {
  let component: ChangeActiveTemplateConfirmationComponent;
  let fixture: ComponentFixture<ChangeActiveTemplateConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeActiveTemplateConfirmationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeActiveTemplateConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
