package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.model.NimGameInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AI.class);

    public abstract void move(NimGameInstance gameInstance) throws ApiException;

    //The AI move is delayed to not immediately send updated game back to client, which would lead to confusion
    //Implementation has to execute the game update after some short timer after the player request
    //TODO: this is not a beautiful implementation but it works
    public static void delayedMove(AI ai, NimGameInstance gameInstance) {
        {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                ai.move(gameInstance);
                            } catch (ApiException e) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                    },
                    2000
            );
        }
    }
}

