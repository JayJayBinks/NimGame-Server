package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GameConfiguration {

    @JsonProperty("aiDifficulty")
    private String aiDifficulty;

    @JsonProperty("gameMode")
    private String gameMode;

    @JsonProperty("numberOfMatches")
    private Integer numberOfMatches;

    @JsonProperty("minMatchesToTake")
    private Integer minMatchesToTake;

    @JsonProperty("maxMatchesToTake")
    private Integer maxMatchesToTake;

    @JsonProperty("playerFirstMove")
    private String playerFirstMove;

    public String getAiDifficulty() {
        return aiDifficulty;
    }

    public void setAiDifficulty(String aiDifficulty) {
        this.aiDifficulty = aiDifficulty;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Integer getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(Integer numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public Integer getMinMatchesToTake() {
        return minMatchesToTake;
    }

    public void setMinMatchesToTake(Integer minMatchesToTake) {
        this.minMatchesToTake = minMatchesToTake;
    }

    public Integer getMaxMatchesToTake() {
        return maxMatchesToTake;
    }

    public void setMaxMatchesToTake(Integer maxMatchesToTake) {
        this.maxMatchesToTake = maxMatchesToTake;
    }

    public String getPlayerFirstMove() {
        return playerFirstMove;
    }

    public void setPlayerFirstMove(String playerFirstMove) {
        this.playerFirstMove = playerFirstMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConfiguration that = (GameConfiguration) o;
        return numberOfMatches == that.numberOfMatches &&
                minMatchesToTake == that.minMatchesToTake &&
                maxMatchesToTake == that.maxMatchesToTake &&
                aiDifficulty == that.aiDifficulty &&
                Objects.equals(gameMode, that.gameMode) &&
                Objects.equals(playerFirstMove, that.playerFirstMove);
    }

    @Override
    public int hashCode() {

        return Objects.hash(aiDifficulty, gameMode, numberOfMatches, minMatchesToTake, maxMatchesToTake, playerFirstMove);
    }

    @Override
    public String toString() {
        return "GameConfiguration{" +
                "aiDifficulty=" + aiDifficulty +
                ", gameMode='" + gameMode + '\'' +
                ", numberOfMatches=" + numberOfMatches +
                ", minMatchesToTake=" + minMatchesToTake +
                ", maxMatchesToTake=" + maxMatchesToTake +
                ", playerFirstMove='" + playerFirstMove + '\'' +
                '}';
    }
}