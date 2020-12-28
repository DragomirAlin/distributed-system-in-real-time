import { Component, OnInit } from '@angular/core';
import { UserService } from '../api';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {
  topics: any;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getSubscribes().subscribe((response) =>{
      this.topics = response;
    })
  }



}
