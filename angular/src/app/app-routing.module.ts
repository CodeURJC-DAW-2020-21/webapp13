import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from './index/index.component'
import { SearchComponent } from './search/search.component'
import { AdminComponent } from "./admin/admin.component";
import { AdminAppGraphicsComponent } from "./admin-app-graphics/admin-app-graphics.component";
import { AdminTemplatesListComponent } from "./admin-templates-list/admin-templates-list.component";
import { SettingsEditAccountComponent } from './settings-edit-account/settings-edit-account.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'search', component: SearchComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/appGraphics', component: AdminAppGraphicsComponent},
  { path: 'admin/templatesList', component: AdminTemplatesListComponent},
  { path: 'settings-edit-account', component: SettingsEditAccountComponent},

];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

