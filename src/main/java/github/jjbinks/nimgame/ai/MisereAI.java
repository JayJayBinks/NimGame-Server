package github.jjbinks.nimgame.ai;

import github.jjbinks.nimgame.api.exceptions.ApiException;
import github.jjbinks.nimgame.model.GameProperties;
import github.jjbinks.nimgame.model.NimGameInstance;
import github.jjbinks.nimgame.util.GameInstanceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MisereAI extends AI{
    private static final Logger LOGGER = LoggerFactory.getLogger(MisereAI.class);

    @Autowired
    private GameInstanceUtil gameInstanceUtil;

    @Override
    public void move(NimGameInstance gameInstance) throws ApiException {
        LOGGER.debug("Making move on : " + gameInstance);
        NimGameInstance updatedGameInstance = new NimGameInstance();
        updatedGameInstance.setGameProperties(new GameProperties.Builder()
                .withMatchesRemaining(
                        gameInstance.getGameProperties().getMatchesRemaining() -
                                this.getNumberOfMatchesToRemove(gameInstance))
                .build());
        gameInstanceUtil.patch(gameInstance, updatedGameInstance);
        LOGGER.debug("Move successfull on : " + gameInstance);
    }

    protected abstract int getNumberOfMatchesToRemove(NimGameInstance nimGameInstance);
}

