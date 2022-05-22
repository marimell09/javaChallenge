# Java Challenge
Java CRUD implementation for Customer Service.

### Challenge
Implement an API CRUD for basic Customer Services using Java. It should contain a GET method to return the list of customers using pagination.
The Customer registration may be simple, with basic data, and it must run locally.

### Implementation
For the challenge, it was implement a BASIC CRUD API for Customer and it's roles.
For the anthentication and authorization, it was used Basic Auth, instead of JWT, in order to be a simpler implementation.
Also, the libs and methods used for the authentication and authorization are currently deprecated, so in the enhancaments, it was included an item for this matter.
It was used Postman to test the routes, postgree for the database and everything was run locally.

#### `/customers`

- `GET /customers?size=&page=` - obtain customers list with pagination
- `POST /customers` - create customer
- `PUT /customers/{username}` - update customer information
      Only allowed to update firstname and lastname
- `POST /customers/roles` - attributes a role to a customer
      Only allowed to customer with role ADMIN

#### `/roles`

- `GET /roles` - get list of roles without pagination
      Only allowed to customer with role ADMIN
- `POST /roles` - create new role
      Only allowed to customer with role ADMIN

### Routes
The following Route documentation can be found at Postman: https://www.getpostman.com/collections/59db24e7eb4ad817ada7

### Enhancements
+ Delete Route
+ Get by Id
+ Junit tests
+ JWT full implementation
+ Authorization enhancement lib deprecated
+ Authentication using JWT
+ Authorization for update and delete only for allowed customer (owner or with role admin)
+ Spring Cloud Gateway
+ Docker upgrade
+ AWS Cognito enhancement
+ AWS API Gateway
+ Load balancer
+ EC2 Deploy




