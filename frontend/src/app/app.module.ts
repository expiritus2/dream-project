import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Http, HttpModule} from "@angular/http";
import {AgmCoreModule} from "@agm/core";
import {AppComponent} from "./app.component";
import {AdminComponent} from "./admin/admin.component";
import {AppRoutingModule} from "./app-routing.module";
import {HomeComponent} from "./home/home.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {LoginComponent} from "./auth/login/login.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {PersonalAreaComponent} from "./personal-area/personal-area.component";
import {AccessDeniedComponent} from "./auth/access-denied/access-denied.component";
import {TranslateLoader, TranslateModule, TranslateStaticLoader} from "ng2-translate";
import {LocaleComponent} from "./header/locale/locale.component";
import {HeaderComponent} from "./header/header.component";
import {FooterComponent} from "./footer/footer.component";
import {GoogleMapComponent} from "./personal-area/google-map/google-map.component";

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
    LocaleComponent,
    HeaderComponent,
    FooterComponent,
    GoogleMapComponent
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
    }),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAnMTHGnLJvyQJDXW8t-OVA2hZ2qDflHrI'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
