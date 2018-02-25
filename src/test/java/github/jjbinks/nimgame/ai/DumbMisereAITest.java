package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DumbMisereAITest extends AITest {

    @InjectMocks
    private DumbMisereAI dumbMisereAI;

    @Test
    public void move() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        int minMatchesToTake = gameConfiguration.getMinMatchesToTake();
        int maxMatchesToTake = gameConfiguration.getMaxMatchesToTake();
        gameInstance = getNimGame(gameConfiguration);
        int matchesRemainingBefore = gameInstance.getGameProperties().getMatchesRemaining();
        //when
        dumbMisereAI.move(gameInstance);
        //then
        assert (gameInstance.getGameProperties().getMatchesRemaining() <= matchesRemainingBefore - minMatchesToTake);
        assert (gameInstance.getGameProperties().getMatchesRemaining() >= matchesRemainingBefore - maxMatchesToTake);
    }

    @Test
    public void moveWithOnlyTwoMatchesLeft() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        gameInstance = getNimGame(gameConfiguration);
        gameInstance.getGameProperties().setMatchesRemaining(2);
        //when
        dumbMisereAI.move(gameInstance);
        //then
        assert (gameInstance.getGameProperties().getMatchesRemaining() < 2);
        assert (gameInstance.getGameProperties().getMatchesRemaining() >= 0);

    }
}