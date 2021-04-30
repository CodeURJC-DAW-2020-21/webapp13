import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import * as CanvasJS from '../../../assets/js/canvasjs.min'

@Component({
  selector: 'app-admin-app-graphics',
  templateUrl: './admin-app-graphics.component.html',
  styleUrls: ['./admin-app-graphics.component.css']
})
export class AdminAppGraphicsComponent implements OnInit {

  usersPerMonth: any[] = []

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsersPerMonth().subscribe(
      usersPerMonth => {
        this.usersPerMonth.push(usersPerMonth[0])
        this.usersPerMonth.push(usersPerMonth[1])
        this.usersPerMonth.push(usersPerMonth[2])
        this.usersPerMonth.push(usersPerMonth[3])
        this.usersPerMonth.push(usersPerMonth[4])
        this.usersPerMonth.push(usersPerMonth[5])
        this.usersPerMonth.push(usersPerMonth[6])
        this.usersPerMonth.push(usersPerMonth[7])
        this.usersPerMonth.push(usersPerMonth[8])
        this.usersPerMonth.push(usersPerMonth[9])
        this.usersPerMonth.push(usersPerMonth[10])
        this.usersPerMonth.push(usersPerMonth[11])
        console.log(this.usersPerMonth)
        this.createChart(this.usersPerMonth)
      },
      error => console.log("error")
    )    
  }

  private createChart(usersPerMonth: any[]){
    let chart = new CanvasJS.Chart("chartContainer", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Usuarios de Porthub por mes"
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: usersPerMonth[0], label: "Enero" },
          { y: usersPerMonth[1], label: "Febrero" },
          { y: usersPerMonth[2], label: "Marzo" },
          { y: usersPerMonth[3], label: "Abril" },
          { y: usersPerMonth[4], label: "Mayo" },
          { y: usersPerMonth[5], label: "Junio" },
          { y: usersPerMonth[6], label: "Julio" },
          { y: usersPerMonth[7], label: "Agosto" },
          { y: usersPerMonth[8], label: "Septiembre" },
          { y: usersPerMonth[9], label: "Octubre" },
          { y: usersPerMonth[10], label: "Noviembre" },
          { y:usersPerMonth[11], label: "Diciembre" }
        ]
      }]
    });
      
    chart.render();
  }


}
