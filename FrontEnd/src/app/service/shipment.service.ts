import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { FormGroup } from '@angular/forms';
@Injectable({
  providedIn: 'root',
})
export class ShipmentService {
  private baseUrl = '/api/api/v1/auth/shipments';
  private storageUrl = '/storage';
  private productUrl = '/product';
  public formData!: FormGroup;
  constructor(private http: HttpClient, private datePipe: DatePipe) {}

  createStorage(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl + this.storageUrl}`, info);
  }
  createProduct(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl + this.productUrl}`, info);
  }
}
