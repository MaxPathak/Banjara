package src.events;

public class Image {
    private long tileID;
    private String characterName;
    private long direction;
    private long pattern;
    private long characterIndex;

    public long getTileID() {
        return tileID;
    }

    public void setTileID(long value) {
        this.tileID = value;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String value) {
        this.characterName = value;
    }

    public long getDirection() {
        return direction;
    }

    public void setDirection(long value) {
        this.direction = value;
    }

    public long getPattern() {
        return pattern;
    }

    public void setPattern(long value) {
        this.pattern = value;
    }

    public long getCharacterIndex() {
        return characterIndex;
    }

    public void setCharacterIndex(long value) {
        this.characterIndex = value;
    }
}
