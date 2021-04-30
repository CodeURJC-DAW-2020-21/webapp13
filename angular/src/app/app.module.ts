import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AuthGuardService as AuthGuard } from '../app/services/auth-guard.service';

import { AppRoutingModule } from '../app/app-routing.module'
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { IndexComponent } from './components/index/index.component';
import { AdminComponent } from './components/admin/admin.component';
import { AdminTemplatesListComponent } from './components/admin-templates-list/admin-templates-list.component';
import { AdminAppGraphicsComponent } from './components/admin-app-graphics/admin-app-graphics.component';
import { SettingsEditAccountComponent } from './components/settings-edit-account/settings-edit-account.component';
import { SettingsEditAccountEditPortfolioitemComponent } from './components/settings-edit-account-edit-portfolioitem/settings-edit-account-edit-portfolioitem.component';
import { SettingsEditAccountMytemplatesComponent } from './components/settings-edit-account-mytemplates/settings-edit-account-mytemplates.component';
import { SettingsEditAccountPanelComponent } from './components/settings-edit-account-panel/settings-edit-account-panel.component';
import { SettingsEditAccountPasswordComponent } from './components/settings-edit-account-password/settings-edit-account-password.component';
import { SettingsEditAccountPortfolioitemsComponent } from './components/settings-edit-account-portfolioitems/settings-edit-account-portfolioitems.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ShopComponent } from './components/shop/shop.component';
import { RegisterComponent } from './components/register/register.component';
import { ChatComponent } from './components/chat/chat.component';
import { InfoComponent} from './components/info/info.component';
import { FooterComponent} from './components/footer/footer.component'

@NgModule({
  declarations: [AppComponent,
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
    LoginComponent,
    SearchComponent,
    ShopComponent,
    RegisterComponent,
    ChatComponent,
    InfoComponent,
    FooterComponent

  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
