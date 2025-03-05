import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KafkaService {
  private apiUrl = 'http://localhost:9092'; // Stelle sicher, dass deine Kafka-API hier erreichbar ist

  constructor(private http: HttpClient) {}

  getProductRecalls(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
