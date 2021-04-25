import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePasswordErrorComponent } from './change-password-error.component';

describe('ChangePasswordErrorComponent', () => {
  let component: ChangePasswordErrorComponent;
  let fixture: ComponentFixture<ChangePasswordErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangePasswordErrorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePasswordErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
