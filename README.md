# Nim Game


## Overview  
This is the an implementation of the [Nim game](https://en.wikipedia.org/wiki/Nim). 
The curent interface only supports the game mode misere with a single row of 13 matches.

The server was implemented with a REST interface in Java using the SpringBoot framework.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

Start your server as an simple java application

mvnw spring-boot:run

Or compile the application and run the jar.

You can view the api documentation in swagger-ui by pointing to  
http://localhost:8080/swagger-ui.html

Change default port value in application.properties