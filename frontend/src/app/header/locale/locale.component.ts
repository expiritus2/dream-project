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
  listLangs: string[];
  selectedLang = "en";

  constructor(private translate: TranslateService, private activatedRoute: ActivatedRoute) {
    translate.addLangs(['en', 'ru']);
    translate.setDefaultLang('en');
    this.listLangs = translate.getLangs();

    const browserLang = translate.getBrowserLang();
    let preferLanguage = localStorage.getItem("language");
    if(preferLanguage != null){
      translate.use(preferLanguage);
      this.selectedLang = preferLanguage;
    } else {
      let defaultLang = browserLang.match(/en|ru/) ? browserLang : 'en';
      translate.use(defaultLang);
      this.selectedLang = defaultLang;
    }


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
    localStorage.setItem("language", lang);
  }

  ngOnInit() {
    localStorage.setItem("language", this.selectedLang);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
