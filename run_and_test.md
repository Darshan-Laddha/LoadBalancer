## Prerequisites
1) Java 8 
2) IDE to run the code
## Steps to run and test the entire functionality
1) Clone the LoadBalancer repo into you faviourte IDE
2) We have 3 repos inside this EurekaServer, EurekaClient and API-Gateway
3) Start the eureka server first and make sure its up and running. Once its up verify eureka server dashboard should load at post 8761(http://localhost:8761/)
   At this point of time we will not see any registered clients
4) Now start the API Gateway and make sure its up and running.
5) Verify The API Gateway should be one of the registered clients of The Eureka server on (http://localhost:8761/)
6) Similarly start the Discovery Client as well and verify it in the registered client
7)Once all the 3 services are up and running
Hit the below curl
 **curl --location 'http://localhost:9000/greeting/accept-greetings'**
 and it should return a 200 OK response.
   
