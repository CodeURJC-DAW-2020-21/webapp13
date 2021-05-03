import { User } from './../../models/user.model';
import { TemplateService } from './../../services/template.service';
import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Template } from './../../models/template.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  constructor(private userService:UserService, private loginService:LoginService, private templateService:TemplateService, private router: Router) { }

  user: User

  purchasedTemplates: Template[] = []

  notPurchasedTemplates: Template[] = []

  recommendedTemplate: Template

  contentToRecommend: boolean

  contentToShop: boolean

  ngOnInit(): void {

    this.user = this.loginService.refreshUser()

    if(this.user != undefined){
      this.userService.getUserTemplates(this.user.content.id).subscribe(
        templates => {
          templates.map(template => this.purchasedTemplates.push(template))
        },
        error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      )
    }

    this.templateService.getTemplates().subscribe(
      templates => {
        templates.map(template => this.notPurchasedTemplates.push(template))
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )

    this.recommendedTemplate = undefined

    this.contentToRecommend = false

    this.contentToShop = true

  }

  isPurchased(template:Template):boolean {
  return this.purchasedTemplates.some(function(arrVal) {
    return template.id === arrVal.id;
    });
  }

  purchaseTemplate(templateId:number){
    this.userService.purchaseTemplate(this.user.content.id, templateId).subscribe(
      template => {
        this.router.navigate(["/settings-edit-account-mytemplates"])
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  recommendTemplate(){
    this.userService.recommendTemplate(this.user.content.id).subscribe(
      template => {
        this.contentToShop = false
        this.recommendedTemplate = template
        if (this.recommendedTemplate==null){
          this.contentToRecommend = false
        }else{
          this.contentToRecommend = true
        }
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  previewTemplate(price: number){
    if (this.user == undefined){
      if (price==0){
        this.router.navigate(['/free-template', "userNotLogued"])
      }else{
        this.router.navigate(['/premium-template', "userNotLogued"])
      }
    }else{
      if (price==0){
        this.router.navigate(['/free-template', this.user.content.id])
      }else{
        this.router.navigate(['/premium-template', this.user.content.id])
      }
    }
  }

}
