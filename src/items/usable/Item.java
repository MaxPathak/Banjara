package src.items.usable;

public class Item extends UsableItem {

    protected boolean consumable;
    protected int itypeId; // 1: Regular item 2: Key item
    protected int price;

    public Item(int id, int iconIndex, String name, String description, String note, int animationId, int hitType,
            int occasion, int scope, int speed, float successRate, boolean consumable, int itypeId, int price) {
        super(id, iconIndex, name, description, note, animationId, hitType, occasion, scope, speed, successRate);
        this.consumable = consumable;
        this.itypeId = itypeId;
        this.price = price;
    }

}
