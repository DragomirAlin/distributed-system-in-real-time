import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ApiModule} from './api';
import {HttpClientModule} from '@angular/common/http';
import {CommonModule} from '@angular/common';
import { MqttComponent } from './mqtt/mqtt.component';
import {FormsModule} from '@angular/forms';
import { TopicsComponent } from './topics/topics.component';


@NgModule({
    declarations: [
        AppComponent,
        DashboardComponent,
        MqttComponent,
        TopicsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ApiModule,
        HttpClientModule,
        CommonModule,
        FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
