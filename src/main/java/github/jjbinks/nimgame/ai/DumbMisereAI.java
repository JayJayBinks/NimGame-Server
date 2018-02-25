package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DumbMisereAI extends MisereAI {
    public static final Logger LOGGER = LoggerFactory.getLogger(DumbMisereAI.class);
    public static final AIDifficulty DIFFICULTY = AIDifficulty.DUMB;


    protected int getNumberOfMatchesToRemove(NimGameInstance nimGameInstance) {
        GameConfiguration gameConfiguration = nimGameInstance.getGameConfiguration();
        GameProperties gameProperties = nimGameInstance.getGameProperties();
        int max = gameProperties.getMatchesRemaining() < gameConfiguration.getMaxMatchesToTake()
                ? gameConfiguration.getMinMatchesToTake() : gameConfiguration.getMaxMatchesToTake();
        int range = (max - gameConfiguration.getMinMatchesToTake()) + 1;
        return (int) (Math.random() * range) + gameConfiguration.getMinMatchesToTake();
    }
}
