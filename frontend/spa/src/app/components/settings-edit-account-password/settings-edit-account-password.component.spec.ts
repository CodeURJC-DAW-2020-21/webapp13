import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountPasswordComponent } from './settings-edit-account-password.component';

describe('SettingsEditAccountPasswordComponent', () => {
  let component: SettingsEditAccountPasswordComponent;
  let fixture: ComponentFixture<SettingsEditAccountPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
