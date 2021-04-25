import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from './index/index.component'
import { SearchComponent } from './search/search.component'
import { SettingsEditAccountComponent } from './settings-edit-account/settings-edit-account.component'
import { ShopComponent } from './shop/shop.component'


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'search', component: SearchComponent },
  { path: 'settings-edit-account', component: SettingsEditAccountComponent},
  { path: 'shop', component: ShopComponent},

];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

