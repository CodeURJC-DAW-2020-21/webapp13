import { TemplateService } from './../../services/template.service';
import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Template } from './../../models/template.model';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  constructor(private userService:UserService, private loginService:LoginService, private templateService:TemplateService) { }

  user: User

  purchasedTemplates: Template[] = []

  notPurchasedTemplates: Template[] = []

  recommendedTemplate: Template

  ngOnInit(): void {

    this.user = this.loginService.refreshUser()

    if(this.user != undefined){
      this.userService.getUserTemplates(this.user.content.id).subscribe(
        templates => {
          templates.map(template => this.purchasedTemplates.push(template))
          console.log(this.purchasedTemplates)
        },
        error => console.log("error")
      )
    }

    this.templateService.getTemplates().subscribe(
      templates => {
        templates.map(template => this.notPurchasedTemplates.push(template))
        console.log(this.notPurchasedTemplates)
      },
      error => console.log("error")
    )

  }

  isPurchased(template:Template):boolean {
  return this.purchasedTemplates.some(function(arrVal) {
    return template.id === arrVal.id;
    });
  }



}
