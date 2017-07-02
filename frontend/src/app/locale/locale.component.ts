import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {TranslateService} from "ng2-translate";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-locale',
  templateUrl: './locale.component.html',
  styleUrls: ['./locale.component.css']
})
export class LocaleComponent implements OnInit {

  private subscription: Subscription;
  selectedLang = "en";

  constructor(private translate: TranslateService, private activatedRoute: ActivatedRoute) {
    translate.addLangs(['en', 'ru']);
    translate.setDefaultLang('en');

    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|ru/) ? browserLang : 'en');

    this.subscription = this.activatedRoute.queryParams.subscribe(
      (param: any) => {
        let locale = param['locale'];
        if(locale !== undefined){
          this.translate.use(locale);
          this.selectedLang = locale;
        }
      }
    );
  }

  onChangeLanguage(event: any){
    let lang = event.target.value;
    this.translate.use(lang);
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
