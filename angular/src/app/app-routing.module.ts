import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from './index/index.component'
import { SearchComponent } from './search/search.component'
import { SettingsEditAccountComponent } from './settings-edit-account/settings-edit-account.component'
import { RegisterComponent } from './register/register.component'
import { ChatComponent } from './chat/chat.component';
import { ShopComponent } from './shop/shop.component'
import { AdminComponent } from "./admin/admin.component";
import { AdminAppGraphicsComponent } from "./admin-app-graphics/admin-app-graphics.component";
import { AdminTemplatesListComponent } from "./admin-templates-list/admin-templates-list.component";
import { SettingsEditAccountEditPortfolioitemComponent } from './settings-edit-account-edit-portfolioitem/settings-edit-account-edit-portfolioitem.component';
import { SettingsEditAccountMytemplatesComponent } from './settings-edit-account-mytemplates/settings-edit-account-mytemplates.component';
import { SettingsEditAccountPanelComponent } from './settings-edit-account-panel/settings-edit-account-panel.component';
import { SettingsEditAccountPasswordComponent } from './settings-edit-account-password/settings-edit-account-password.component';
import { SettingsEditAccountPortfolioitemsComponent } from './settings-edit-account-portfolioitems/settings-edit-account-portfolioitems.component';
import { SettingsEditAccountProfileComponent } from './settings-edit-account-profile/settings-edit-account-profile.component';
import { LoginComponent } from './login/login.component';
import { LoginErrorComponent } from './login-error/login-error.component';
import { LogoutConfirmationComponent } from './logout-confirmation/logout-confirmation.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'search', component: SearchComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/appGraphics', component: AdminAppGraphicsComponent},
  { path: 'admin/templatesList', component: AdminTemplatesListComponent},
  { path: 'settings-edit-account', component: SettingsEditAccountComponent},
  { path: 'settings-edit-account-edit-portfolioitem', component: SettingsEditAccountEditPortfolioitemComponent},
  { path: 'settings-edit-account-mytemplates', component: SettingsEditAccountMytemplatesComponent},
  { path: 'settings-edit-account-panel', component: SettingsEditAccountPanelComponent},
  { path: 'settings-edit-account-password', component: SettingsEditAccountPasswordComponent},
  { path: 'settings-edit-account-portfolioitems', component: SettingsEditAccountPortfolioitemsComponent},
  { path: 'settings-edit-account-profile', component: SettingsEditAccountProfileComponent},
  { path: 'login', component: LoginComponent},
  { path: 'login-error', component: LoginErrorComponent},
  { path: 'logout-confirmation', component: LogoutConfirmationComponent},
  { path: 'shop', component: ShopComponent},

];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

