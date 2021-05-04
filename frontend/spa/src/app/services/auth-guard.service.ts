import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public loginService: LoginService, public router: Router) {}

    canActivate(route: ActivatedRouteSnapshot,state: RouterStateSnapshot):boolean { 
            
            if (localStorage.getItem('logged')==='true'){
                console.log("Estoy logueado")
                let userRole: String = "USER"
                if (this.loginService.checkAdmin()){
                    userRole = "ADMIN"
                    return true
                }
                if(route.data.role && route.data.role.indexOf(userRole) !== -1)
                    return true
    
                this.router.navigate([''])
                return false
            }
            if(localStorage.getItem('logged')===undefined){
                console.log("No estoy logueado ni definido")
                this.router.navigate([''])
                return false
            }
            this.router.navigate(['login'])
            return false
        }

 
}