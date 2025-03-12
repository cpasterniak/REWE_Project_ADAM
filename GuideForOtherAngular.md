## 🚀 **Short Guide: Kafka Integration for Any Angular Project**

**Overall architecture:**

```

Kafka ➡️ Node.js Backend (REST API) ➜ Angular Frontend (HTTP)

```

---

### ✅ **1. Backend Setup (Reusable Node.js Backend):**

Create a folder named `backend` in your Angular project's root:

```bash

mkdir backend
cd backend
npm init -y
npm install express cors kafkajs

```

### **Simple Kafka Backend (`server.js`)**

Create `server.js` inside the backend folder:

```jsx

const express = require('express');
const cors = require('cors');
const { Kafka } = require('kafkajs');

const app = express();
app.use(cors());

const kafka = new Kafka({
  clientId: 'angular-kafka-app',
  brokers: ['localhost:9092']
});

const consumer = kafka.consumer({ groupId: 'angular-group' });
const messages = [];

async function consumeKafka() {
  await consumer.connect();
  await consumer.subscribe({ topic: 'my-topic', fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ message }) => {
      messages.push(message.value.toString());
    },
  });
}

consumeKafka().catch(console.error);

app = express();
app.use(cors());

app.get('/api/messages', (req, res) => res.json(messages));

app.listen(3000, () => {
  console.log('Kafka backend running on http://localhost:3000');
});

```

Run your backend with:

```bash

node server.js

```

---

## 🔥 **Frontend Angular Setup**

Inside your Angular project:

### **1. Provide HTTP support (in `main.ts`)**

Update `main.ts` (Angular 16+):

```tsx
import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, {
  providers: [provideHttpClient()],
}).catch(console.error);

```

---

### **2. Kafka Service (reusable)**

Create `kafka.service.ts` in `src/app/`:

```tsx

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class KafkaService {
  constructor(private http: HttpClient) { }

  getMessages() {
    return this.http.get<string[]>('http://localhost:3000/api/messages');
  }
}

```

---

### **2. Angular Component Integration (Example)**

A simple example Angular component to consume and display Kafka data:

```tsx

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KafkaService } from './kafka.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <h2>Kafka Messages</h2>
    <ul>
      <li *ngFor="let message of messages">{{ message }}</li>
    </ul>
  `
})
export class AppComponent implements OnInit {
  messages: string[] = [];

  constructor(private kafkaService: KafkaService) {}

  ngOnInit() {
    this.kafkaService.getMessages().subscribe(msgs => {
      this.messages = msgs;
    });
  }
}

```
