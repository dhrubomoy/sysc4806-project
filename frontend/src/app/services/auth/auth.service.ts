import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
 
import { JwtResponse } from './jwt-response';
import { SignInInfo } from './sign-in-info';
import { SignUpInfo } from './sign-up-info';
import { CONSTANTS } from '../../utils/constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private signInUrl = CONSTANTS.AUTH_URL.SIGN_IN;
  private signUpUrl = CONSTANTS.AUTH_URL.SIGN_UP;
 
  constructor(private http: HttpClient) {}
 
  signIn(credentials: SignInInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.signInUrl, credentials, httpOptions);
  }
 
  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signUpUrl, info, httpOptions);
  }
}
