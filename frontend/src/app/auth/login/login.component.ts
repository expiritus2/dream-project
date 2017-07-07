import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {NgForm} from "@angular/forms";
import {Response} from "@angular/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthService]
})
export class LoginComponent implements OnInit {
  @ViewChild("f") loginForm: NgForm;
  loginError = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(){
    const value = this.loginForm.value;
    this.authService.sendCredential(value.username, value.password).subscribe(
      (response: Response) => {
        if(response.status === 200){
          console.info(response);
          localStorage.setItem("userName", response.text());
          this.router.navigate(["/personal-area"]);
        }
      },
      (err) => {
        console.log(err);
        this.loginError = true;
      }
    );
  }
}
