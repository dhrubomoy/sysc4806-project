import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitterHomeComponent } from './submitter-home.component';

describe('SubmitterHomeComponent', () => {
  let component: SubmitterHomeComponent;
  let fixture: ComponentFixture<SubmitterHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmitterHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitterHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
