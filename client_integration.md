## Integrating a New Server to the API-Gateway

### Scenario1: Horizontally scale existing service
In order to acheive load balancing we should have multiple servers to which the traffic can flow through.
We will have to run these servers on different ports(eg 8082, 8083)(any port of your choice)

To run the EurekaClient((**Pls do not confuse this with user or the request which external client sends**), 
Eureka client is a service in itself which contains the endpoint(here it is GREETINGS-SERVICE)) on a different port run the following command
**mvn spring-boot:run -Dserver.port=8083**(here 8083 is the port on which you run the client)

Post running all the Eureka clients(servers)(the GREETINGS-SERVICE in this case), the servers will start accepting the requests in RoundRobin fashion
So if we have 3 services SERVER1, SERVER2, SERVER3
Requests will follow the below order SERVER1, SERVER2, SERVER3, SERVER1, SERVER2, SERVER3 ....

### Scenario2: Integrate another service with same API-GATEWAY
Say the service name is **HELLO-WORLD-SERVICE** and the endpoint point is **/hello-world**
The application.yml file in API-GATEWAY will have to be modified as follows
under
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: hello_world_route # A unique name for the routes
          uri: lb://HELLO-WORLD-SERVICE
          predicates:
            - Path=/hello-world/**
```

And in the new servers application.yml file(make sure the name matches with service name in uri field in API-GATEWAYs application.yml file
```yaml
spring:
  application:
    name: HELLO_WORLD-SERVICE
```

**Note: Make sure the file is in src/main/resources path in the application and the name is application.properties**





