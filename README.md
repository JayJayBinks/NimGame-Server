
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
http://localhost:8080/nim_game/api/v1/swagger-ui.html

Change default port value in application.properties

## Example Game Play (For demo purpose)
Starting a new game:
You can define the Game Configuration (which only supports AI difficulty at the moment):

POST /game_modes/misere/instances

    {
      "aiDifficulty": "smart"
    }

This will create a new game with an Smart AI and an game instance id.

    {
      "instanceId": 1,
      "gameProperties": {
        "matchesRemaining": 13,
        "playerOnMove": "human"
      },
      "gameEndedProperties": null,
      "gameConfiguration": {
        "aiDifficulty": "smart",
        "gameMode": "misere",
        "numberOfMatches": 13,
        "minMatchesToTake": 1,
        "maxMatchesToTake": 3,
        "playerFirstMove": "human"
      }
    }

The instanceId can be used to make a move on the game:

PATCH /game_modes/misere/instances/1

    {
      "gameProperties": {
        "matchesRemaining": 10
      }
    }

The AI will make a subsequent move which is delayed and shown when the game is retrieved.

    {
      "instanceId": 1,
      "gameProperties": {
        "matchesRemaining": 9,
        "playerOnMove": "human"
      },
      "gameEndedProperties": null,
      "gameConfiguration": {
        "aiDifficulty": "smart",
        "gameMode": "misere",
        "numberOfMatches": 13,
        "minMatchesToTake": 1,
        "maxMatchesToTake": 3,
        "playerFirstMove": "human"
      }
    }
The PATCH and GET requests can be repeated until the matchesRemaining field reaches 0, which ends the game.