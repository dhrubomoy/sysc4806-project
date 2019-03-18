import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../../services/auth/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {
  info: any;
 
  constructor(private token: TokenStorageService) { }
 
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }
 
  signOut() {
    this.token.signOut();
    window.location.reload();
  }
}
