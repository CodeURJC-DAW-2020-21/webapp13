import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FreeTemplateComponent } from './free-template.component';

describe('FreeTemplateComponent', () => {
  let component: FreeTemplateComponent;
  let fixture: ComponentFixture<FreeTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FreeTemplateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FreeTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
