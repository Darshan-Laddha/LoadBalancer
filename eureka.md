# What is Eureka Server and Discovery Client?

In a microservices architecture, Eureka acts like a dynamic phone book for all your services. It consists of two main components: the **Eureka Server** (the phone book itself) and **Eureka Discovery Clients** (the people who use and update the phone book). This system allows services to find and communicate with each other without needing to know their exact network locations, which can change frequently.

The entire mechanism is known as **Service Discovery**.

---

## The Eureka Server (The Phone Book) 📖

The **Eureka Server** is a standalone application that serves as a central registry. Its only job is to keep a list of all available microservices.

* **Service Registration:** When a new microservice (a "client") starts up, it registers itself with the Eureka Server, providing information like its name, IP address, and port number.
* **Service Registry:** It maintains a complete, up-to-date list of all registered services and their current statuses.
* **Sends Heartbeats:** The server expects to receive regular "heartbeats" (pings) from each client. If it stops receiving heartbeats from a service, it assumes that service is no longer available and removes it from the registry.

## The Eureka Discovery Client (The User) 📲

The **Eureka Discovery Client** is a lightweight library included in each of your individual microservices. It handles all communication with the Eureka Server.

* **Registers Itself:** On startup, the client contacts the Eureka Server to get added to the registry.
* **Sends Heartbeats:** It periodically sends heartbeats to the server to signal that it is still alive and healthy.
* **Discovers Other Services:** When one service needs to communicate with another (e.g., a "User Service" needs to call the "Order Service"), it asks the Eureka Server for the current address of the "Order Service." It then caches this information locally.
