import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { map } from 'rxjs/operators'
import { Observable } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class LoginService {
    
    constructor(private httpClient: HttpClient) { 

    }
}
