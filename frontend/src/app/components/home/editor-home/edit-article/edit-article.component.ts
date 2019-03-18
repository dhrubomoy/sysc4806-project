import { Component, OnInit, Input } from '@angular/core';
import { Article, Reviewer, ReviewerInfo } from 'src/app/services/types';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.scss']
})
export class EditArticleComponent implements OnInit {

  @Input() article: Article;
  @Input() allArticles: Article;
  @Input() reviewers: Reviewer[];

  currentReviewerId: number;
  reviewDeadline: Date;
  reviewerInputError: String;
  reviewDeadlineError: String;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    if(this.article.reviewer) {
      this.currentReviewerId = this.article.reviewer.id;
    }
  }

  handleErrors() {
    if(!this.currentReviewerId) {
      this.reviewerInputError = 'You must choose a reviewer';
      return;
    } else {
      this.reviewerInputError = '';
    }
    if(!this.reviewDeadline) {
      this.reviewDeadlineError = 'You must set a deadline';
      return;
    } else {
      this.reviewDeadlineError = '';
    }
    this.reviewerInputError = '';
    this.reviewDeadlineError = '';
  }

  updateArticle() {
    console.log(this.currentReviewerId, this.reviewDeadline);
    this.handleErrors();
    console.log('works');
    let date = this.reviewDeadline.getDate()+'/'+ this.reviewDeadline.getMonth()+1 +'/'
      + this.reviewDeadline.getFullYear();
    let reviewerInfo = new ReviewerInfo(this.currentReviewerId, date)
    this.articleService.setReviewerForArticle(reviewerInfo, this.article.id).subscribe(
      data => {
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

}
