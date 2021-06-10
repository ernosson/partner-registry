import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {Partner} from "../model/partner";
import {throwError} from "rxjs";
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PartnerService {

  private baseUrl = 'http://localhost:8080/partner/';

  constructor(private http: HttpClient) {
  }

  getPartners(param: string) {
    let params = new HttpParams();
    params = params.append('name', param);
    params = params.append('country', param);
    params = params.append('city', param);
    params = params.append('zipCode', param);
    params = params.append('streetName', param);
    params = params.append('houseNumber', param);

    return this.http
      .get<Partner[]>(this.baseUrl + "filter", {params: params})
      .pipe(map(data => data), catchError(this.handleError));
  }

  delete(partner: Partner) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    const url = `${this.baseUrl}/delete/${partner.partnerId}`;

    return this.http.delete<Partner>(url).pipe(catchError(this.handleError));
  }

  private handleError(res: HttpErrorResponse | any) {
    console.error(res.error || res.body.error);
    return throwError(res.error || 'Server error');
  }
}
