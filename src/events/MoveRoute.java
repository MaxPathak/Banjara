package src.events;

public class MoveRoute {
    private MoveRouteList[] list;
    private boolean repeat;
    private boolean skippable;
    private boolean wait;

    public MoveRouteList[] getList() {
        return list;
    }

    public void setList(MoveRouteList[] value) {
        this.list = value;
    }

    public boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean value) {
        this.repeat = value;
    }

    public boolean getSkippable() {
        return skippable;
    }

    public void setSkippable(boolean value) {
        this.skippable = value;
    }

    public boolean getWait() {
        return wait;
    }

    public void setWait(boolean value) {
        this.wait = value;
    }
}
