import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { Message } from "../models/message.model";

@Injectable({ providedIn: 'root' })
export class ChatService {

    constructor(private httpClient: HttpClient) {
    }


    getChats(user:string):Observable<string[]> {
        return this.httpClient.get("/api/messages/activeChats/"+user).pipe(
            map(response => this.extractResponse(response as string[]))
        )
    }


    getMessages(receiver:string,sender:string):Observable<Message[]> {
        return this.httpClient.get("/api/messages/"+receiver+"/"+sender).pipe(
            map(response => this.extractResponse(response as Message[]))
        )
    }

    postMessage(receiver:string,sender:string,content:string):Observable<Message> {
        return this.httpClient.post("/api/messages/",{content,sender,receiver}).pipe(
            map(response => this.extractResponse(response as Message))
        )
    }

    private extractResponse(response) {
        return response
    }

}
