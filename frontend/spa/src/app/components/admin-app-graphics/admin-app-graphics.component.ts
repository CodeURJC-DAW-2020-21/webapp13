import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import * as CanvasJS from '../../../assets/js/canvasjs.min'
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-app-graphics',
  templateUrl: './admin-app-graphics.component.html',
  styleUrls: ['./admin-app-graphics.component.css']
})
export class AdminAppGraphicsComponent implements OnInit {

  usersPerMonth: any[] = []

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getUsersPerMonth().subscribe(
      usersPerMonth => {
        for (let i = 0; i < 12; i++) {
          this.usersPerMonth.push(usersPerMonth[i])

        }
        this.createChart(this.usersPerMonth)
      },
      error => this.router.navigate(['/error', error.status, error.statusText, error.name, error.message])
    )
  }

  private createChart(usersPerMonth: any[]): void {
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
          { y: usersPerMonth[11], label: "Diciembre" }
        ]
      }]
    });

    chart.render();
  }


}
