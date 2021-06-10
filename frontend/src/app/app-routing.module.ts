import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PartnerComponent} from "./component/partner/partner.component";
import {AddressComponent} from "./component/address/address.component";

const routes: Routes = [
  { path: '', redirectTo: '/partner', pathMatch: 'full' },
  { path: 'partner', component: PartnerComponent },
  { path: 'address', component: AddressComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
