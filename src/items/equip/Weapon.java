package src.items.equip;

public class Weapon extends EquipItem {

    int animationId;
    int wtypeId;

    public Weapon(int id, int iconIndex, String name, String description, String note, int quantity, int etypeId,
            int[] params, int price, int animationId, int wtypeId) {
        super(id, iconIndex, name, description, note, quantity, 0, params, price);
        this.animationId = animationId;
        this.wtypeId = wtypeId;
    }

}
