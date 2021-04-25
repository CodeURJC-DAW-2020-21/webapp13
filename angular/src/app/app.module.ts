import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { IndexComponent } from './index/index.component';
import { AdminComponent } from './admin/admin.component';
import { AdminTemplatesListComponent } from './admin-templates-list/admin-templates-list.component';
import { AdminAppGraphicsComponent } from './admin-app-graphics/admin-app-graphics.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    IndexComponent,
    AdminComponent,
    AdminTemplatesListComponent,
    AdminAppGraphicsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
