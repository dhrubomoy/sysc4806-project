import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Article, Reviewer, ReviewerInfo } from 'src/app/services/types';
import { ArticleService } from 'src/app/services/article.service';
import { ArticleUtil } from 'src/app/utils/article-util';

@Component({
  selector: 'review-article',
  templateUrl: './review-article.component.html',
  styleUrls: ['./review-article.component.scss']
})
export class ReviewArticleComponent implements OnInit {

  @Input() article: Article;
  @Input() allArticles: Article;
  @Input() reviewers: Reviewer[];

  @Output() onDlgClose = new EventEmitter();

  reviewString: String;

  constructor(private articleService: ArticleService) { }

  ngOnInit() {
    this.reviewString = this.article.review ? this.article.review : '';
  }

  updateArticle() {
    this.articleService.reviewArticle(this.reviewString, this.article.id).subscribe(
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
