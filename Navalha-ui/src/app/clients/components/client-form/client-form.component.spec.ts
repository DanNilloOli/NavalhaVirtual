import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentsClientFormComponent } from './client-form.component';

describe('ComponentsClientFormComponent', () => {
  let component: ComponentsClientFormComponent;
  let fixture: ComponentFixture<ComponentsClientFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComponentsClientFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComponentsClientFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
