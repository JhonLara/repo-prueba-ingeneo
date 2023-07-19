import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';

import { FormGroup } from '@angular/forms';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = '/api/api/v1/auth/users';
  list: any = [];
  public formData!: FormGroup;
  constructor(private http: HttpClient, private datePipe: DatePipe) {}

  login(userName: string, pwd: string) {
    let params = new HttpParams().set('userName', userName);
    params.set('password', pwd)
    return this.http.get(`${this.baseUrl}/signin`, { params: params });
  }

  verifEmail(email: string) {
    return this.http.get(`${this.baseUrl}/verif/${email}`);
  }

  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createData(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/register`, info);
  }

  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteData(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
