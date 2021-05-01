import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  errorStatus: number
  errorStatusText: string
  errorName: string
  errorMessage: string

  constructor(activatedRoute: ActivatedRoute) { 
    console.log(activatedRoute.snapshot.url.length)
    if(activatedRoute.snapshot.params!==undefined && activatedRoute.snapshot.url.length > 2){
      this.errorStatus = activatedRoute.snapshot.params['errorStatus']
      this.errorStatusText = activatedRoute.snapshot.params['errorStatusText']
      this.errorName = activatedRoute.snapshot.params['errorName']
      this.errorMessage = activatedRoute.snapshot.params['errorMessage']
    }else{
      this.errorStatus = 404
      this.errorStatusText = "Not Found"
      this.errorName = "HttpErrorResponse"
      this.errorMessage = "The page doesn't exist"
    }
    
  }

  ngOnInit(): void {
  }

}
