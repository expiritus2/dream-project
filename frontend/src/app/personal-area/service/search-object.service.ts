import {Injectable, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {WebConfig} from "../../webconfig.config";

@Injectable()
export class SearchObjectService implements OnInit {

  constructor(private http: Http) {
  }

  search(marker) {
    return this.http.post(WebConfig.host + "/api/target/search", JSON.stringify(marker), {withCredentials: true});
  }

  ngOnInit(): void {
  }

}
