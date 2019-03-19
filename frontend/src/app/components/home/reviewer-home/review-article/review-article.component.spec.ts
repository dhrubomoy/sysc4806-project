import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewArticleComponent } from './review-article.component';

describe('EditArticleComponent', () => {
  let component: ReviewArticleComponent;
  let fixture: ComponentFixture<ReviewArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
