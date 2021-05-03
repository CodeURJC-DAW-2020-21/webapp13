import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { catchError, map } from 'rxjs/operators'
import { Observable, throwError } from "rxjs";
import { User } from "../models/user.model";

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean 
    admin: boolean
    user: User

    constructor(private httpClient: HttpClient, private router: Router) {
        if(localStorage.getItem('logged')==='true'){
            this.logged = true
            if(localStorage.getItem('isAdmin')==='true'){
                this.admin = true
            }else{
                this.admin = false
            }
            this.user = new User(JSON.parse(localStorage.getItem('user')).content);
        }else{
          this.reqIsLogged()  
        }

    }

    reqIsLogged() {
        this.httpClient.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = new User(response);
                localStorage.setItem('user', JSON.stringify(this.user))
                this.logged = true;
                localStorage.setItem('logged', 'true')
                this.isAdmin().subscribe(response => {
                        this.admin = response as boolean
                        if(this.admin===true){
                            localStorage.setItem('isAdmin', 'true')
                        }else{
                            localStorage.setItem('isAdmin', 'false')
                        }
                    }
                );
            },
            error => {
                console.log("reqIsLogged ha devuelto error")
                this.admin = false
                this.logged = false
                this.user = undefined
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
        localStorage.setItem('user', undefined)
        localStorage.setItem('logged', 'false')
        localStorage.setItem('isAdmin', 'false')
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
