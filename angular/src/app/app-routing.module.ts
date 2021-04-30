import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from '../app/components/index/index.component'
import { SearchComponent } from '../app/components/search/search.component'
import { SettingsEditAccountComponent } from '../app/components/settings-edit-account/settings-edit-account.component'
import { RegisterComponent } from '../app/components/register/register.component'
import { ChatComponent } from '../app/components/chat/chat.component';
import { ShopComponent } from '../app/components/shop/shop.component'
import { AdminComponent } from '../app/components/admin/admin.component';
import { AdminAppGraphicsComponent } from "../app/components/admin-app-graphics/admin-app-graphics.component";
import { AdminTemplatesListComponent } from "../app/components/admin-templates-list/admin-templates-list.component";
import { SettingsEditAccountEditPortfolioitemComponent } from '../app/components/settings-edit-account-edit-portfolioitem/settings-edit-account-edit-portfolioitem.component';
import { SettingsEditAccountMytemplatesComponent } from '../app/components/settings-edit-account-mytemplates/settings-edit-account-mytemplates.component';
import { SettingsEditAccountPanelComponent } from '../app/components/settings-edit-account-panel/settings-edit-account-panel.component';
import { SettingsEditAccountPasswordComponent } from '../app/components/settings-edit-account-password/settings-edit-account-password.component';
import { SettingsEditAccountPortfolioitemsComponent } from '../app/components/settings-edit-account-portfolioitems/settings-edit-account-portfolioitems.component';
import { LoginComponent } from '../app/components/login/login.component';
import { AuthGuardService as AuthGuard } from '../app/services/auth-guard.service';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'search', component: SearchComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'admin', component: AdminComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'admin/app/graphics', component: AdminAppGraphicsComponent},
  { path: 'admin/templates/list', component: AdminTemplatesListComponent},
  { path: 'settings-edit-account', component: SettingsEditAccountComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'settings-edit-account-edit-portfolioitem/:id', component: SettingsEditAccountEditPortfolioitemComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'settings-edit-account-mytemplates', component: SettingsEditAccountMytemplatesComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'settings-edit-account-panel', component: SettingsEditAccountPanelComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'settings-edit-account-password', component: SettingsEditAccountPasswordComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'settings-edit-account-portfolioitems', component: SettingsEditAccountPortfolioitemsComponent, 
    canActivate: [AuthGuard],
    data: {
      role: 'USER'
    } },
  { path: 'login', component: LoginComponent},
  { path: 'shop', component: ShopComponent},
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

