import {Component, OnInit} from '@angular/core';
import {UserService, MqttService} from '../api';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    temperature: any = [];
    humidity: any = [];

    lineChartData: ChartDataSets[] = [
        { data: this.temperature, label: 'Temperature' },
        { data: this.humidity, label: 'Humidity' }
    ];


    //Labels shown on the x-axis
    lineChartLabels: Label[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20'];

    // Define chart options
    lineChartOptions: ChartOptions = {
        responsive: true
    };

    // Define colors of chart segments
    lineChartColors: Color[] = [

        { // dark grey
            borderColor: 'red',
        },
        { // red
            borderColor: 'cyan',
        }
    ];

    // Set true to show legends
    lineChartLegend = true;

    // Define type of chart
    lineChartType = 'line';

    lineChartPlugins = [];


    constructor(private userServie: UserService, private mqttService: MqttService) {
    }

    ngOnInit(): void {
        window.setInterval(() => {
            this.mqttService.getData().subscribe((response) => {
                this.temperature.push(response.temperature);
                this.humidity.push(response.humidity);
                if(this.temperature.length > 19){
                    this.temperature.shift();
                    this.humidity.shift();
                    console.log(this.temperature);
                }

            });
        }, 1000);
    }

    chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }

    chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
        console.log(event, active);
    }



}
