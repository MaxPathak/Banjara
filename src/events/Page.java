package src.events;

public class Page {
    private Conditions conditions;
    private boolean directionFix;
    private Image image;
    private PageList[] list;
    private long moveFrequency;
    private MoveRoute moveRoute;
    private long moveSpeed;
    private long moveType;
    private long priorityType;
    private boolean stepAnime;
    private boolean through;
    private long trigger;
    private boolean walkAnime;

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions value) {
        this.conditions = value;
    }

    public boolean getDirectionFix() {
        return directionFix;
    }

    public void setDirectionFix(boolean value) {
        this.directionFix = value;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image value) {
        this.image = value;
    }

    public PageList[] getList() {
        return list;
    }

    public void setList(PageList[] value) {
        this.list = value;
    }

    public long getMoveFrequency() {
        return moveFrequency;
    }

    public void setMoveFrequency(long value) {
        this.moveFrequency = value;
    }

    public MoveRoute getMoveRoute() {
        return moveRoute;
    }

    public void setMoveRoute(MoveRoute value) {
        this.moveRoute = value;
    }

    public long getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(long value) {
        this.moveSpeed = value;
    }

    public long getMoveType() {
        return moveType;
    }

    public void setMoveType(long value) {
        this.moveType = value;
    }

    public long getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(long value) {
        this.priorityType = value;
    }

    public boolean getStepAnime() {
        return stepAnime;
    }

    public void setStepAnime(boolean value) {
        this.stepAnime = value;
    }

    public boolean getThrough() {
        return through;
    }

    public void setThrough(boolean value) {
        this.through = value;
    }

    public long getTrigger() {
        return trigger;
    }

    public void setTrigger(long value) {
        this.trigger = value;
    }

    public boolean getWalkAnime() {
        return walkAnime;
    }

    public void setWalkAnime(boolean value) {
        this.walkAnime = value;
    }
}
