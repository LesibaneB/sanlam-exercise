# Solution Explainer

## Project structure

The project follow a package-by-feature based separation of concerns and within that feature a further separation of concerns is based on what each file is trying to achieve.

The structure looks like the following;

    - bankaccount
        This is the package that will house all code concerned with the bankaccount feature of our system
        - constants - Holds constant values
        - controller - Holds all the controllers that expose the endpoints for the bank account features. These are kept very lean
        - dto - Holds classes that will represent data going in and out of the application
        - entity - Holds classes that represent our database
        - event - Holds classes that represent events that are published by the event service
        - exception - Holds handlers and classes that represent exceptions that we expect for this specific feature
        - mapper - Holds classes we use to map to and from our DTos and Entities
        - repository - Holds JPA repositories to interact with our database
        - service - Holds classes that contain all our business logic
    - config
        This package will contain non-specific configurations that might be needed by one or more features, in the current case it hold the configuration of the AWS SNS client
    - exception
        This package contains global exception handling for all possible features of the application
    - service
        This package contains non-feature-specific services that we might need for the features. Right now it contains the EventService which uses the AWS SNS client to publish events

## Library usages

- The Lombok library is used to avoid being verbose by making our classes smaller in cases where we might need setters and getters by using Annotations.
- The MapStruct library is used to avoid being verbose by helping write less code while mapping from our DTOs to Entities and vice versa.

