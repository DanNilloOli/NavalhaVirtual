import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentsClientTableComponent } from './client-table.component';

describe('ComponentsClientTableComponent', () => {
  let component: ComponentsClientTableComponent;
  let fixture: ComponentFixture<ComponentsClientTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComponentsClientTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComponentsClientTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
