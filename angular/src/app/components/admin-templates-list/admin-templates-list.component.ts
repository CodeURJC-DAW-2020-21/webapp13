import { Component, OnInit } from '@angular/core';
import { TemplateService } from '../../services/template.sevice'
import { Observable } from 'rxjs';
import { error } from 'selenium-webdriver';

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
    //TODO HAY QUE PROBARLO
  }

  addNewTemplate(name: string, price: number, description: string){
    let isFree: boolean = price == 0
    let htmlPath: string = "/templates/" + name
    let id: number = this.templates.length + 1
    this.templateService.postTemplate(id, htmlPath, name, price, isFree, description).subscribe(
      template => {
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
      error => console.error(error)
    )
  }

}
