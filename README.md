# _World Windsufer's Weather Application_

## How to check the operation of the application?

### Weather for all selected locations

- launch your web browser and enter the website address:
    -     https://salty-hamlet-75396-7f564a7d82dc.herokuapp.com/api/forecasts

### The best location for windsurfing within the designated weather parameters

- launch your web browser and enter the website address:
    -     https://salty-hamlet-75396-7f564a7d82dc.herokuapp.com/api/best/forecasts

### {Sending information by e-mail}The best location for windsurfing within the designated weather parameters:

- launch your web browser and enter the website address but you need to change your email address:
   -     https://salty-hamlet-75396-7f564a7d82dc.herokuapp.com/api/best/forecasts?email=bartek.anczewski10@gmail.com
## How to enable the application?

- run intellij
- selecting from the file toolbar
- then new
- then Project from Version Control...
- pasting into the URL bar : https://github.com/redred0011/Weather_Service.git
- then pressing clone
- success, you have downloaded the application to your device!

## Technologies used

- Java 17
- Spring Boot 3.2.2
- Spring Web (spring-boot-starter-web)
- Spring Boot DevTools (spring-boot-devtools)
- Lombok (org.projectlombok)
- Spring Boot Starter Test (spring-boot-starter-test)
- Spring Cloud OpenFeign (spring-cloud-starter-feign)
- H2 Database (com.h2database:h2)
- Liquibase (org.liquibase:liquibase-core)
- Spring Boot Starter Cache (spring-boot-starter-cache)
- Spring Boot Starter Mail (spring-boot-starter-mail)
- Caffeine Cache (com.github.ben-manes.caffeine)
- Spring Cloud Dependencies (org.springframework.cloud:spring-cloud-dependencies)
- Maven

## Description

#### Conceptualization and Design:

- Decide on the MVC (Model-View-Controller) architectural pattern to structure the application. This decision helps
  separate concerns, making the application easier to manage and scale.
- Sketch out the main components of the application: the model for the weather data, the view for presenting information
  to the user, and the controller for handling application logic.

#### Setting Up the Project Environment:

- Initialize a new Spring Boot project by selecting dependencies for Spring Web, Spring Cloud OpenFeign, Spring Boot
  Actuator, and any other relevant dependencies.
- Configuring the project structure to reflect MVC layers: creating packages for controllers, services (within the
  controller layer for business logic), models, and services.

#### Model Development:

- Definition of the Weather, Forecast, and WeatherScore classes in the model package. These classes represent the data
  structure for weather forecasts, individual forecast details and calculated results for windsurfing conditions,
  respectively.
- Implement Lombok annotations (@Getter, @Setter) to reduce template code for model classes.

#### Controller and Service Layer Implementation:

- Implement WeatherController in the controller package to handle HTTP requests. This controller should manage routes
  such as /api/forecasts to retrieve weather forecasts and /api/best/forecasts to find the best windsurfing conditions.
- Implementation of the WeatherService and EmailService classes in the service package. WeatherService will handle the
  logic of retrieving weather data and determining the best windsurfing locations. EmailService will manage sending
  notifications to users about the best conditions.

#### Integration with External Weather API:

- Using Spring Cloud OpenFeign to declare a WeatherClient interface, allowing easy RESTful communication with an
  external weather API.
- Configure FeignClient with the base URL and API key of the weather service. Implement methods in WeatherClient to
  retrieve weather forecasts.

#### Asynchronous Processing Configuration:

- Configure asynchronous method execution to enable non-blocking operations, especially when sending email messages. Use
  @EnableAsync in your configuration class.

#### Application Configuration:

- Use Spring Boot application properties (application.yml or application.properties) to configure external API details,
  mail server settings, and caching behavior.
- Enable caching and configure cache properties for frequently accessed data, such as weather forecasts, to optimize
  performance.

#### Testing and Validation:

- Writing unit and integration tests for application components. Take advantage of Spring Boot testing support to mock
  external services and check your application's behavior under various conditions.
- Test your application manually to ensure that endpoints return expected results, asynchronous operations such as email
  notifications work correctly, and the caching mechanism effectively reduces the load on external services.

#### Deployment Preparation:

- Ensuring the application is configured securely, including encryption of sensitive properties and implementing proper
  error handling and logging.

### This detailed guide covers the development stages of the World Windsurfer weather application, focusing on the MVC design pattern, integration with external services, asynchronous processing, and practical deployment and monitoring considerations.