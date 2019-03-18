import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/auth/token-storage.service';
import { NB_WINDOW, NbMenuService } from '@nebular/theme';
import { filter, map } from 'rxjs/operators';
import { Router } from '@angular/router';

const HOME = 'Home';
const SIGNOUT = 'Sign Out'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  
  isSignedIn: boolean;
  router: Router;
  private roles: string[];
  private authority: string;
  private items: any = [
    { title: HOME },
    { title: SIGNOUT },
  ];
 
  constructor(
    private token: TokenStorageService,
    private nbMenuService: NbMenuService, 
    private router_: Router
  ) {
    this.isSignedIn = false;
    this.router = router_;
  }

  menuItemClicked() {
    this.nbMenuService.onItemClick().pipe(
      filter(({ tag }) => tag === 'my-context-menu'),
      map(({ item: { title } }) => title),
    ).subscribe(title => {
      if(title === HOME) {
        this.router.navigateByUrl('/home');
      } else if(title === SIGNOUT) {
        this.token.signOut();
        window.location.reload();
      }
    });
  }

  getAuthorities() {
    if (this.token.getToken()) {
      this.isSignedIn = true;
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_EDITOR') {
          this.authority = 'Editor';
          return false;
        } else if (role === 'ROLE_REVIEWER') {
          this.authority = 'Reviewer';
          return false;
        } else if (role === 'ROLE_SUBMITTER') {
          this.authority = 'Submitter';
          return false;
        }
        return true;
      });
    }
  }
 
  ngOnInit() {
    this.menuItemClicked();
    this.getAuthorities();
  }
}
