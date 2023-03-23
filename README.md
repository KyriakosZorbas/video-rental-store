# Movie Store Api

## Description
This is a sample server for a video rental store for managing the rental administration.

## Prerequisites

* Java 8

## Installation

There are two approaches:

1. Intellij or Eclipse IDE: Open project as Maven project and start main class :
   * com.kyriakos.Application
2. Use maven :
   * Run in project dir: $ mvn clean install
   * Navigate to target dir and run: $ java -jar exchange-<VERSION>.jar

Exchange API is built with Maven. Taken that into account, regular Maven tasks such as clean, compile, test tasks for development and testing can be used.

## Documentation
The API Testing interactive documentation has been created with [Swagger](https://swagger.io/). After starting the application Swagger can be accessed from the following URL :
*  http://localhost:8080/swagger-ui.html

## Endpoints
This API implements the following operations :

Method | Endpoint | Description |
| ------ | ------ | ------ |
| POST | /movie | Add a movie in store's database. |
| GET | /movie/{movieId} | Find movie by id. |
| DELETE | /movie/{movieId} | Delete movie by id. |
| GET | /movies | Get all movies that exist in the store's database. |
| POST | /movies/rent | Renting one or several films and calculating the price. |
| POST | /movie/return | Returning films and calculating possible surcharges. |


#### Example
Request URL : http://localhost:8080/api/movies/rent

Input for the POST request is the following :
```sh
{
  "rent": [
    "Matrix",
    "Avatar"
  ]
}
```

The response will be the following :
```sh
{
  "movies": [
    {
      "name": "Matrix",
      "type": "regular",
      "delay": 0,
      "price": 30
    },
    {
      "name": "Avatar",
      "type": "new",
      "delay": 0,
      "price": 40
    }
  ],
  "totalPrice": 70,
  "currency": "SEK"
}
```

## Tests

The project has been test with the help of the following :

* junit
* MockMvc

The tests are located in :

```sh
\src\test\java\com\kyriakos\
```

### App Structure
The code given is structured as follows :
```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───kyriakos
│   │   │           ├───config
│   │   │           ├───controller
│   │   │           ├───entity
│   │   │           ├───exception
│   │   │           ├───model
│   │   │           ├───repository
│   │   │           ├───service
│   │   │           └───util
│   │   └───resources
│   │       └───static
│   │           └───swagger
│   │               └───api
│   └───test
│       └───java
│           └───com
│               └───kyriakos
│                   ├───controller
│                   ├───repository
│                   ├───service
│                   └───util
└───target
    ├───classes
    │   ├───com
    │   │   └───kyriakos
    │   │       ├───config
    │   │       ├───controller
    │   │       ├───entity
    │   │       ├───exception
    │   │       ├───model
    │   │       ├───repository
    │   │       ├───service
    │   │       └───util
    │   └───static
    │       └───swagger
    │           └───api
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───kyriakos
                ├───controller
                ├───repository
                ├───service
                └───util

```

## Technology stack
This project uses the following technologies:
- Java
- Spring Boot
- H2 Database
- Swagger
- Hybernate
- Maven

## Authentication/Authorisation

If we want to add authentication we can use a third-party tool named [Auth0](https://auth0.com/). Auth0 is an easy to implement, adaptable authentication and authorization platform. Auth0 supports multiple protocols, one of the most suitable for this app's case would be **OAuth 2.0** which allows a user to grant limited access to their resources on one site to another site, without having to expose their credentials.

## Notes

Regarding this solution for conviniece ,the user must also enter the date on which the films were rented in order to determine if there was a delay that would warrant charging him extra.This will be corrected in the subsequent update, which will also include a new feature that will record information about consumers, including what they have rented and when. Also a new feature will be to add Authentication/Authorisation for the API which for simplicity is not implemented in this release.

## Future work
As a future work the following enhancements could be done :

* Add a new feature that will keep info about the customers and what they have rent.
* Dockerize the project and deploy it among H2 database.
* Use other database rather than in-memory database like H2 that we used in this project.
* Split the endpoints so that there are separate ones for administrators and for regular users.
* Add Authentication/Authorisation
* Add more endpoints.
* Add more tests.