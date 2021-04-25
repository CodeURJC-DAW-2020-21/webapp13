import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountProfileComponent } from './settings-edit-account-profile.component';

describe('SettingsEditAccountProfileComponent', () => {
  let component: SettingsEditAccountProfileComponent;
  let fixture: ComponentFixture<SettingsEditAccountProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
