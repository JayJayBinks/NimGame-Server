package github.jjbinks.nimgame.util;

import github.jjbinks.nimgame.model.NimGameMode;
import org.junit.Test;

public class GameIdUtilTest {

    @Test
    public void generateNewGameIdMisere() {
        //when
        Long id = GameIdUtil.generateNewGameId(NimGameMode.MISERE);
        //then
        assert (id != null);
    }
}