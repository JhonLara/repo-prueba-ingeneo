import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';
import { MatPaginatorModule } from '@angular/material/paginator';
import { IndexComponent } from './index/index.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './user/register/register.component';
import { APP_BASE_HREF, DatePipe, DecimalPipe } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LoginComponent } from './user/login/login.component';
import { StorageComponent } from './storage/storage.component';
import { ProductComponent } from './product/product-form/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';

const MATERIAL_MODULES = [MatToolbarModule, MatIconModule];
@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    RegisterComponent,
    LoginComponent,
    StorageComponent,
    ProductComponent,
    ProductListComponent
  ],
  imports: [
    MatToolbarModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    NgxPaginationModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule,
    AppRoutingModule,
    MatIconModule,
    ToastrModule.forRoot(),
    MatPaginatorModule,
  ],
  exports: MATERIAL_MODULES,
  providers: [
    DatePipe,
    DecimalPipe,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: APP_BASE_HREF, useValue: '' },
    { provide: MatDialogRef, useValue: {} },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
