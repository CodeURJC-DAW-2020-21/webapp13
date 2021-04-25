import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountPanelComponent } from './settings-edit-account-panel.component';

describe('SettingsEditAccountPanelComponent', () => {
  let component: SettingsEditAccountPanelComponent;
  let fixture: ComponentFixture<SettingsEditAccountPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
