import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './user/register/register.component';
import { LoginComponent } from './user/login/login.component';
import { StorageComponent } from './storage/storage-form/storage.component';
import { ProductComponent } from './product/product-form/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { StorageListComponent } from './storage/storage-list/storage-list.component';
import { ShipmentComponent } from './shipment/shipment-form/shipment.component';
import { ShipmentListComponent } from './shipment/shipment-list/shipment-list.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'storage', component: StorageComponent },
  { path: 'product', component: ProductComponent },
  { path: 'product-list', component: ProductListComponent },
  { path: 'storage-list', component: StorageListComponent },
  { path: 'shipment', component: ShipmentComponent },
  { path: 'shipment-list', component: ShipmentListComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
