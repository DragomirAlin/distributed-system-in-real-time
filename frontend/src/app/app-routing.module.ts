import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent} from './dashboard/dashboard.component';
import { MqttComponent} from './mqtt/mqtt.component';

const routes: Routes = [
  {path: 'dashboard', component: DashboardComponent},
  {path: 'mqtt', component: MqttComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
