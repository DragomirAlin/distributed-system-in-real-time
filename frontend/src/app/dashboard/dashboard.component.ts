import {Component, OnInit} from '@angular/core';
import {UserService, MqttService} from '../api';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    temperature: any;
    humidity: any;
    constructor(private userServie: UserService, private mqttService: MqttService) {
    }

    ngOnInit(): void {
        // window.setInterval(() => {
        //     this.mqttService.getData().subscribe((response) => {
        //         this.temperature = response.temperature;
        //         this.humidity = response.humidity;
        //     });
        // }, 1000);
    }

}
