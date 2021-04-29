import { UserService } from './../../services/user.service';
import { IndexComponent } from './../index/index.component';
import { Template } from './../../models/template.model';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-settings-edit-account-mytemplates',
  templateUrl: './settings-edit-account-mytemplates.component.html',
  styleUrls: ['./settings-edit-account-mytemplates.component.css']
})
export class SettingsEditAccountMytemplatesComponent implements OnInit {

  constructor(private userService:UserService, public loginService:LoginService) { }

  userTemplates: Template[] = []

  activeTemplate: Template

  ngOnInit(): void {
    this.userService.getUserActiveTemplate(this.loginService.currentUser().content.id).subscribe(
      template => {
        this.activeTemplate = template
      },
      error => console.log("error")
    )

    //this.userTemplates = this.loginService.currentUser().content.templates.map(template => this.userTemplates.push(template))
    this.userService.getUserTemplates(this.loginService.currentUser().content.id).subscribe(
      templates => {
        templates.map(template => this.userTemplates.push(template))
        console.log(this.userTemplates)
      },
      error => console.log("error")
    )

  }

  activateTemplate(templateId: number){
    this.userService.activateTemplate(this.loginService.currentUser().content.id, templateId).subscribe(
      template => {
        this.activeTemplate = template
      },
      error => console.log("error")
    )
  }


}
