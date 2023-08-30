import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CitylistComponent } from './components/citylist/citylist.component';

const routes: Routes = [
  {path: '', component: CitylistComponent},
  {path: 'weather', component: CitylistComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
