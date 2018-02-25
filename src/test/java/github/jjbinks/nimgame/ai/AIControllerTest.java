package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.NimGameMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AIControllerTest {

    @Mock
    private DumbMisereAI dumbMisereAI;

    @Mock
    private SmartMisereAi smartMisereAi;

    @InjectMocks
    private AIController aiController;

    @Test
    public void getDumbMisereAI() throws Exception {
        //when
        AI ai = aiController.getAI(NimGameMode.MISERE, AIDifficulty.DUMB);
        //then
        assert ai.equals(dumbMisereAI);
    }

    @Test
    public void getSmartMisereAI() throws Exception {
        //when
        AI ai = aiController.getAI(NimGameMode.MISERE, AIDifficulty.SMART);
        //then
        assert ai.equals(smartMisereAi);
    }

}