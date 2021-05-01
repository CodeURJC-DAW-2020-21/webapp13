import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(public loginService: LoginService, public router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
    ): boolean {
        if (this.loginService.isLogged()){
            let userRole: String = "USER"
            if (this.loginService.checkAdmin()){
                userRole = "ADMIN"
            }
            if(route.data.role && route.data.role.indexOf(userRole) !== -1)
                return true

            this.router.navigate([''])
            return false
        }
        if(this.loginService.isLogged()===undefined){
            this.router.navigate([''])
            return false
        }
        this.router.navigate(['login'])
        return false
    }

 
}