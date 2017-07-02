import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    const currentUrl = window.location.href;
    if (currentUrl.indexOf("logout") != -1) {
      localStorage.clear();
    }
  }
}
