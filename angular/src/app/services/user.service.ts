import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class UserService {

    constructor(private httpClient: HttpClient) { 
    }


    getUsers(url:string):Observable<any[]> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractUsers(response as any))
        )
    }


    getTotalElements(url:string): Observable<number> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractTotalElements(response as number))
        )
    }

    private extractUsers(response){
        return response.content
    }

    private extractTotalElements(response){
        return response.totalElements
    }
}