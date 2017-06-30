import { Component, OnInit } from '@angular/core';
import {AuthService} from "../service/auth.service";
import {NgForm} from "@angular/forms";
import {User} from "../model/user.mode";
import {Response} from "@angular/http";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [AuthService]
})
export class SignupComponent implements OnInit {

  success = false;
  error = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm){
    const value = form.value;
    const user = new User(value.username, value.password, value.email);
    this.authService.createUser(user)
      .subscribe(
      (response: Response) => {
        if(response.status === 200){
          this.success = true;
          this.error = false;
        }
      },
      (err) => {
        this.success = false;
        this.error = true;
      }
    )
  }

}
