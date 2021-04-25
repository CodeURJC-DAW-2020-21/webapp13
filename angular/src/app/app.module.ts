import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { IndexComponent } from './index/index.component';
<<<<<<< HEAD
=======
import { AdminComponent } from './admin/admin.component';
import { AdminTemplatesListComponent } from './admin-templates-list/admin-templates-list.component';
import { AdminAppGraphicsComponent } from './admin-app-graphics/admin-app-graphics.component';
>>>>>>> 017d17f1b4912bdb53ebd1b35ecddc3ba30b00cd
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

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    IndexComponent,
<<<<<<< HEAD
=======
    AdminComponent,
    AdminTemplatesListComponent,
    AdminAppGraphicsComponent,
    IndexComponent,
>>>>>>> 017d17f1b4912bdb53ebd1b35ecddc3ba30b00cd
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
