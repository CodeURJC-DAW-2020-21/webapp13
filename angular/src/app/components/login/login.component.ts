import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public loginService: LoginService) { }

  ngOnInit(): void {
  }

  
  logIn(event: any, user: string, pass: string):void {
    event.preventDefault();
    this.loginService.logIn(user, pass);

  }

}
