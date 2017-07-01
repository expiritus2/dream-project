import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {User} from "../model/user.mode";
import 'rxjs/Rx';

@Injectable()
export class AuthService {

  constructor(private http: Http) {
  }

  createUser(user: User) {
    return this.http.put("http://localhost:8080/api/auth/signup", user);
  }
}
