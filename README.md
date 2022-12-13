# Spring Data Lab

## Learning Goals

- Practice creating a web application that connects to a data source using the
  Spring Framework.
- Connect an application to a PostgreSQL database.
- Create a DTO and `@Entity` class.
- Create a `@Repository` interface.
- Modify a `@Service` class.
- Use Postman as an API client tool to test.

## Prerequisite

For this lab, you will need to create a new database in pgAdmin4 and create a
new table.

Open up pgAdmin4 and create a new database called **gym_db**:

![create-gym-db](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/create-gym-db.png)

Notice in the `resources` directory there is a file called `data.sql`:

```sql
DROP TABLE IF EXISTS member;

CREATE TABLE member (
    id INTEGER PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    location TEXT
);
```

This file should run automatically when the Spring Boot application starts up,
so there shouldn't be any need to open this file up in the query tool in
pgAdmin4.

## Instructions

You just got hired to help out a large gym corporation that has gym locations
all over the country! They need help managing their members, and have asked you
to build a web application that connects to a data source to perform some
CRUD operations.

Follow the given instructions and tips:

- Create a `MemberDTO` class.
  - The DTO will need to have the following fields:
    - First name
    - Last name
    - The location of their home gym (i.e., Newport, RI)
  - Each of the fields should have a getter and setter method. Consider using
    the `@Data` Lombok annotation.
  - The first name and last name fields should be non-null and not empty.
  - Creating this class should be the first step, as everything else will
    expand off of using this class.
- Create a `Member` entity class.
  - The entity will need to have the following fields:
    - Generated integer ID
    - First name
    - Last name
    - The location of their home gym (i.e., Newport, RI)
  - Each of these fields should have a getter and setter method.
  - This class should consist of a no argument constructor.
  - Consider using the Lombok annotations we have learned about.
  - Creating this class should be the second step.
- Create a `MemberRepository` interface.
  - This should extend the `CrudRepository`.
  - Create a method using the query builder to find a member based on their
    last name.
- Modify the `MemberSerivce` class.
  - The `MemberController` and `MemberService` classes already have some starter
    code for you.
  - As you work each of these methods below, un-comment the corresponding method
    in the controller class to test. It is recommended you work on one method at
    a time, test, and then move onto the next method.
  - The service class will have the following methods:
    - `addMember()`: This will take in a `MemberDTO` object to be posted to the
      data source and will return a `String`.
    - `getMember()`: This will take in a `String` object with the last name of
      a member to perform a lookup. Return a `MemberDTO`.
    - `updateMember()`: This will take in a member ID and a `MemberDTO`. Update
      the member with the associated ID with the fields in the `MemberDTO` that
      is passed in. Return the updated member as a `MemberDTO`.
    - `deleteMember()`: This will take in a member ID. Delete the member from
      the data source with the associated ID and return a `String` stating so.
  - It is recommended to use the `ModelMapper` when performing entity-DTO
    conversions. Don't forget to add the `ModelMapper` as a bean to the
    configuration class (i.e., the `SpringDataLabApplication` class).
- Use Postman to test the application. If you run into a bug or issue, make use
  of the debugger in IntelliJ and the Java Visualizer tool.

## Project Structure

The Spring Boot project has already been initialized for you. Consider the
following project structure:

```text
├── CONTRIBUTING.md
├── HELP.md
├── LICENSE.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── springdatalab
    │   │               ├── SpringDataLabApplication.java
    │   │               ├── controller
    │   │               │   └── MemberController.java
    │   │               ├── dto
    │   │               │   └── MemberDTO.java
    │   │               ├── entity
    │   │               │   └── Member.java
    │   │               ├── repository
    │   │               │   └── MemberRepository.java        
    │   │               └── service
    │   │                   └── MemberService.java
    │   └── resources
    │       ├── application.properties
    │       ├── data.sql    
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── org
                └── example
                    └── springdatalab
                        └── SpringDataLabApplication.java
```

## Starter Code

Some starter code has already been written for you. Consider the following:

### Application Properties

The `application.properties` file is all set to connect to the PostgreSQL
gym_db. Remember to enter your password in the password property!

```properties
spring.datasource.url= jdbc:postgresql://localhost:5432/gym_db
spring.datasource.username= postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
```

### Service Class

As stated in the instructions, the `MemberSerivce` class already has a template
of the methods for you that you need to write:

```java
package com.example.springdatalab.service;

import com.example.springdatalab.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {


    public String addMember(MemberDTO memberDTO) {
        // Write the method to add the member to the data store
        return "New member has been added!";
    }

    public MemberDTO getMember(String lastName) {
        // Write method to get the member associated with the last name
        return null;
    }

    public MemberDTO updateMember(Integer id, MemberDTO memberDTO) {
        // Write method to update the member associated with id
        return null;
    }

    public String deleteMember(Integer id) {
        // Write method to delete the member associated with id
        return String.format("Member with ID %d was deleted", id);
    }
}
```

### Controller Class

As stated in the instructions, the `MemberController` class already has the
starter code in there for you. Currently, most of the methods are commented out.
It is suggested that you work each method individually in the `MemberSerivice`
class and then uncomment as you go in the controller class.

```java
package com.example.springdatalab.controller;

import com.example.springdatalab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Uncomment the section below little-by-little as you work through these

/*
    @PostMapping("/member")
    public ResponseEntity<String> addMember(@Valid @RequestBody MemberDTO member) {
        String status = memberService.addMember(member);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/member/{lastName}")
    public MemberDTO getMember(@PathVariable String lastName) {
        return memberService.getMember(lastName);
    }

    @PutMapping("/member/{memberId}")
    public MemberDTO updateMember(@PathVariable Integer memberId, @RequestBody MemberDTO member) {
        return memberService.updateMember(memberId, member);
    }

    @DeleteMapping("/member/{memberId}")
    public String deleteMember(@PathVariable Integer memberId) {
        return memberService.deleteMember(memberId);
    }
*/

}
```

## Example Output

Consider the example outputs:

### POST Request

Request URL: `http://localhost:8080/member`

Request Body:

```json
{
  "firstName":"Chris",
  "lastName":"Traeger",
  "location": "Pawnee, IN"
}
```

![Postman-post-request](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/postman-add-member.png)

![pgAdmin-add-member](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/pgadmin-lab-select-1.png)

### GET Request

Request URL: `http://localhost:8080/member/Traeger`

![Postman-get-request](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/postman-get-member.png)

### PUT Request

Request URL: `http://localhost:8080/member/1`

Request Body:

```json
{
  "firstName":"Chris",
  "lastName":"Traeger",
  "location": "Ann Arbor, MI"
}
```

![Postman-put-request](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/postman-update-member.png)

![pgAdmin-update-member](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/pgadmin-lab-select-2.png)

### DELETE Request

Request URL: `http://localhost:8080/member/1`

![Potman-delete-request](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/postman-delete-member.png)

![pgAdmin-empty-table](https://curriculum-content.s3.amazonaws.com/spring-mod-1/data-lab/pgadmin-delete.png)
