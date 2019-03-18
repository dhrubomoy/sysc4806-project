import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CONSTANTS } from '../utils/constants';
import { Article, ReviewerInfo } from './types';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private submitterAddArticleUrl = CONSTANTS.ARTICLE_URL.SUBMITTER_CREATE_ARTICLE;
  private getSubmittedArticlesUrl = CONSTANTS.ARTICLE_URL.SUBMITTER_LIST_ARTICLE;
  private getAllArticlesUrl = CONSTANTS.ARTICLE_URL.ALL_ARTICLE;
  private updateReviewerForArticle = CONSTANTS.ARTICLE_URL.SET_REVIEWER_FOR_ARTICLE;
 
  constructor(private http: HttpClient) {}
 
  createArticleForSubmitter(article: Article): Observable<Article> {
    return this.http.post<Article>(this.submitterAddArticleUrl, article);
  }

  getArticlesForSubmitter(): Observable<Article[]> {
    return this.http.get<Article[]>(this.getSubmittedArticlesUrl);
  }

  getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.getAllArticlesUrl);
  }

  setReviewerForArticle(reviewerInfo: ReviewerInfo, articleId: number): Observable<Article> {
    let regex = /{id}/gi;
    let url: string = this.updateReviewerForArticle.replace(regex, articleId.toString())
    console.log(url);
    return this.http.put<Article>(url, reviewerInfo);
  }
}
