import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingsEditAccountPortfolioitemsComponent } from './settings-edit-account-portfolioitems.component';

describe('SettingsEditAccountPortfolioitemsComponent', () => {
  let component: SettingsEditAccountPortfolioitemsComponent;
  let fixture: ComponentFixture<SettingsEditAccountPortfolioitemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SettingsEditAccountPortfolioitemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SettingsEditAccountPortfolioitemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
