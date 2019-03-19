import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Article, Reviewer, ReviewerInfo } from 'src/app/services/types';
import { ArticleService } from 'src/app/services/article.service';
import { ArticleUtil } from 'src/app/utils/article-util';

@Component({
  selector: 'edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.scss']
})
export class EditArticleComponent implements OnInit {

  @Input() article: Article;
  @Input() allArticles: Article;
  @Input() reviewers: Reviewer[];

  @Output() onDlgClose = new EventEmitter();

  currentReviewerUsername: String;
  reviewDeadline: Date;
  reviewerInputError: String;
  reviewDeadlineError: String;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    if(this.article.reviewer) {
      this.currentReviewerUsername = this.article.reviewer.username;
    }
    if(this.article.reviewDeadline) {
      let dateStr = ArticleUtil.convertDateToAnotherFormat(this.article.reviewDeadline, 'mm-dd-yyyy', 'Mon dd, yyyy')
      this.reviewDeadline = new Date(dateStr);
    }
  }

  handleErrors() {
    if(!this.currentReviewerUsername) {
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
    this.handleErrors();
    let date = ArticleUtil.getDateInFormat(this.reviewDeadline, 'dd/mm/yyyy');
    let currentReviewerId = this.reviewers.find(r => r.username === this.currentReviewerUsername).id;
    let reviewerInfo = new ReviewerInfo(currentReviewerId, date);
    this.articleService.setReviewerForArticle(reviewerInfo, this.article.id).subscribe(
      data => {
        console.log(data);
        this.onDlgClose.emit();
      },
      error => {
        console.log(error);
      }
    );
  }

}
