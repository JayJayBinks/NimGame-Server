package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.GameEndedProperties;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import github.jjbinks.nimgame.model.Player;
import org.junit.Test;

public class GameInstanceUtilTest {


    private NimGameInstance instanceToUpdate;
    private NimGameInstance updatedGameInstance;
    private GameConfiguration gameConfiguration;
    private GameInstanceUtil gameInstanceUtil = new GameInstanceUtil();

    @Test
    public void patchValid() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        int matchesToTake = gameConfiguration.getMinMatchesToTake();

        instanceToUpdate = getNimGame(gameConfiguration);
        int matchesBefore = instanceToUpdate.getGameProperties().getMatchesRemaining();
        updatedGameInstance = getNimGame(gameConfiguration);
        updatedGameInstance.getGameProperties().setMatchesRemaining(
                updatedGameInstance.getGameProperties().getMatchesRemaining()
                        - matchesToTake
        );
        //when
        gameInstanceUtil.patch(instanceToUpdate, updatedGameInstance);
        //then
        assert (matchesBefore - matchesToTake == instanceToUpdate.getGameProperties().getMatchesRemaining());
        assert (instanceToUpdate.getGameProperties().getPlayerOnMove().toString()
                .equals(gameConfiguration.getPlayerFirstMove()) == false);
        assert (instanceToUpdate.getGameConfiguration().equals(gameConfiguration));
    }

    @Test(expected = ApiException.class)
    public void patchIvalidTooLessMatchesTaken() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();

        int matchesToTake = gameConfiguration.getMinMatchesToTake();
        //invalidate request
        matchesToTake -= 1;

        instanceToUpdate = getNimGame(gameConfiguration);

        updatedGameInstance = getNimGame(gameConfiguration);
        updatedGameInstance.getGameProperties().setMatchesRemaining(
                updatedGameInstance.getGameProperties().getMatchesRemaining()
                        - matchesToTake
        );
        //when
        gameInstanceUtil.patch(instanceToUpdate, updatedGameInstance);
        //then exception is thrown
    }

    @Test(expected = ApiException.class)
    public void patchIvalidGameAlreadyEnded() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        int matchesToTake = gameConfiguration.getMinMatchesToTake();
        instanceToUpdate = getNimGame(gameConfiguration);
        instanceToUpdate.setGameEndedProperties(new GameEndedProperties());

        updatedGameInstance = getNimGame(gameConfiguration);
        updatedGameInstance.getGameProperties().setMatchesRemaining(
                updatedGameInstance.getGameProperties().getMatchesRemaining()
                        - matchesToTake
        );
        //when
        gameInstanceUtil.patch(instanceToUpdate, updatedGameInstance);
        //then exception is thrown
    }

    @Test(expected = ApiException.class)
    public void patchIvalidTooManyMatchesTaken() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();

        int matchesToTake = gameConfiguration.getMaxMatchesToTake();
        //invalidate request
        matchesToTake += 1;

        instanceToUpdate = getNimGame(gameConfiguration);

        updatedGameInstance = getNimGame(gameConfiguration);
        updatedGameInstance.getGameProperties().setMatchesRemaining(
                updatedGameInstance.getGameProperties().getMatchesRemaining()
                        - matchesToTake
        );
        //when
        gameInstanceUtil.patch(instanceToUpdate, updatedGameInstance);
        //then exception is thrown
    }

    @Test
    public void patchGameEndedPatch() throws ApiException {
        //given
        gameConfiguration = getGameConfiguration();
        instanceToUpdate = getNimGame(gameConfiguration);
        instanceToUpdate.getGameProperties().setMatchesRemaining(1);

        updatedGameInstance = getNimGame(gameConfiguration);
        updatedGameInstance.getGameProperties().setMatchesRemaining(0);
        //when
        gameInstanceUtil.patch(instanceToUpdate, updatedGameInstance);
        //then
        assert instanceToUpdate.getGameEndedProperties() != null;
        assert instanceToUpdate.getGameEndedProperties().getWinner() != null;
        assert instanceToUpdate.getGameProperties().getPlayerOnMove() == null;
    }

    private NimGameInstance getNimGame(GameConfiguration gameConfiguration) {
        GameProperties gameProperties = new GameProperties();
        gameProperties.setMatchesRemaining(13);
        gameProperties.setPlayerOnMove(Player.HUMAN);

        NimGameInstance nimInstance = new NimGameInstance();
        nimInstance.setGameConfiguration(gameConfiguration);
        nimInstance.setGameProperties(gameProperties);
        nimInstance.setInstanceId(1L);

        return nimInstance;
    }

    private GameConfiguration getGameConfiguration() {
        GameConfiguration gameConfiguration = new GameConfiguration();
        gameConfiguration.setAiDifficulty(AIDifficulty.DUMB);
        gameConfiguration.setGameMode(NimGameMode.MISERE);
        gameConfiguration.setMaxMatchesToTake(3);
        gameConfiguration.setMinMatchesToTake(1);
        gameConfiguration.setNumberOfMatches(13);
        gameConfiguration.setPlayerFirstMove(Player.HUMAN);
        return gameConfiguration;
    }
}