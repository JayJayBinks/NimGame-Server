package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.model.Player;
import github.jjbinks.nimgame.util.GameInstanceUtil;
import org.mockito.Spy;

public abstract class AITest {

    protected GameConfiguration gameConfiguration;
    protected NimGameInstance gameInstance;

    @Spy
    protected GameInstanceUtil gameInstanceUtil;

    protected NimGameInstance getNimGame(GameConfiguration gameConfiguration) {
        GameProperties gameProperties = new GameProperties();
        gameProperties.setMatchesRemaining(13);
        gameProperties.setPlayerOnMove(Player.HUMAN);

        NimGameInstance nimInstance = new NimGameInstance();
        nimInstance.setGameConfiguration(gameConfiguration);
        nimInstance.setGameProperties(gameProperties);
        nimInstance.setInstanceId(1L);

        return nimInstance;
    }

    protected GameConfiguration getGameConfiguration() {
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfiguration.setAiDifficulty(AIDifficulty.DUMB.toString());
        gameConfiguration.setGameMode(NimGameMode.MISERE.toString());
        gameConfiguration.setMaxMatchesToTake(3);
        gameConfiguration.setMinMatchesToTake(1);
        gameConfiguration.setNumberOfMatches(13);
        gameConfiguration.setPlayerFirstMove(Player.HUMAN.toString());
        return gameConfiguration;
    }
}