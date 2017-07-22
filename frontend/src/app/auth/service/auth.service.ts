import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {User} from "../model/user.model";
import {WebConfig} from "../../webconfig.config";

@Injectable()
export class AuthService {

  constructor(private http: Http) {
  }

  createUser(user: User) {
    return this.http.put(WebConfig.host + "/api/auth/signup", user);
  }

  sendCredential(username: string, password: string){
    const url = WebConfig.host + '/login';
    const params = 'username=' + username + '&password=' + password;
    const headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    return this.http.post(url, params, { headers: headers, withCredentials: true });
  }

  logout(){
    const url = WebConfig.host + '/logout';
    return this.http.get(url, { withCredentials: true });
  }
}
