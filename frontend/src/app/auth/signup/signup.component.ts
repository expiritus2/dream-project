import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {FormGroup, FormBuilder, Validators, NgForm} from "@angular/forms";
import {User} from "../model/user.mode";
import {Response} from "@angular/http";
import {repeat} from "rxjs/operator/repeat";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [AuthService]
})
export class SignupComponent implements OnInit {
  @ViewChild('f') signupForm: NgForm;
  password: string;
  passMatch: boolean;

  success = false;
  error = false;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  matchPasswords(event: any){
    this.passMatch = event.target.value == this.password;
  }



  onSubmit(){
    const value = this.signupForm.value;
    const user = new User(value.firstName, value.lastName, value.email, value.password);
    this.authService.createUser(user)
      .subscribe(
      (response: Response) => {
        if(response.status === 200){
          this.success = true;
          this.error = false;
          this.signupForm.reset();
        }
      },
      (err) => {
        console.info(err);
        this.success = false;
        this.error = true;
      }
    )
  }

}
