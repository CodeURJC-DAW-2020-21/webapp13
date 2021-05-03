import { Template } from './../models/template.model';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class UserService {

    constructor(private httpClient: HttpClient) {
    }

    getUser(user:string):Observable<User> {
        return this.httpClient.get("/api/users/"+user).pipe(
            map(response => this.extractResponse(response as User))
        )
    }

    getUsers(url:string):Observable<User[]> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractUsers(response as User))
        )
    }

    getUserActiveTemplate(userId:string):Observable<Template> {
      return this.httpClient.get("/api/users/"+userId+"/activeTemplate").pipe(
          map(response => this.extractResponse(response as Template))
      )
    }

    getUserTemplates(userId:string):Observable<Template[]> {
      return this.httpClient.get("/api/users/"+userId+"/templates").pipe(
          map(response => this.extractResponse(response as Template))
      )
    }


    getTotalElements(url:string): Observable<number> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractTotalElements(response as number))
        )
    }

    getUsersPerMonth(): Observable<any[]>{
        return this.httpClient.get("/api/users/statistics").pipe(
            map(response => this.extractResponse(response as any[]))
        )
    }

    postUser(id:string,email:string,name:string,surname:string,password:string,phoneNumber:string,bornDate:string,city:string,freelance:string,category:string,degree:string,website:string,description:string):Observable<User> {
        return this.httpClient.post("/api/users/",{id,name,surname,password,phoneNumber,bornDate,city,freelance,category,degree,website,description}).pipe(
            map(response => this.extractResponse(response as User))
        )
    }

    putUser(id:string,name:string,surname:string,email:string,phoneNumber:string,city:string,degree:string,freelance:string,category:string,description:string):Observable<User> {
      return this.httpClient.put("/api/users/"+id,{name,surname,email,phoneNumber,city,degree,freelance,category,description}).pipe(
          map(response => this.extractResponse(response as User))
      )
    }

    putImage(userId:string,image:File):Observable<string> {
      const formData = new FormData();
      formData.append("profilePhoto", image);

      return this.httpClient.put("/api/users/"+userId+"/image",formData).pipe(
          map (response => this.extractResponse(response as string))
      )
    }


    putPassword(id:string,password:string):Observable<User> {
      return this.httpClient.put("/api/users/"+id,{password}).pipe(
          map(response => this.extractResponse(response as User))
      )
    }

    deleteUser(userId: string){
        return this.httpClient.delete("/api/users/"+userId).pipe(
            map(response => this.extractResponse(response as User))
        )
    }

    activateTemplate(userId:string, templateId:number):Observable<Template> {
      return this.httpClient.put("/api/users/"+userId+"/activeTemplate/"+templateId, {}).pipe(
          map(response => this.extractResponse(response as Template))
      )
    }

    purchaseTemplate(userId:string, templateId:number):Observable<Template> {
      return this.httpClient.put("/api/users/"+userId+"/templateList/"+templateId, {}).pipe(
          map(response => this.extractResponse(response as Template))
      )
    }

    recommendTemplate(userId:string):Observable<Template> {
      return this.httpClient.get("/api/users/"+userId+"/recommendedTemplate/", {}).pipe(
          map(response => this.extractResponse(response as Template))
      )
    }

    private extractUsers(response){
        return response.content
    }

    private extractTotalElements(response){
        return response.totalElements
    }

    private extractResponse(response) {
        return response
    }
}
