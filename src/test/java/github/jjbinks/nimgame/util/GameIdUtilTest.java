package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.model.NimGameMode;
import org.junit.Test;

public class GameIdUtilTest {

    @Test
    public void generateNewGameIdMisere() throws GameModeNotFoundException {
        //when
        Long id = GameIdUtil.generateNewGameId(NimGameMode.MISERE);
        //then
        assert (id != null);
    }

    @Test
    public void generateTwoDifferentGameIdMisere() throws GameModeNotFoundException {
        //when
        Long id = GameIdUtil.generateNewGameId(NimGameMode.MISERE);
        Long id2 = GameIdUtil.generateNewGameId(NimGameMode.MISERE);
        //then
        assert (id != null);
        assert (id2 != null);
        assert (id.equals(id2) == false);
    }
}