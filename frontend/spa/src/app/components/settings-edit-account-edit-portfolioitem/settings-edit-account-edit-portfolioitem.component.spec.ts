import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountEditPortfolioitemComponent } from './settings-edit-account-edit-portfolioitem.component';

describe('SettingsEditAccountEditPortfolioitemComponent', () => {
  let component: SettingsEditAccountEditPortfolioitemComponent;
  let fixture: ComponentFixture<SettingsEditAccountEditPortfolioitemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountEditPortfolioitemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountEditPortfolioitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
