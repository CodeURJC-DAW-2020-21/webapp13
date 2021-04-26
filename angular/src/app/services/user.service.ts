import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class UserService {

    constructor(private httpClient: HttpClient) { 
    }


    getUsers(url:string):Observable<any[]> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractUsers(response as any))
        )
    }

    getUsersProfilePhoto(): Observable<any> {
        return this.httpClient.get("/api/users/anitarom56/image").pipe(
            map(response => this.extractUserProfilePhoto(response as any))
        )
    }

    getImage(id: string): Observable<Blob> {
        return this.httpClient.get('/api/users/anitarom56/image', {responseType: "blob"});
    }
    
    getTotalElements(url:string): Observable<number> {
        return this.httpClient.get(url).pipe(
            map(response => this.extractTotalElements(response as number))
        )
    }

    postUser(id:string,email:string,name:string,surname:string,password:string,phoneNumber:string,bornDate:string,city:string,freelance:string,category:string,degree:string,website:string,description:string):Observable<User> {
        return this.httpClient.post("/api/users/",{id,name,surname,password,phoneNumber,bornDate,city,freelance,category,degree,website,description}).pipe(
            map(response => this.extractUserProfilePhoto(response as User))
        )
    }

    private extractUsers(response){
        return response.content
    }

    private extractTotalElements(response){
        return response.totalElements
    }

    private extractUserProfilePhoto(response) {
        return response
    }
}