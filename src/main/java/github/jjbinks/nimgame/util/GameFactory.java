package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.model.Player;

import static github.jjbinks.nimgame.model.NimGameMode.MISERE;

public class GameFactory {

    public NimGameInstance createNewGame(GameConfiguration gameConfiguration) throws GameModeNotFoundException {
        NimGameMode nimGameMode = NimGameMode.fromValue(gameConfiguration.getGameMode());
        switch (nimGameMode) {
            case MISERE:
                return createMisereGame(gameConfiguration);
            default:
                throw new GameModeNotFoundException(nimGameMode);
        }
    }

    private NimGameInstance createMisereGame(GameConfiguration gameConfiguration) {
        NimGameInstance nimGameInstance = new NimGameInstance();
        nimGameInstance.setInstanceId(GameIdUtil.generateNewGameId(MISERE));
        nimGameInstance.setGameProperties(new GameProperties.Builder()
                .withMatchesRemaining(gameConfiguration.getNumberOfMatches())
                .withPlayerOnMove(Player.fromValue(gameConfiguration.getPlayerFirstMove()))
                .build());
        nimGameInstance.setGameConfiguration(gameConfiguration);
        return nimGameInstance;
    }
}
