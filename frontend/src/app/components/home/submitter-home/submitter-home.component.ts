import { Component, OnInit, TemplateRef } from '@angular/core';
import { NbDialogService } from '@nebular/theme';
import { ArticleService } from '../../../services/article.service';
import { Article } from '../../../services/types';
import { LocalDataSource } from 'ng2-smart-table';
import { ArticleUtil } from '../../../utils/article-util';
import { ViewDetailsComponent } from './view-details/view-details.component'

@Component({
  selector: 'submitter-home',
  templateUrl: './submitter-home.component.html',
  styleUrls: ['./submitter-home.component.scss']
})
export class SubmitterHomeComponent implements OnInit {

  submittedArticles = [];
  submitOptions = [
    { value: 'text', label: 'Text' },
    { value: 'attachment', label: 'Attachment' },
  ];
  submitOption: string = this.submitOptions[0].value;
  private articleTitle: string = '';
  private articleText: string = '';
  private dialogRef: any;
  private settings: any;

  source: LocalDataSource = new LocalDataSource();

  constructor(
    private dialogService: NbDialogService,
    private articleService: ArticleService
  ) {}

  ngOnInit() {
    this.setSubmittedArticles();
    this.settings = {
      actions: {
        add: false,
        edit: false,
        delete: false,
      },
      columns: {
        viewDetails: {
          title: 'Details',
          filter: false,
          type: 'custom',
          renderComponent: ViewDetailsComponent,
        },
        id: {
          title: 'ID',
          type: 'number',
        },
        title: {
          title: 'Title',
          type: 'string',
        },
        status: {
          title: 'Status',
          type: 'string',
          filter: {},
        },
      },
    };
  }

  onFileChange(event: any) {
    let file = event.target.files[0];
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.articleText = event.target.result;
    }
    reader.readAsText(file);
  }

  setSubmittedArticles() {
    this.articleService.getArticlesForSubmitter().subscribe(
      data => {
        let articles: Article[] = data;
        this.submittedArticles = articles.map(a => {
          return {
            viewDetails: a,
            id: a.id,
            title: a.title,
            status: ArticleUtil.getReviewStatus(a.reviewStatus)
          }
        });
        this.source.load(this.submittedArticles);
        this.settings.columns.status.filter = ArticleUtil.getStatusFilter(this.submittedArticles);
      },
      error => {
        console.log(error);
      }
    );
  }

  open(dialog: TemplateRef<any>) {
    this.dialogRef = this.dialogService.open(dialog, { context: '' });
  }

  addArticle() {
    let article: Article = {
      title: this.articleTitle,
      text: this.articleText
    }
    this.articleService.createArticleForSubmitter(article).subscribe(
      data => {
        let article: Article = data;
        console.log(data);
        this.submittedArticles.push({
          viewDetails: article,
          id: article.id,
          title: article.title,
          status: ArticleUtil.getReviewStatus(article.reviewStatus)
        });
        this.source.load(this.submittedArticles);
        this.settings.columns.status.filter = ArticleUtil.getStatusFilter(this.submittedArticles);
        this.dialogRef.close();
      },
      error => {
        console.log(error);
      }
    );
  }

}
