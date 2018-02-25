package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SmartMisereAITest extends AITest {

    @InjectMocks
    private SmartMisereAi smartMisereAi;

    @Test
    public void move() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        int minMatchesToTake = gameConfiguration.getMinMatchesToTake();
        int maxMatchesToTake = gameConfiguration.getMaxMatchesToTake();
        gameInstance = getNimGame(gameConfiguration);
        int matchesRemainingBefore = gameInstance.getGameProperties().getMatchesRemaining();
        //when
        smartMisereAi.move(gameInstance);
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
        smartMisereAi.move(gameInstance);
        //then
        assert (gameInstance.getGameProperties().getMatchesRemaining() == 1);
    }

    @Test
    public void moveWithThreeMatchesLeft() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        gameInstance = getNimGame(gameConfiguration);
        gameInstance.getGameProperties().setMatchesRemaining(3);
        //when
        smartMisereAi.move(gameInstance);
        //then
        assert (gameInstance.getGameProperties().getMatchesRemaining() == 1);
    }
}