import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-settings-edit-account-mytemplates',
  templateUrl: './settings-edit-account-mytemplates.component.html',
  styleUrls: ['./settings-edit-account-mytemplates.component.css']
})
export class SettingsEditAccountMytemplatesComponent implements OnInit {

  constructor(public loginService:LoginService) { }

  ngOnInit(): void {
  }

}
