import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AreaSelectComponent } from './area-select.component';

describe('AreaSelectComponent', () => {
  let component: AreaSelectComponent;
  let fixture: ComponentFixture<AreaSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AreaSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AreaSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
