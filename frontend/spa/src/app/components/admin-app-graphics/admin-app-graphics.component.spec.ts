import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAppGraphicsComponent } from './admin-app-graphics.component';

describe('AdminAppGraphicsComponent', () => {
  let component: AdminAppGraphicsComponent;
  let fixture: ComponentFixture<AdminAppGraphicsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAppGraphicsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAppGraphicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
