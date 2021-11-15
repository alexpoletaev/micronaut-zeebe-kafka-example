### This project introduces communication between two services (order and payment) through Zeebe and Kafka ###

#### Requirements: ####
* Gradle 7.0+


#### Steps to execute an example: ####
1. Run docker compose
    ```
    cd docker
    docker-compose up
    ```
2. Run `order` service
    ```
    gradle :order:run
    ```
3. Run `payment` service
    ```
    gradle :payment:run
    ```
4. Start bpmn process by executing rest endpoint
    ```
    POST http://localhost:8080/process/order-payment-example/start
    ```
   Payload:
    ```
    {
        "cardNumber": 1235,
        "amount": "35.66"
    }
    ```
