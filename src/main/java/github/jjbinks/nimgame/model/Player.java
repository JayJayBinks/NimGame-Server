package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Player
 */
public enum Player {

    HUMAN("human"),

    COMPUTER("computer");

    private String value;

    Player(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Player fromValue(String text) {
        for (Player b : Player.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    public Player changeTurn() {
        switch (this) {
            case HUMAN:
                return COMPUTER;
            case COMPUTER:
                return HUMAN;
            default:
                //should never happen
                return null;
        }
    }
}

