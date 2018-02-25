package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class GameProperties {

    @JsonProperty("matchesRemaining")
    private Integer matchesRemaining = null;

    @JsonProperty("playerOnMove")
    private Player playerOnMove = null;

    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public Integer getMatchesRemaining() {
        return matchesRemaining;
    }

    public void setMatchesRemaining(Integer matchesRemaining) {
        this.matchesRemaining = matchesRemaining;
    }


    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid
    public Player getPlayerOnMove() {
        return playerOnMove;
    }

    public void setPlayerOnMove(Player playerOnMove) {
        this.playerOnMove = playerOnMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameProperties that = (GameProperties) o;
        return Objects.equals(matchesRemaining, that.matchesRemaining) &&
                playerOnMove == that.playerOnMove;
    }

    @Override
    public int hashCode() {

        return Objects.hash(matchesRemaining, playerOnMove);
    }

    @Override
    public String toString() {
        return "GameProperties{" +
                "matchesRemaining=" + matchesRemaining +
                ", playerOnMove=" + playerOnMove +
                '}';
    }


    public static final class Builder {
        private GameProperties gameProperties;

        public Builder() {
            gameProperties = new GameProperties();
        }

        public Builder withMatchesRemaining(Integer matchesRemaining) {
            gameProperties.setMatchesRemaining(matchesRemaining);
            return this;
        }

        public Builder withPlayerOnMove(Player playerOnMove) {
            gameProperties.setPlayerOnMove(playerOnMove);
            return this;
        }

        public GameProperties build() {
            return gameProperties;
        }
    }
}
