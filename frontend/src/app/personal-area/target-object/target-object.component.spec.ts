import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TargetObjectComponent } from './target-object.component';

describe('TargetObjectComponent', () => {
  let component: TargetObjectComponent;
  let fixture: ComponentFixture<TargetObjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TargetObjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TargetObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
