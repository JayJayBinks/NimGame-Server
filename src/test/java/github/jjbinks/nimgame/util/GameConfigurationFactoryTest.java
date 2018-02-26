package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.BadRequestException;
import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.configuration.ProjectProperties;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameMode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "development")
@TestPropertySource(locations = {"classpath:/development.properties"})
public class GameConfigurationFactoryTest {

    @Value("${nim_game.misere.number_of_matches}")
    private int misereNumberOfMatches;

    @Value("${nim_game.misere.min_matches_to_take}")
    private int misereMinMatchesToTake;


    @Value("${nim_game.misere.max_matches_to_take}")
    private int misereMaxMatchesToTake;


    @Mock
    private ProjectProperties projectProperties;

    @InjectMocks
    private GameConfigurationFactory gameConfigurationFactory = new GameConfigurationFactory();

    private GameConfiguration gameConfiguration;

    @Before
    public void setupMocks() {
        when(projectProperties.getMisereMaxMatchesToTake()).thenReturn(misereMaxMatchesToTake);
        when(projectProperties.getMisereMinMatchesToTake()).thenReturn(misereMinMatchesToTake);
        when(projectProperties.getMisereNumberOfMatches()).thenReturn(misereNumberOfMatches);
    }

    @Test
    public void createGameConfigurationMisere() throws GameModeNotFoundException {
        //when
        gameConfiguration = gameConfigurationFactory.createGameConfiguration(NimGameMode.MISERE);
        //then
        assert gameConfiguration.getMinMatchesToTake() == misereMinMatchesToTake;
        assert gameConfiguration.getMaxMatchesToTake() == misereMaxMatchesToTake;
        assert gameConfiguration.getNumberOfMatches() == misereNumberOfMatches;
        assert gameConfiguration.getAiDifficulty().equals(AIDifficulty.DUMB);
        assert gameConfiguration.getGameMode().equals(NimGameMode.MISERE);
    }


    @Test
    public void verifyGameConfigurationMisereDumb() throws GameModeNotFoundException, BadRequestException {
        //given
        gameConfiguration = new GameConfiguration();
        gameConfiguration.setAiDifficulty(AIDifficulty.DUMB);
        //when
        GameConfiguration verifiedGameConfiguration = gameConfigurationFactory.verifyAlternateConfig(NimGameMode.MISERE, gameConfiguration);
        //then
        assert (verifiedGameConfiguration.getGameMode() != null);
        assert (verifiedGameConfiguration.getMaxMatchesToTake() != null);
        assert (verifiedGameConfiguration.getNumberOfMatches()) != null;
        assert (verifiedGameConfiguration.getGameMode()).equals(NimGameMode.MISERE);
        assert (verifiedGameConfiguration.getAiDifficulty()).equals(AIDifficulty.DUMB);
    }

    @Test
    public void verifyGameConfigurationMisereSmart() throws GameModeNotFoundException, BadRequestException {
        //given
        gameConfiguration = new GameConfiguration();
        gameConfiguration.setAiDifficulty(AIDifficulty.SMART);
        //when
        GameConfiguration verifiedGameConfiguration = gameConfigurationFactory.verifyAlternateConfig(NimGameMode.MISERE, gameConfiguration);
        //then
        assert (verifiedGameConfiguration.getGameMode() != null);
        assert (verifiedGameConfiguration.getMaxMatchesToTake() != null);
        assert (verifiedGameConfiguration.getNumberOfMatches()) != null;
        assert (verifiedGameConfiguration.getGameMode()).equals(NimGameMode.MISERE);
        assert (verifiedGameConfiguration.getAiDifficulty()).equals(AIDifficulty.SMART);
    }

    @Test
    public void verifyGameConfigurationMisere() throws GameModeNotFoundException, BadRequestException {
        //given
        gameConfiguration = new GameConfiguration();
        gameConfiguration.setGameMode(NimGameMode.MISERE);
        //when
        GameConfiguration verifiedGameConfiguration = gameConfigurationFactory.verifyAlternateConfig(NimGameMode.MISERE, gameConfiguration);
        //then
        assert (verifiedGameConfiguration.getGameMode() != null);
        assert (verifiedGameConfiguration.getMaxMatchesToTake() != null);
        assert (verifiedGameConfiguration.getNumberOfMatches()) != null;
        assert (verifiedGameConfiguration.getGameMode()).equals(NimGameMode.MISERE);
        assert (verifiedGameConfiguration.getAiDifficulty() != null);
    }
}