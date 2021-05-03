import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { catchError, map } from 'rxjs/operators'
import { Observable, throwError } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean;
    admin: boolean;
    user: User;

    constructor(private httpClient: HttpClient, private router: Router) {
        this.reqIsLogged();
    }

    reqIsLogged() {
        this.httpClient.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = new User(response);
                this.logged = true;
                this.isAdmin().subscribe(response => this.admin = response as boolean);
            },
            error => {
                this.logged = false;
                this.user = undefined;
            }
        );
    }

    logIn(user: string, pass: string) {
        this.httpClient.post("/api/auth/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => {
                  this.reqIsLogged()
                  this.router.navigate([''])
                },
                (error) => alert("Wrong credentials")
            );
    }

    logOut() {
       return this.httpClient.post("/api/auth/logout", { withCredentials: true }).pipe(
            map(_ => this.logOutConfirmed()),
            catchError(error => throwError('Server error'))
        );
    }

    logOutConfirmed(){
        this.logged = false;
        this.user = undefined;
        this.admin = false;
        console.log("LOGOUT: Successfully");
    }

    isLogged() {
        return this.logged;
    }

    private isAdmin() {
        return this.httpClient.get('/api/users/me/admin', { withCredentials: true }).pipe();
    }

    checkAdmin(){
        return this.admin;
    }

    currentUser() {
        return this.user;
    }

    refreshUser(){
        this.reqIsLogged();
        return this.user;
    }

    isUserAdmin(userId: String) {
      return this.httpClient.get('/api/users/'+userId+'/admin').pipe();
    }


}
