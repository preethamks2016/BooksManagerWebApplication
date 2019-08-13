# BooksManager_Maven_dropwizard_Project
A micro-service is created to act like a Library manager.

The app follows REST conventions.
Guice is used for Dependency injection.
The apis app supports the following actions:
- Get a list of all authors
- Get an author by id
- Given an author id return his books
- Get all the books
- Add a new book
- Add a new author

# Library
How to start the Library application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/events-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8082`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
