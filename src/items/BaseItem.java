package src.items;

import java.awt.image.BufferedImage;

public abstract class BaseItem {

    protected BufferedImage icon;
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

        setIconIndex(iconIndex);
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public int getIconIndex() {
        return iconIndex;
    }

    public void setIconIndex(int iconIndex) {
        this.iconIndex = iconIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
