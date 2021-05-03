import { Component, OnInit } from '@angular/core';
import { TemplateService } from '../../services/template.service'
import { Template } from '../../models/template.model';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import { LoginService } from '../../services/login.service'



@Component({
  selector: 'app-admin-templates-list',
  templateUrl: './admin-templates-list.component.html',
  styleUrls: ['./admin-templates-list.component.css']
})
export class AdminTemplatesListComponent implements OnInit {

  templates: any[] = []

  constructor(private templateService: TemplateService, private router: Router, public loginService: LoginService) { }

  ngOnInit(): void {
    this.templateService.getTemplates().subscribe(
      templates => {
        templates.map(template => this.templates.push(template))
      }
    )
  }

  addNewTemplate(name: string, price: number, description: string):void {
    let isFree: boolean = false
    if (price == 0) {
      isFree = true
    }
    else {
      isFree == false
    }
    let htmlPath: string = "/templates/" + name
    let id: number = 10 + this.templates.length - 2
    this.templateService.postTemplate(htmlPath, name, price, description, isFree).subscribe(
      template => {
        this.templates.push(new Template(id, htmlPath, name, price, isFree, description))
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  seeTemplate(id: number):void {
    const currentUser: User = this.loginService.currentUser()
    this.templateService.getTemplate(id).subscribe(
      template => {
        if (template.price == 0) {
          this.router.navigate(['/free-template', currentUser.content.id])
        } else {
          this.router.navigate(['/premium-template', currentUser.content.id])
        }
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  templateIsFree(template: Template):boolean {
    return template.isFree
  }

}
