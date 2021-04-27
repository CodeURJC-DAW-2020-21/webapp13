import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class PortfolioitemService {

    constructor(private httpClient: HttpClient) { 

    }

    getPortfolioItems(user: string,page: number):Observable<any[]> {
        return this.httpClient.get("/api/portfolioItems/users/"+user+"/?page="+page).pipe(
            map(response => this.extracPortfolioItems(response as any))
        )
    }

    private extracPortfolioItems(response){
        return response.content
    }

}
