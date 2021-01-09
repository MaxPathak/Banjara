package src.items;

public abstract class BaseItem {

    protected int id;
    protected int iconIndex;
    protected String name;
    protected String description;
    protected String note;

    public BaseItem(int id, int iconIndex, String name, String description, String note) {
        this.id = id;
        this.iconIndex = iconIndex;
        this.name = name;
        this.description = description;
        this.note = note;
    }

}
