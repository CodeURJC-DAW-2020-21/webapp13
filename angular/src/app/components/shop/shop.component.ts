import { TemplateService } from './../../services/template.service';
import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { Template } from './../../models/template.model';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
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
          console.log(this.purchasedTemplates)
        },
        error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
      )
    }

    console.log(this.purchasedTemplates)

    this.templateService.getTemplates().subscribe(
      templates => {
        templates.map(template => this.notPurchasedTemplates.push(template))
        console.log(this.notPurchasedTemplates)
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
        console.log("Comprada")
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
        console.log(this.recommendedTemplate)
        if (this.recommendedTemplate==null){
          this.contentToRecommend = false
        }else{
          this.contentToRecommend = true
        }
        console.log(this.contentToRecommend)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

}
