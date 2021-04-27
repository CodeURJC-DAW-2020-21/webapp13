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

    getPortfolioItems(user: string,page: number):Observable<any[]> {
        return this.httpClient.get("/api/portfolioItems/users/"+user+"/?page="+page).pipe(
            map(response => this.extracPortfolioItems(response as any))
        )
    }

    post(content):Observable<Portfolioitem> {
        return this.httpClient.post("/api/portfolioItems/users/"+content.userId,content).pipe(
            map( response => this.extracPortfolioItem(response as Portfolioitem))
        )
    }

    getTotalElements(url:string): Observable<number> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractTotalElements(response as number))
        )
    }

    private extracPortfolioItems(response){
        return response.content
    }

    private extracPortfolioItem(response){
        return response
    }

    private extractTotalElements(response){
        return response.totalElements
    }


}
