import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class FileService {

  private baseUrl = 'http://localhost:8080/'; // URL to web api

  constructor(private http: HttpClient) {
  }

  downloadFile(path: string): any {
    return this.http.get(`${this.baseUrl}/${path}/download`, {responseType: 'blob'});
  }
  
}
