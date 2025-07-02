# KSQL and stream processing

# Data processing Pipelines in Kafka
```mermaid
graph TD

A[(Sources)]
B[(Sinks)]
C[Kafka Connect Source]
D[Kafka Connect Sink]
E[Kafka Streams]
F[Kafka]
G[KSQL - Write SQL]

A -->|Fast, Real Time| C
C --> F
F <-->|Stream Processing| E
F --> D
D -->|Insights, Actions| B
E <-->|Generate Kafka Streams| G
```

## KSQL form Confluent
- Writing Kafka stream java applications is complex
  - May want to write SQL
  - Underneath, Kafka streams applications are generated
  - Get the same benefits (Scale, Security)

## KSQL use cases
- Real time analytics dashboard.
- Correlate event behaviour.
- Home automation notifications.
- .....

## How does KSQL work?
```mermaid
graph LR
    
    A[KSQL CLI]
    B[KSQL Server]
    C[Kafka Streams embedded]
    D[Kafka]
    
    A -->|HTTP| B
    B --> C
    C --> D
    
```