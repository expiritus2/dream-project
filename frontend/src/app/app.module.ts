import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Http, HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {AdminComponent} from "./admin/admin.component";
import {AppRoutingModule} from "./app-routing.module";
import {HomeComponent} from "./home/home.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {LoginComponent} from "./auth/login/login.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {PersonalAreaComponent} from "./personal-area/personal-area.component";
import { AccessDeniedComponent } from './auth/access-denied/access-denied.component';
import {TranslateLoader, TranslateModule, TranslateStaticLoader} from "ng2-translate";
import { LocaleComponent } from './locale/locale.component';

export function createTranslateLoader(http: Http) {
  return new TranslateStaticLoader(http, './assets/i18n', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    HomeComponent,
    NotFoundComponent,
    LoginComponent,
    SignupComponent,
    PersonalAreaComponent,
    AccessDeniedComponent,
    LocaleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      provide: TranslateLoader,
      useFactory: (createTranslateLoader),
      deps: [Http]
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
