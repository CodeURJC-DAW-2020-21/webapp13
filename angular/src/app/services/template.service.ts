import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { Template } from "../models/template.model"

@Injectable({ providedIn: 'root' })
export class TemplateService{

    constructor(private httpClient: HttpClient) { 
    }

    getTemplates():Observable<Template[]>{
        return this.httpClient.get("/api/templates/").pipe(
            map(response => this.extractTemplates(response as Template[]))
        )
    }

    getTemplate(id:number):Observable<Template> {
        return this.httpClient.get("/api/templates/"+String(id)).pipe(
            map(response => this.extractResponse(response as Template))
        )
    }

    postTemplate(htmlPath: string, name: string, price: number, description: string, free:boolean){
        return this.httpClient.post("/api/templates/", {htmlPath, name, price, description, free}).pipe(
            map(response => {this.extractTemplate(response as Template)}), 
        )    
    }

    private extractTemplates(response){
        return response.content
    }

    private extractTemplate(response){
        return response.content
    }

    private extractResponse(response) {
        return response
    }
}
