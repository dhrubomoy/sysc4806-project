import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CONSTANTS } from '../utils/constants'
import { Article } from './types';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(private http: HttpClient) { }
 
  getReviewerList() {
    return this.http.get<Article[]>(CONSTANTS.USER_URL.REVIEWER_LIST);
  }
}
