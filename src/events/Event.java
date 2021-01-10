package src.events;

public class Event {
    private long id;
    private String name;
    private String note;
    private Page[] pages;
    private long x;
    private long y;

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String value) {
        this.note = value;
    }

    public Page[] getPages() {
        return pages;
    }

    public void setPages(Page[] value) {
        this.pages = value;
    }

    public long getX() {
        return x;
    }

    public void setX(long value) {
        this.x = value;
    }

    public long getY() {
        return y;
    }

    public void setY(long value) {
        this.y = value;
    }
}
