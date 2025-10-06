# What is a Load Balancer?

A **load balancer** acts as a "traffic cop" for your servers. It sits in front of your backend servers and distributes incoming network traffic across multiple servers in a server farm or pool. The primary goal is to prevent any single server from becoming overwhelmed, ensuring a smooth and responsive experience for users.

By spreading the load, a balancer dramatically improves the **scalability**, **availability**, and **performance** of applications.

---

## Key Goals of Load Balancing

* **Scalability:** Allows you to handle more traffic by simply adding more servers to the pool without changing your application's entry point.
* **High Availability (Redundancy):** If one server goes down, the load balancer automatically redirects traffic to the remaining healthy servers. This prevents downtime and ensures the application stays online.
* **Performance:** By distributing requests, it reduces the response time for users and prevents server overload, leading to a faster and more reliable service.

## How It Works

The process is simple but effective. Instead of clients connecting directly to a specific server, they connect to the load balancer, which then intelligently forwards their request to one of the available backend servers.

```text
                      +------------+
                      |  Server 1  |
                      +------------+
                      |
Client Request -----> [ Load Balancer ] -----> +------------+
                      |                        |  Server 2  |
                      +------------+
                      |
                      +------------+
                      |  Server 3  |
                      +------------+
