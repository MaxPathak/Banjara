package src.items.usable;

public class Item extends UsableItem {

    protected boolean consumable;
    protected int itypeId; // 1: Regular item 2: Key item
    protected int price;

    public Item(int id, int iconIndex, String name, String description, String note, int quantity, int animationId,
            int hitType, int occasion, int scope, int speed, int successRate, boolean consumable, int itypeId,
            int price) {
        super(id, iconIndex, name, description, note, quantity, animationId, hitType, occasion, scope, speed,
                successRate);
        this.consumable = consumable;
        this.itypeId = itypeId;
        this.price = price;
    }

    public boolean isConsumable() {
        return consumable;
    }

    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    public int getItypeId() {
        return itypeId;
    }

    public void setItypeId(int itypeId) {
        this.itypeId = itypeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
