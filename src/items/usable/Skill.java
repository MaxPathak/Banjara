package src.items.usable;

public class Skill extends UsableItem {

    String message1, message2;
    int mpCost;
    int requiredWtypeId1, requiredWtypeId2;
    int stypeId;

    public Skill(int id, int iconIndex, String name, String description, String note, int quantity, int animationId,
            int hitType, int occasion, int scope, int speed, int successRate, String message1, String message2,
            int mpCost, int requiredWtypeId1, int requiredWtypeId2, int stypeId) {
        super(id, iconIndex, name, description, note, quantity, animationId, hitType, occasion, scope, speed,
                successRate);
        this.message1 = message1;
        this.message2 = message2;
        this.mpCost = mpCost;
        this.requiredWtypeId1 = requiredWtypeId1;
        this.requiredWtypeId2 = requiredWtypeId2;
        this.stypeId = stypeId;
    }

}
