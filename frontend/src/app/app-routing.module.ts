import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AdminComponent} from "./admin/admin.component";
import {HomeComponent} from "app/home/home.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {LoginComponent} from "./auth/login/login.component";
import {SignupComponent} from "./auth/signup/signup.component";
import {PersonalAreaComponent} from "./personal-area/personal-area.component";
import {AccessDeniedComponent} from "./auth/access-denied/access-denied.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent, pathMatch: 'full'},
  {path: 'admin', component: AdminComponent, children: [

  ]},
  {path: 'personal-area', component: PersonalAreaComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: HomeComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'access-denied', component: AccessDeniedComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
