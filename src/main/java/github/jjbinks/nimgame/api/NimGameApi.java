package github.jjbinks.nimgame.api;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "game_modes", description = "the game_modes API")
public interface NimGameApi {

    @ApiOperation(value = "Creates a new game of given game_mode_id.", notes = "", response = NimGameInstance.class, tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "", response = ApiException.class),
            @ApiResponse(code = 404, message = "", response = ApiException.class)})

    @RequestMapping(value = "/game_modes/{game_mode_id}/instances",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<NimGameInstance> createNewInstanceOfGameMode(@ApiParam(value = "ID of the game mode to get instances for.", required = true) @PathVariable("game_mode_id") String gameModeId,
                                                                @ApiParam(value = "GameConfiguration (atm only AIDifficulty is supported)") @RequestBody(required = false) GameConfiguration gameConfiguration) throws ApiException;


    @ApiOperation(value = "Deletes the instance for instance_id with given game_mode_id.", notes = "Deleting can atm only be done for a finished game.", response = Void.class, tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted successfully.", response = Void.class),
            @ApiResponse(code = 404, message = "", response = ApiException.class)})

    @RequestMapping(value = "/game_modes/{game_mode_id}/instances/{instance_id}",
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteInstanceOfGameMode(@ApiParam(value = "ID of the game mode to get instances for.", required = true)
                                                  @PathVariable("game_mode_id") String gameModeId, @ApiParam(value = "ID of the game instance.", required = true) @PathVariable("instance_id") Long instanceId) throws ApiException;


    @ApiOperation(value = "Get a list of all game modes available", notes = "", response = NimGameMode.class, responseContainer = "List", tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = NimGameMode.class, responseContainer = "List")})

    @RequestMapping(value = "/game_modes",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<NimGameMode>> getGameModes() throws ApiException;


    @ApiOperation(value = "Get the game instance.", notes = "", response = NimGameInstance.class, tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = NimGameInstance.class),
            @ApiResponse(code = 404, message = "", response = ApiException.class)})

    @RequestMapping(value = "/game_modes/{game_mode_id}/instances/{instance_id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<NimGameInstance> getInstanceOfGameMode(@ApiParam(value = "ID of the game mode to get instances for.", required = true)
                                                          @PathVariable("game_mode_id") String gameModeId, @ApiParam(value = "ID of the game instance.", required = true) @PathVariable("instance_id") Long instanceId) throws ApiException;


    @ApiOperation(value = "Get a list of all instances of a game.", notes = "", response = NimGameInstance.class, responseContainer = "List", tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = NimGameInstance.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "", response = ApiException.class)})

    @RequestMapping(value = "/game_modes/{game_mode_id}/instances",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Map<Long, NimGameInstance>> getAllInstancesOfGameMode(@ApiParam(value = "ID of the game mode to get instances for.", required = true) @PathVariable("game_mode_id") String gameModeId) throws ApiException;


    @ApiOperation(value = "Updates the game instance (make a move)", notes = "", response = NimGameInstance.class, tags = {"Game Modes",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = NimGameInstance.class),
            @ApiResponse(code = 400, message = "", response = ApiException.class),
            @ApiResponse(code = 404, message = "", response = ApiException.class)})

    @RequestMapping(value = "/game_modes/{game_mode_id}/instances/{instance_id}",
            produces = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<NimGameInstance> updateInstanceOfGameMode(@ApiParam(value = "ID of the game mode to get instances for.", required = true) @PathVariable("game_mode_id") String gameModeId, @ApiParam(value = "ID of the game instance.", required = true) @PathVariable("instance_id") Long instanceId,
                                                             @ApiParam(value = "Request to update the game state. ATM only updating remaining matches works.", example = "{gameProperties: \n {matchesRemaining: 10} \n }",required = true)
                                                             @RequestBody NimGameInstance gameUpdateRequest) throws ApiException;

}
