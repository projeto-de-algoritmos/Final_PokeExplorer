import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortestPathComponent } from './shortest-path.component';

describe('ShortestPathComponent', () => {
  let component: ShortestPathComponent;
  let fixture: ComponentFixture<ShortestPathComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShortestPathComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShortestPathComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
