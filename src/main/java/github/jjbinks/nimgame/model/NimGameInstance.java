package github.jjbinks.nimgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Represents one game of the given game mode.
 */
@ApiModel(description = "Represents one game of the given game mode.")
public class NimGameInstance {

    @JsonProperty("instanceId")
    private Long instanceId = null;

    @JsonProperty("gameProperties")
    private GameProperties gameProperties = null;

    @JsonProperty("gameEndedProperties")
    private GameEndedProperties gameEndedProperties = null;

    @JsonProperty("gameConfiguration")
    private GameConfiguration gameConfiguration = null;


    public NimGameInstance instanceId(Long instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    /**
     * Get instanceId
     *
     * @return instanceId
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
    }

    public NimGameInstance gameProperties(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
        return this;
    }

    /**
     * Get gameProperties
     *
     * @return gameProperties
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public GameProperties getGameProperties() {
        return gameProperties;
    }

    public void setGameProperties(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

    public NimGameInstance gameEnded(GameEndedProperties gameEndedProperties) {
        this.gameEndedProperties = gameEndedProperties;
        return this;
    }

    /**
     * Get gameEndedProperties
     *
     * @return gameEndedProperties
     **/
    @ApiModelProperty(value = "")
    @Valid
    public GameEndedProperties getGameEndedProperties() {
        return gameEndedProperties;
    }

    public void setGameEndedProperties(GameEndedProperties gameEndedProperties) {
        this.gameEndedProperties = gameEndedProperties;
    }

    @NotNull
    public GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    @NotNull
    public void setGameConfiguration(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NimGameInstance that = (NimGameInstance) o;
        return Objects.equals(instanceId, that.instanceId) &&
                Objects.equals(gameProperties, that.gameProperties) &&
                Objects.equals(gameEndedProperties, that.gameEndedProperties) &&
                Objects.equals(gameConfiguration, that.gameConfiguration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId, gameProperties, gameEndedProperties, gameConfiguration);
    }

    @Override
    public String toString() {
        return "NimGameInstance{" +
                "instanceId=" + instanceId +
                ", gameProperties=" + gameProperties +
                ", gameEndedProperties=" + gameEndedProperties +
                ", gameConfiguration=" + gameConfiguration +
                '}';
    }
}

