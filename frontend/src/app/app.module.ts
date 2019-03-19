import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ThemeModule } from './theme.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { HomeComponent } from './components/home/home.component';
import { SubmitterHomeComponent } from './components/home/submitter-home/submitter-home.component';

import { httpInterceptorProviders } from './services/auth/auth-interceptor';
import { EditorHomeComponent } from './components/home/editor-home/editor-home.component';
import { ReviewViewComponent } from './components/home/editor-home/review-view/review-view-component';
import { EditArticleComponent } from './components/home/editor-home/edit-article/edit-article.component';
import { ReviewerHomeComponent } from './components/home/reviewer-home/reviewer-home.component';
import { ReviewArticleComponent } from './components/home/reviewer-home/review-article/review-article.component';

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    SignUpComponent,
    HomeComponent,
    SubmitterHomeComponent,
    EditorHomeComponent,
    EditArticleComponent,
    ReviewerHomeComponent,
    ReviewArticleComponent,
    ReviewViewComponent,
  ],
  entryComponents: [
    ReviewViewComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ThemeModule.forRoot(),
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
