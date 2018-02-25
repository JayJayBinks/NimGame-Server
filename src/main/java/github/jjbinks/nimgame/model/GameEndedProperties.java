package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Will only be present if the game has ended.
 */
@ApiModel(description = "Will only be present if the game has ended.")
public class GameEndedProperties {
    @JsonProperty("winner")
    private Player winner = null;

    public GameEndedProperties winner(Player winner) {
        this.winner = winner;
        return this;
    }

    /**
     * Get winner
     *
     * @return winner
     **/
    @ApiModelProperty(value = "")

    @Valid

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameEndedProperties that = (GameEndedProperties) o;
        return winner == that.winner;
    }

    @Override
    public int hashCode() {

        return Objects.hash(winner);
    }

    @Override
    public String toString() {
        return "GameEndedProperties{" +
                "winner=" + winner +
                '}';
    }
}

