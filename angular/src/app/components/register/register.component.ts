import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { LoginService } from '../../services/login.service'

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private userService: UserService, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  register(id:string,email:string,name:string,surname:string,password:string,phoneNumber:string,bornDate:string,city:string,freelance:string,category:string,degree:string,website:string,description:string){
    this.userService.postUser(id,email,name,surname,password,phoneNumber,bornDate,city,freelance,category,degree,website,description).subscribe(
      user => {
        console.log(user)
        this.loginService.logIn(id, password)
      },
      error => console.log("error")
    )
  }
}
