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
  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+this.productUrl}/${id}`, { responseType: 'text' });
  }
  getAllProducts(): Observable<any> {
    return this.http.get(`${this.baseUrl+this.productUrl}`);
  }
  deleteStorage(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl+this.storageUrl}/${id}`, { responseType: 'text' });
  }
  getAllStorages(): Observable<any> {
    return this.http.get(`${this.baseUrl+this.storageUrl}`);
  }

  deleteShipment(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
  getAllShipments(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  createShipment(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, info);
  }
}
