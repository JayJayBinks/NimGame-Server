package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.model.AIDifficulty;
import github.jjbinks.nimgame.model.NimGameInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartMisereAi extends MisereAI {
    public static final Logger LOGGER = LoggerFactory.getLogger(SmartMisereAi.class);
    public static final AIDifficulty DIFFICULTY = AIDifficulty.SMART;

    @Override
    protected int getNumberOfMatchesToRemove(NimGameInstance nimGameInstance) {

        int remainingMatches = nimGameInstance.getGameProperties().getMatchesRemaining();
        int matchesToRemove = remainingMatches - calculateWinningStrategy(remainingMatches);
        if (matchesToRemove == 0)
            // can not make a smart move in this  case
            matchesToRemove = 1;
        return matchesToRemove;
    }

    private int calculateWinningStrategy(int numberOfMatches)
    {
        return ((numberOfMatches - 1) / 4) * 4 + 1;
    }
}
