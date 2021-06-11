import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = `${environment.baseUrl}`;

  constructor(private http: HttpClient) {
  }

  downloadFile(path: string): any {
    return this.http.get(`${this.baseUrl}${path}/download`, {responseType: 'blob'});
  }

}
