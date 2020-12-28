import { Component, OnInit } from '@angular/core';
import { MqttService } from '../api';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-mqtt',
  templateUrl: './mqtt.component.html',
  styleUrls: ['./mqtt.component.css']
})
export class MqttComponent implements OnInit {

  constructor(private mqttService: MqttService) { }

  ngOnInit(): void {
  }

  subscribeTopic(data: { topic: string; }){
    this.mqttService.subscribeChannel(data.topic).subscribe();
    location.reload();
  }

  unsubscribeTopic(unsub: { utopic: string; }){
    this.mqttService.unsubscribeChannel(unsub.utopic).subscribe();
    location.reload();
  }
}
