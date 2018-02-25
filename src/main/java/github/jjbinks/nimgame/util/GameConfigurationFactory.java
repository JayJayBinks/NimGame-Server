package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.BadRequestException;
import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.configuration.ProjectProperties;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.model.Player;
import org.springframework.beans.factory.annotation.Autowired;

import static github.jjbinks.nimgame.model.NimGameMode.MISERE;

public class GameConfigurationFactory {

    @Autowired
    private ProjectProperties projectProperties;

    public GameConfiguration createGameConfiguration(NimGameMode nimGameMode) throws GameModeNotFoundException {
        switch (nimGameMode) {
            case MISERE:
                return createMisereConfiguration();
            default:
                throw new GameModeNotFoundException(nimGameMode);
        }
    }

    public GameConfiguration verifyAlternateConfig(NimGameMode gameMode, GameConfiguration gameConfiguration) throws BadRequestException, GameModeNotFoundException {
        switch (gameMode) {
            case MISERE:
                return verifyMisereConfiguration(gameMode, gameConfiguration);
            default:
                throw new BadRequestException("BAD_REQUEST008", "Game mode needs to be present to create a game.");
        }
    }

    private GameConfiguration verifyMisereConfiguration(NimGameMode nimGameMode, GameConfiguration gameConfiguration) throws BadRequestException, GameModeNotFoundException {
        if (gameConfiguration.getMinMatchesToTake() != null ||
                gameConfiguration.getMaxMatchesToTake() != null ||
                gameConfiguration.getNumberOfMatches() != null ||
                gameConfiguration.getPlayerFirstMove() != null) {
            throw new BadRequestException("BAD_REQUEST007", "To alter this game configuration is not supported at the moment.");
        }

        GameConfiguration alteredVerifiedConfig = createGameConfiguration(nimGameMode);
        AIDifficulty aiDifficulty = gameConfiguration.getAiDifficulty();
        if (aiDifficulty != null) {
            alteredVerifiedConfig.setAiDifficulty(aiDifficulty);
        }
        alteredVerifiedConfig.setGameMode(nimGameMode);
        return alteredVerifiedConfig;
    }

    private GameConfiguration createMisereConfiguration() {
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfiguration.setGameMode(MISERE);
        gameConfiguration.setAiDifficulty(AIDifficulty.DUMB);
        gameConfiguration.setMaxMatchesToTake(projectProperties.getMisereMaxMatchesToTake());
        gameConfiguration.setMinMatchesToTake(projectProperties.getMisereMinMatchesToTake());
        gameConfiguration.setNumberOfMatches(projectProperties.getMisereNumberOfMatches());
        gameConfiguration.setPlayerFirstMove(Player.HUMAN);
        return gameConfiguration;
    }
}
