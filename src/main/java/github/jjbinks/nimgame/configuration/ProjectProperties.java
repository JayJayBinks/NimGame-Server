package github.jjbinks.nimgame.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class ProjectProperties {

    @Value("${nim_game.misere.number_of_matches}")
    private int misereNumberOfMatches;

    @Value("${nim_game.misere.min_matches_to_take}")
    private int misereMinMatchesToTake;


    @Value("${nim_game.misere.max_matches_to_take}")
    private int misereMaxMatchesToTake;


    public int getMisereNumberOfMatches() {
        return misereNumberOfMatches;
    }

    public void setMisereNumberOfMatches(int misereNumberOfMatches) {
        this.misereNumberOfMatches = misereNumberOfMatches;
    }

    public int getMisereMinMatchesToTake() {
        return misereMinMatchesToTake;
    }

    public void setMisereMinMatchesToTake(int misereMinMatchesToTake) {
        this.misereMinMatchesToTake = misereMinMatchesToTake;
    }

    public int getMisereMaxMatchesToTake() {
        return misereMaxMatchesToTake;
    }

    public void setMisereMaxMatchesToTake(int misereMaxMatchesToTake) {
        this.misereMaxMatchesToTake = misereMaxMatchesToTake;
    }

}
