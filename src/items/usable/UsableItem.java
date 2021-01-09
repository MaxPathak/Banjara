package src.items.usable;

import src.items.BaseItem;

public class UsableItem extends BaseItem {

    protected int animationId;
    // protected Damage damage;
    // protected Effect effects
    protected int hitType;
    protected int occasion;
    protected int scope;
    protected int speed;
    protected float successRate;

    public UsableItem(int id, int iconIndex, String name, String description, String note, int animationId, int hitType,
            int occasion, int scope, int speed, float successRate) {
        super(id, iconIndex, name, description, note);
        this.animationId = animationId;
        this.hitType = hitType;
        this.occasion = occasion;
        this.scope = scope;
        this.speed = speed;
        this.successRate = successRate;
    }

}
