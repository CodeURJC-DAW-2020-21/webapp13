import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTemplatesListComponent } from './admin-templates-list.component';

describe('AdminTemplatesListComponent', () => {
  let component: AdminTemplatesListComponent;
  let fixture: ComponentFixture<AdminTemplatesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminTemplatesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTemplatesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
