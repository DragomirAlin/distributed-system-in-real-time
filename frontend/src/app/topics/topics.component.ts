import {AfterViewInit, Component, ViewChild, OnInit} from '@angular/core';
import {Subscribes, UserService} from '../api';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';

@Component({
    selector: 'app-topics',
    templateUrl: './topics.component.html',
    styleUrls: ['./topics.component.css']
})
export class TopicsComponent implements OnInit {
    topics: any;
    displayedColumns: string[] = ['id', 'topic'];
    dataSource = new MatTableDataSource<Subscribes>();

    constructor(private userService: UserService) {
    }


    ngOnInit(): void {
        this.userService.getSubscribes().subscribe((response) => {
            this.topics = response;
            console.log(this.topics)
            this.dataSource = new MatTableDataSource<Subscribes>(this.topics);

        });
    }


}
