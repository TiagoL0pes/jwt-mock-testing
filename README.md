# jwt-mock-testing
Mock jwt payload for your tests

**WARNING:** this API changes the structure of the token and invalidates its signature, just use it to manipulate data for testing.

## How to use

This application provides three endpoints to change or remove items from a valid JWT token, send token in Authorization header and the new token will be returned in body payload.

Run ``mvn clean install`` in to root project to compile application and run ``java -jar target/jwt-testing-0.0.1-SNAPSHOT.jar`` to start application.

## Endpoints:

Usage examples are below for running from the command prompt.

### Add a new token item
````
POST => /token/add/item
curl --X -d {\"key\":\"newKey\",\"value\":\"newValue\"} \ 
-H "Content-Type: application/json" \ 
-H "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" \ 
localhost:8080/token/add/item
````

### Add a new token list
````
POST => /token/add/item
curl --X -d {\"key\":\"newKey\",\"values\":[\"item0\",\"item1\"]} \ 
-H "Content-Type: application/json" \ 
-H "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" \ 
localhost:8080/token/add/list
````

### Add a new token item
````
POST => /token/add/item
curl --X -d {\"key\":\"name\"} \ 
-H "Content-Type: application/json" \ 
-H "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" \ 
localhost:8080/token/remove/item
````