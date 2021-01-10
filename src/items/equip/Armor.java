package src.items.equip;

public class Armor extends EquipItem {

    int atypeId;

    public Armor(int id, int iconIndex, String name, String description, String note, int quantity, int etypeId,
            int[] params, int price) {
        super(id, iconIndex, name, description, note, quantity, etypeId, params, price);
    }

}
