import { Component, OnInit } from '@angular/core';
import { TemplateService } from '../../services/template.service'
import { Template } from '../../models/template.model';


@Component({
  selector: 'app-admin-templates-list',
  templateUrl: './admin-templates-list.component.html',
  styleUrls: ['./admin-templates-list.component.css']
})
export class AdminTemplatesListComponent implements OnInit {

  templates: any[] = []

  constructor(private templateService: TemplateService) { }

  ngOnInit(): void {
    this.templateService.getTemplates().subscribe(
      templates => {
        templates.map(template => this.templates.push(template))
      }
    )
  }

  addNewTemplate(name: string, price: number, description: string){
    let isFree: boolean = false
    if (price == 0) {
      isFree = true
    }
    else{
      isFree == false
    }
    let htmlPath: string = "/templates/" + name
    let id: number = 10 + this.templates.length - 2
    this.templateService.postTemplate(htmlPath, name, price, description, isFree).subscribe(
      template => {
        this.templates.push(new Template(id, htmlPath, name, price, isFree, description))
        console.log(template)
      },
      error => console.error(error)
    )
  }

  seeTemplate(id: number){
    this.templateService.getTemplate(id).subscribe(
      template => {
        console.log(template)
      },
      error => console.error("Error")
    )
  }

}
