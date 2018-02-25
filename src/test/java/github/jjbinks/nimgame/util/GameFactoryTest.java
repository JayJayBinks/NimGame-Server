package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
public class GameFactoryTest {

    public static final int NUMBER_OF_MATCHES = 13;
    public static final int MAX_MATCHES_TO_TAKE = 3;
    public static final int MIN_MATCHES_TO_TAKE = 1;
    public static final Player FIRST_TURN_PLAYER = Player.HUMAN;
    public static final AIDifficulty AI_DIFFICUTLY = AIDifficulty.DUMB;

    @Mock
    private GameConfiguration gameConfiguration;

    @InjectMocks
    private GameFactory gameFactory;

    private void createMisereGameConfig() {
        when(gameConfiguration.getMinMatchesToTake()).thenReturn(MIN_MATCHES_TO_TAKE);
        when(gameConfiguration.getMaxMatchesToTake()).thenReturn(MAX_MATCHES_TO_TAKE);
        when(gameConfiguration.getNumberOfMatches()).thenReturn(NUMBER_OF_MATCHES);
        when(gameConfiguration.getGameMode()).thenReturn(NimGameMode.MISERE);
        when(gameConfiguration.getPlayerFirstMove()).thenReturn(FIRST_TURN_PLAYER);
        when(gameConfiguration.getAiDifficulty()).thenReturn(AI_DIFFICUTLY);
    }

    @Test
    public void createNewGameMisere() throws GameModeNotFoundException {
        //given
        createMisereGameConfig();
        //when
        NimGameInstance nimGameInstance = gameFactory.createNewGame(gameConfiguration);
        //then
        assert (nimGameInstance.getGameEndedProperties() == null);
        assert (nimGameInstance.getInstanceId() != null);
        GameConfiguration gameConfiguration = nimGameInstance.getGameConfiguration();
        assert (gameConfiguration != null);
        assert (gameConfiguration.getGameMode().equals(NimGameMode.MISERE));
        assert (gameConfiguration.getNumberOfMatches() == NUMBER_OF_MATCHES);
        assert (gameConfiguration.getMaxMatchesToTake() == MAX_MATCHES_TO_TAKE);
        assert (gameConfiguration.getMinMatchesToTake() == MIN_MATCHES_TO_TAKE);
        assert gameConfiguration.getAiDifficulty().equals(AI_DIFFICUTLY);
        assert (gameConfiguration.getPlayerFirstMove().equals(FIRST_TURN_PLAYER));
        assert nimGameInstance.getGameProperties().getMatchesRemaining() == NUMBER_OF_MATCHES;
        assert nimGameInstance.getGameProperties().getPlayerOnMove().equals(FIRST_TURN_PLAYER);
    }
}