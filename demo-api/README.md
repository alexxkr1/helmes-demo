# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.6/maven-plugin/build-image.html)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## **API Swagger docs**
1. http://localhost:6060/swagger-ui/index.html

### Database

# Database Schema (SQLite)

1. It is only needed if you want to reset the database to the initial state.
2. The dump file is located at `src/main/resources/data/database_schema.sql`.
3. You can use any SQLite client to import the Schema file and reset the database.


# Database Dump (SQLite)

1. It is only needed if you want to get database current state data.
2. The dump file is located at `src/main/resources/data/database_dump.sql`.
3. You can use any SQLite client to import the dump file and reset the database.

# Database Seeder 

1. Database seeder is implemented in the `DatabaseSeeder` class located at `src/main/java/com/alex/demo/config/DatabaseSeeder.java`.
2. It will load `sectors.json` and populate the database with initial sectors.
3. If there is any existing data in sectors table, seeding will not happen.


# IntelliJ Setup

1. Open the project in **IntelliJ IDEA**.
2. Make sure **Maven** is enabled for the project. IntelliJ should detect `pom.xml` automatically.
3. Build the project using Maven:
    ```bash
    ./mvnw clean install      # MacOS
    mvnw.cmd clean install    # Windows
    ```
4. Create a Run Configuration:
    - Go to **Run -> Edit Configurations -> + -> Application**.
    - Name it `Run`.
    - Select the main class: `com.alex.demo.DemoApiApplication`.
    - Apply and Save.

# Running Test with IntelliJ

1. Go to **test -> java -> com.alex.demo -> `SubmissionServiceUnitTest`**.


# Application Properties

Update `src/main/resources/application.properties` with your environment values:

```properties
server.port=6060
spring.web.cors.allowed-origins=http://localhost:4200
```
