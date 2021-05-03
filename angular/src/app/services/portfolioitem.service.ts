import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { User } from "../models/user.model";
import { Portfolioitem } from "../models/portfolioitem.model";

@Injectable({ providedIn: 'root' })
export class PortfolioitemService {

    constructor(private httpClient: HttpClient) {

    }

    getPortfolioItems(user: string,page: number):Observable<Portfolioitem[]> {
        return this.httpClient.get("/api/portfolioItems/users/"+user+"/?page="+page).pipe(
            map(response => this.extractResponse(response as Portfolioitem))
        )
    }

    getPortfolioItem(id:string):Observable<Portfolioitem> {
        return this.httpClient.get("/api/portfolioItems/"+id).pipe(
            map(response => this.extractResponse(response as Portfolioitem))
        )
    }

    post(content):Observable<Portfolioitem> {
        return this.httpClient.post("/api/portfolioItems/users/"+content.userId,content).pipe(
            map( response => this.extractResponse(response as Portfolioitem))
        )
    }

    delete(user:string,id:string) {
        return this.httpClient.delete("/api/portfolioItems/users/"+user+"/"+id)
    }

    putImage(user:string,item:string,type:string,image:File):Observable<string> {
        console.log(image)
        const formData = new FormData();
        formData.append(type, image);

        return this.httpClient.put("/api/portfolioItems/users/"+user+"/"+item+"/"+type,formData).pipe(
            map (response => this.extractResponse(response as string))
        )
    }

    putText(user:string,item:string,data:any):Observable<Portfolioitem> {
        return this.httpClient.put("/api/portfolioItems/users/"+user+"/"+item,data).pipe(
            map (response => this.extractResponse(response as Portfolioitem))
        )
    }

    private extractResponse(response){
        return response
    }

}
