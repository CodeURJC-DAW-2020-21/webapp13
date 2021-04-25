import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountMytemplatesComponent } from './settings-edit-account-mytemplates.component';

describe('SettingsEditAccountMytemplatesComponent', () => {
  let component: SettingsEditAccountMytemplatesComponent;
  let fixture: ComponentFixture<SettingsEditAccountMytemplatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountMytemplatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountMytemplatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
