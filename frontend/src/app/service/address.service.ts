import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {throwError} from "rxjs";
import {Address} from "../model/address";

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private baseUrl = 'http://localhost:8080/address/';

  constructor(private http: HttpClient) {
  }

  getAddresses(param: string) {
    let params = new HttpParams();
    params = params.append('country', param);
    params = params.append('city', param);
    params = params.append('zipCode', param);
    params = params.append('streetName', param);
    params = params.append('houseNumber', param);

    return this.http
      .get<Address[]>(this.baseUrl + "filter", {params: params})
      .pipe(map(data => data), catchError(this.handleError));
  }

  delete(address: Address) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const url = `${this.baseUrl}/delete/${address.addressId}`;

    return this.http.delete<Address>(url).pipe(catchError(this.handleError));
  }

  private handleError(res: HttpErrorResponse | any) {
    console.error(res.error || res.body.error);
    return throwError(res.error || 'Server error');
  }
}
