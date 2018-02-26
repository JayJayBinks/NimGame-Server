package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.api.exceptions.GameModeNotFoundException;
import github.jjbinks.nimgame.api.exceptions.NotFoundException;
import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.NimGameMode;
import org.springframework.beans.factory.annotation.Autowired;

import static github.jjbinks.nimgame.api.exceptions.NotFoundException.AI_NOT_FOUND;

public class AIController {

    @Autowired
    private DumbMisereAI dumbMisereAI;

    @Autowired
    private SmartMisereAi smartMisereAi;


    public AI getAI(NimGameMode nimGameMode, AIDifficulty aiDifficulty) throws NotFoundException {
        switch(nimGameMode){
            case MISERE:
                return getMisereAI(aiDifficulty);
            default:
                throw new GameModeNotFoundException(nimGameMode);
        }
    }

    private AI getMisereAI(AIDifficulty aiDifficulty)throws NotFoundException  {
        switch (aiDifficulty){
            case DUMB:
                return dumbMisereAI;
            case SMART:
                return smartMisereAi;
            default:
                throw new NotFoundException(AI_NOT_FOUND, "Ai could not be found: " + aiDifficulty);
        }
    }
}
