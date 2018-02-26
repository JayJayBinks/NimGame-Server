package github.jjbinks.nimgame.persistence;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.api.exceptions.NotFoundException;
import github.jjbinks.nimgame.model.GameConfiguration;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.model.NimGameMode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GameStorageImplTest {

    public static final long INSTANCE_ID_ONE = 1L;
    public static final long INSTANCE_ID_TWO = 2L;

    @Mock
    private NimGameInstance nimGameInstanceOne;
    @Mock
    private NimGameInstance nimGameInstanceTwo;
    @Mock
    private GameConfiguration gameConfiguration;

    @Spy
    private GameStorageImpl gameStorage;


    @Before
    public void setup() {
        when(gameConfiguration.getGameMode()).thenReturn(NimGameMode.MISERE);

        when(nimGameInstanceOne.getInstanceId()).thenReturn(INSTANCE_ID_ONE);
        when(nimGameInstanceOne.getGameConfiguration()).thenReturn(gameConfiguration);

        when(nimGameInstanceTwo.getInstanceId()).thenReturn(INSTANCE_ID_TWO);
        when(nimGameInstanceTwo.getGameConfiguration()).thenReturn(gameConfiguration);

    }

    @Test
    public void find() throws NotFoundException {
        //given
        gameStorage.store(nimGameInstanceOne);
        //when
        NimGameInstance nimGameInstance = gameStorage.find(NimGameMode.MISERE, INSTANCE_ID_ONE);
        //then
        assert nimGameInstance.equals(nimGameInstanceOne);
    }

    @Test(expected = NotFoundException.class)
    public void remove() throws NotFoundException {
        //given
        gameStorage.store(nimGameInstanceOne);
        NimGameInstance nimGameInstance = gameStorage.find(NimGameMode.MISERE, INSTANCE_ID_ONE);
        assert nimGameInstance != null;
        //when
        gameStorage.remove(nimGameInstanceOne);
        nimGameInstance = gameStorage.find(NimGameMode.MISERE, INSTANCE_ID_ONE);
    }

    @Test
    public void getAllGames() throws GameModeNotFoundException {
        //given
        gameStorage.store(nimGameInstanceOne);
        gameStorage.store(nimGameInstanceTwo);
        //when
        Map<Long, NimGameInstance> allGames = gameStorage.getAllGames(NimGameMode.MISERE);
        //then
        assert allGames.get(nimGameInstanceOne.getInstanceId()) != null;
        assert allGames.get(nimGameInstanceOne.getInstanceId()).equals(nimGameInstanceOne);

        assert allGames.get(nimGameInstanceTwo.getInstanceId()) != null;
        assert allGames.get(nimGameInstanceTwo.getInstanceId()).equals(nimGameInstanceTwo);
    }
}