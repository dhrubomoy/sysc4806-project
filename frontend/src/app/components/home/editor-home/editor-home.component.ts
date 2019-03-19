import { Component, OnInit, TemplateRef } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { Article, Reviewer } from 'src/app/services/types';
import { LocalDataSource } from 'ng2-smart-table';
import { ArticleUtil } from '../../../utils/article-util';
import { NbDialogService } from '@nebular/theme';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'editor-home',
  templateUrl: './editor-home.component.html',
  styleUrls: ['./editor-home.component.scss']
})
export class EditorHomeComponent implements OnInit {

  articlesData: any[] = [];
  private articles: Article[];
  private reviewers: Reviewer[];
  source: LocalDataSource = new LocalDataSource();
  private settings = {
    mode: 'external',
    actions: {
      add: false,
      delete: false,
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      title: {
        title: 'Title',
        type: 'string',
      },
      submitter: {
        title: 'Submitter',
        type: 'string',
      },
      reviewer: {
        title: 'Reviewer',
        type: 'string',
      },
      reviewDeadline: {
        title: 'Review Deadline',
        type: 'string',
        compareFunction:(direction: any, a: any, b: any) => {
          let aTime: number, bTime: number;
          if(typeof a === 'string') {
            if(a === 'Not Set') {
              return direction;
            } else {
              aTime = new Date(a).getTime();
            }
          }
          if(typeof b === 'string') {
            if(b === 'Not Set') {
              return -1*direction;
            } else {
              bTime = new Date(b).getTime();
            }
          }
          if (aTime < bTime) {
            return -1 * direction;
           }
           if (aTime > bTime) {
             return direction;
           }
           return 0;
        }
      },
      status: {
        title: 'Status',
        type: 'string',
        filter: {},
      },
    },
  };

  constructor(
    private articleService: ArticleService,
    private userService: UserService,
    private dialogService: NbDialogService
  ) { }

  ngOnInit() {
    this.updateEditorHome();
  }

  updateEditorHome() {
    this.setAllArticles();
    this.setReviewers();
  }

  dlgClosed(ref: any) {
    ref.close();
    this.updateEditorHome();
  }

  setReviewers() {
    this.userService.getReviewerList().subscribe(
      data => {
        this.reviewers = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  // From 'mm-dd-yyyy' to 'Mon dd, yyyy'
  getProperDateFormat(deadline: string) {
    return ArticleUtil.convertDateToAnotherFormat(deadline, 'mm-dd-yyyy', 'Mon dd, yyyy');
  }

  setAllArticles() {
    this.articleService.getAllArticles().subscribe(
      data => {
        console.log(data);
        this.articles = data;
        this.articlesData = this.articles.map(a => {
          return {
            id: a.id,
            title: a.title,
            text: a.text,
            submitter: a.submitter.username,
            reviewer: a.reviewer && a.reviewer.username? a.reviewer.username : 'Not Set',
            reviewDeadline: a.reviewDeadline? this.getProperDateFormat(a.reviewDeadline) : 'Not Set',
            status: ArticleUtil.getReviewStatus(a.reviewStatus),
          }
        });
        this.source.load(this.articlesData);
        this.settings.columns.status.filter = ArticleUtil.getStatusFilter(this.articlesData);
      },
      error => {
        console.log(error);
      }
    );
  }

  onEditClick(event: any, dialog: TemplateRef<any>) {
    let selectedArticle: Article = this.articles.find(a => a.id===event.data.id);
    console.log('event', event);
    console.log('selectedArticle', selectedArticle);
    this.dialogService.open(dialog, { context: {
      selectedArticle: selectedArticle,
      allArticles: this.articles,
      reviewers: this.reviewers,
    } });
  }

}
