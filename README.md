This is a project that I built as a task for ClearScore.
I used Spring Boot with Java,
as well as Intellij Idea as an IDE and Postman to test it.


# Running this project

## Prerequisites

- Set the environmental variables:

  - HTTP_PORT (e.g. : 8080)
  - CSCARDS_ENDPOINT (e.g. : https://app.clearscore.com/api/global/backend-tech-test/v1/cards)
  - SCOREDCARDS_ENDPOINT (e.g. : https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards)

- Have java JDk v17 installed

## Running

Run `start.sh` from the project repository.

Do note, this script cleans the project and recompiles the software.
To simply run the jar after installation, you can use `make run`

You can also use `make test` to run the tests.

# Design

I used basic MVC ideas to build this project, with clear 
modules such as controllers, exceptions, models and services.
