import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoTargetObjectComponent } from './info-target-object.component';

describe('InfoTargetObjectComponent', () => {
  let component: InfoTargetObjectComponent;
  let fixture: ComponentFixture<InfoTargetObjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoTargetObjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoTargetObjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
