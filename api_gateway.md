# The Need for an API Gateway

An **API Gateway** acts as a single entry point for all client requests to the various microservices in an application. Think of it as a restaurant's front-of-house manager: instead of customers running directly to different chefs (microservices) for each part of their meal, they give their entire order to one person, who then coordinates with the kitchen.

In a microservices architecture, you might have dozens of small, independent services. Without a gateway, the client application (e.g., a mobile app or a website) would have to manage requests to all of them directly.

---

## Problems Without an API Gateway

Managing direct client-to-microservice communication creates several significant challenges:

* **Complexity:** The client has to know the address of every single microservice. If a service's location changes, the client application must be updated.
* **Security:** Each microservice would need to handle its own security, such as authentication, authorization, and SSL. This is repetitive and hard to maintain.
* **Multiple Round Trips:** A single page load on a website might require data from many different services, forcing the client to make numerous separate requests, which is slow and inefficient.
* **Mixed Protocols:** Some services might use REST, while others use gRPC or another protocol. The client would need to be able to speak all of these languages.

## How an API Gateway Solves These Problems

An API Gateway sits between the client and the microservices, acting as a reverse proxy to simplify communication and centralize common tasks.

```text
                               +-------------------+
                               |    Service A      |
                               +-------------------+
                               |
Client(s) <--> [ API Gateway ] --+-------------------+
                               |    Service B      |
                               +-------------------+
                               |
                               +-------------------+
                               |    Service C      |
                               +-------------------+
