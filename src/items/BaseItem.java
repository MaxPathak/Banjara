package src.items;

import java.awt.image.BufferedImage;

import src.gfx.Assets;

public abstract class BaseItem {

    protected BufferedImage icon;
    protected int id;
    protected int iconIndex;
    protected String name;
    protected String description;
    protected String note;
    protected int quantity;

    public BaseItem(int id, int iconIndex, String name, String description, String note, int quantity) {
        this.id = id;
        this.iconIndex = iconIndex;
        this.name = name;
        this.description = description;
        this.note = note;
        this.quantity = quantity;

        setIconIndex(iconIndex);
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon() {
        icon = Assets.getIcon(iconIndex);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
