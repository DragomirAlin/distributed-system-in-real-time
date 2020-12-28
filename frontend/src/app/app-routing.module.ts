import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent} from './dashboard/dashboard.component';
import { MqttComponent} from './mqtt/mqtt.component';
import { TopicsComponent } from './topics/topics.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'mqtt', component: MqttComponent},
  {path: 'topic', component: TopicsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
