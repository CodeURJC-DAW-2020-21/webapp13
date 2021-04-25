import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { IndexComponent } from './index/index.component';
import { AdminComponent } from './admin/admin.component';
import { AdminTemplatesListComponent } from './admin-templates-list/admin-templates-list.component';
import { AdminAppGraphicsComponent } from './admin-app-graphics/admin-app-graphics.component';
import { SettingsEditAccountComponent } from './settings-edit-account/settings-edit-account.component';
import { SettingsEditAccountEditPortfolioitemComponent } from './settings-edit-account-edit-portfolioitem/settings-edit-account-edit-portfolioitem.component';
import { SettingsEditAccountMytemplatesComponent } from './settings-edit-account-mytemplates/settings-edit-account-mytemplates.component';
import { SettingsEditAccountPanelComponent } from './settings-edit-account-panel/settings-edit-account-panel.component';
import { SettingsEditAccountPasswordComponent } from './settings-edit-account-password/settings-edit-account-password.component';
import { SettingsEditAccountPortfolioitemsComponent } from './settings-edit-account-portfolioitems/settings-edit-account-portfolioitems.component';
import { SettingsEditAccountProfileComponent } from './settings-edit-account-profile/settings-edit-account-profile.component';
import { LoginComponent } from './login/login.component';
import { LoginErrorComponent } from './login-error/login-error.component';
import { LogoutConfirmationComponent } from './logout-confirmation/logout-confirmation.component';
import { SearchComponent } from './search/search.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShopComponent } from './shop/shop.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    IndexComponent,
    AdminComponent,
    AdminTemplatesListComponent,
    AdminAppGraphicsComponent,
    IndexComponent,
    SettingsEditAccountComponent,
    SettingsEditAccountEditPortfolioitemComponent,
    SettingsEditAccountMytemplatesComponent,
    SettingsEditAccountPanelComponent,
    SettingsEditAccountPasswordComponent,
    SettingsEditAccountPortfolioitemsComponent,
    SettingsEditAccountProfileComponent,
    LoginComponent,
    LoginErrorComponent,
    LogoutConfirmationComponent,
    SearchComponent,
    ShopComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
