import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { SignUpInfo } from '../../services/auth/sign-up-info';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
 
  constructor(private authService: AuthService) { }
 
  ngOnInit() {
  }
 
  onSubmit() {
    console.log(this.form);
 
    this.signupInfo = new SignUpInfo(
      this.form.name,
      this.form.username,
      this.form.password,
      this.form.role
    );

    console.log(this.signupInfo);
 
    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log("data: ", data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      },
      error => {
        console.log("error: ", error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
