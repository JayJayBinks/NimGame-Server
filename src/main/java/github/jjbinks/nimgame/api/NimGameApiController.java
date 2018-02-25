package github.jjbinks.nimgame.api;

import github.jjbinks.nimgame.ai.AI;
import github.jjbinks.nimgame.ai.AIController;
import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.api.exceptions.BadRequestException;
import github.jjbinks.nimgame.configuration.ProjectProperties;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.persistence.GameStorage;
import github.jjbinks.nimgame.util.GameConfigurationFactory;
import github.jjbinks.nimgame.util.GameFactory;
import github.jjbinks.nimgame.util.GameInstanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
//TODO write tests
public class NimGameApiController implements NimGameApi {
    public static final Logger LOGGER = LoggerFactory.getLogger(NimGameApiController.class);

    @Autowired
    private GameStorage gameStorage;

    @Autowired
    private AIController aiController;

    @Autowired
    private ProjectProperties projectProperties;

    @Autowired
    private GameFactory gameFactory;

    @Autowired
    private GameConfigurationFactory gameConfigurationFactory;

    @Autowired
    private GameInstanceUtil gameInstanceUtil;


    public ResponseEntity<NimGameInstance> createNewInstanceOfGameMode(@PathVariable("game_mode_id") String gameModeId,
                                                                       @RequestBody(required = false) GameConfiguration gameConfiguration) throws ApiException {
        LOGGER.debug("Creating new game instance");
        LOGGER.debug("Verifying config.");
        gameConfiguration = gameConfigurationFactory.verifyAlternateConfig(NimGameMode.fromValue(gameModeId), gameConfiguration);
        LOGGER.debug("Verification successfull.");
        NimGameInstance nimGameInstance = gameFactory.createNewGame(gameConfiguration);

        LOGGER.debug("Successfully created " + nimGameInstance);
        LOGGER.debug("Storing instance " + nimGameInstance);
        gameStorage.store(nimGameInstance);
        LOGGER.debug("Successfully stored, sending to client " + nimGameInstance);
        return new ResponseEntity<>(nimGameInstance, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteInstanceOfGameMode(@PathVariable("game_mode_id") String gameModeId,
                                                         @PathVariable("instance_id") Long instanceId) throws ApiException {
        LOGGER.debug(String.format("Attempting to delete game instance %s %s", gameModeId, instanceId));
        NimGameInstance nimGameInstance = gameStorage.find(NimGameMode.fromValue(gameModeId), instanceId);
        if (nimGameInstance.getGameEndedProperties() == null) {
            throw new BadRequestException("BAD_REQUEST_001", "Game is still running, can not be deleted");
        }
        gameStorage.remove(nimGameInstance);
        LOGGER.debug("Successfully deleted " + nimGameInstance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<NimGameMode>> getGameModes() {
        LOGGER.debug("Sending a list of all game modes");
        return new ResponseEntity<>(Arrays.asList(NimGameMode.values()), HttpStatus.OK);
    }

    public ResponseEntity<NimGameInstance> getInstanceOfGameMode(@PathVariable("game_mode_id") String gameModeId,
                                                                 @PathVariable("instance_id") Long instanceId) throws ApiException {
        LOGGER.debug(String.format("Attempting to retrieve game instance %s %s", gameModeId, instanceId));
        NimGameInstance nimGameInstance = gameStorage.find(NimGameMode.fromValue(gameModeId), instanceId);
        return new ResponseEntity<>(nimGameInstance, HttpStatus.OK);
    }

    public ResponseEntity<Map<Long, NimGameInstance>> getAllInstancesOfGameMode(@PathVariable("game_mode_id") String gameModeId) throws ApiException {
        LOGGER.debug("Sending a list of all games of game type " + gameModeId);
        return new ResponseEntity<>(gameStorage.getAllGames(NimGameMode.fromValue(gameModeId)), HttpStatus.OK);
    }

    public ResponseEntity<NimGameInstance> updateInstanceOfGameMode(@PathVariable("game_mode_id") String gameModeId,
                                                                    @PathVariable("instance_id") Long instanceId,
                                                                    @RequestBody NimGameInstance gamePatchRequest) throws ApiException {
        NimGameInstance gameInstance = gameStorage.find(NimGameMode.fromValue(gameModeId), instanceId);

        LOGGER.debug("Updating game instace " + gameInstance + " " + gamePatchRequest);
        gameInstanceUtil.patch(gameInstance, gamePatchRequest);
        LOGGER.debug("Patched succesfully " + gameInstance + " " + gamePatchRequest);
        AIDifficulty aiDifficulty = gameInstance.getGameConfiguration().getAiDifficulty();
        if (aiDifficulty != null &&
                gameInstance.getGameEndedProperties() == null) {
            LOGGER.debug("Making AI Move " + aiDifficulty + " " + gameInstance);
            AI.delayedMove(aiController.getAI(NimGameMode.fromValue(gameModeId), aiDifficulty), gameInstance);
        }
        return new ResponseEntity<>(gameInstance, HttpStatus.OK);
    }

}


