import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { SearchUserComponent } from './search-user/search-user.component';

const routes: Routes = [
  { path: 'search', component: SearchUserComponent },
  { path: 'add', component: AddUserComponent},
  { path: '', component: SearchUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [SearchUserComponent, AddUserComponent]
