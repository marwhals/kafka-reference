# Kafka introduction
- Useful for data integration

## Setting the stage

- Companies have a source system like a DB and at some point another part of the company will want to take that data to put it into another system.
- The data has to move from source system to a target system
- Initially very simple, someone writes some code and then takes the data, extracts it, transforms it and then loads it.
- Eventually system involves many source systems.
- --- Data integration challenges just got a lot more complicated, because all your target systems to share information.
- --- Example: 4 sources systems and 6 target systems you would have to write (4 x 6 = 24) data integration systems.
- ----> Additionally each integration comes with difficulty with around the protocol, because the technology has changed.
- -----> Data could be transported over TCP, HTTP, REST, FTP, JDBC
- -----> Data format - how the data is parsed (Binary, CSV, JSON, Avro, Protobuf)
- -----> Data schema and evolution - how the data is shaped and may change
- Each source system will have an increased load from the connections and the reqeusts to extract the data.

## Solution
- Decoupling using Apache Kafka
- Now the source systems responsible for sending data.
- --- Its called producing for producing data for Apache Kafka
- -----> So now Apache Kafka is going to have a data stream of all your data, of all your source systems within it and your target systems if they ever need to receive data from your systems they will actually tap into the data of Apache Kafka.
- -------> Because Kafka is meant to actually receive data from your systems, they will actually tap into the data of Apache Kafka, because Kafka is mean to receive and send data.
- -------> So you target systems are now consuming from Apache Kafka and everything is more *manageable/maintainable* and more *scalable*

What can source systems be for example?????
- They can be website events, pricing data, financial transaction or user interactions.
- All these things create data streams.
- That means data created in real time, and it is sent to apache kafka.

What can target systems be?
- Databases, analytics streams, email systems and audit systems.

This will lead to an architecture

## Why kafka
Kafka was created by LinkedIn.
Was created as an open source project but is now maintained by Confluent, IBM, Cloudera, LinkedIn.......more?
It's distributed, has a resilient architecture and is fault-tolerant
That means you can upgrade kafka and can do kafka maintenance without taking the whole system down.
Kafka is good for horizontal scalability, this means you can add brokers over time into your kafka cluster, and you can scale to hundreds of brokers.
-- Kafka has huge scale for message throughput, so you have millions of messages per second. (Think twitter)
-- Its also high performance, so you have really low latency, sometimes it's measured in less than 10 milliseconds.
-----> This is why we call Apache Kafka a real time system (double check this).
-----> Kafka also has really wide adoption across the world/industries

2,000 firms using Kafka publicly, 80% of the fortune 100 are using Apache Kafka.
---> Big names are using it. Don't necessarily have to be a big corporation to use it.

## Use cases, how is Apache Kafka used
- Used as a messaging system 
- Activity tacking system.
- Gather metrics from many different locations
- Application Logs gathering
- Stream processing (with the Kafka Streams API for example)
- It's used to decouple system dependencies and microservices
- It has integration with big data technologies such as Spark, Flink, Storm Hadoop.
- Micro-services pub/sub

## Use cases
- Netflix uses Kafka to apply recommendations in real-time while you're watching TV shows
- Uber uses Kafka to gather user, taxi and trip data in real-time to compute and forcast demand and compute surge pricing in real-time
- LinkedIn uses Kafka to prevent spam, collect user interactions to make better connection recommendations in real time.

*kafka is only used as a transportation mechanism*

---
# Additional Areas
- Kafka Connect
- Kafka Streams
- ksqlDB
- Confluent Components
- Kafka Security
- Kafka Monitoring and Operations
- Kafka Cluster Setup