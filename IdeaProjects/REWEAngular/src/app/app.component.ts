import { Component, OnInit } from '@angular/core';
import { KafkaService } from './services/kafka.service';
import { NgFor, NgIf, TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [NgFor, NgIf, TitleCasePipe]
})
export class AppComponent implements OnInit {
  title = 'Product Recall Dashboard';
  kafkaMessages: string[] = [];  // Array für Kafka-Nachrichten

  constructor(private kafkaService: KafkaService) {}

  ngOnInit(): void {
    // Empfange Kafka-Nachricht und speichere sie im Array
    this.kafkaService.getProductRecalls().subscribe(data => {
      this.kafkaMessages = data;
      console.log("Nachricht von Kafka empfangen:", this.kafkaMessages);
    });
  }
}
