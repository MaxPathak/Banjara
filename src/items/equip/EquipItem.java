package src.items.equip;

import src.items.BaseItem;

public class EquipItem extends BaseItem {

    int etypeId;
    int[] params;
    int price;
    // Trait[] traits;

    public EquipItem(int id, int iconIndex, String name, String description, String note, int quantity, int etypeId,
            int[] params, int price) {
        super(id, iconIndex, name, description, note, quantity);
        this.etypeId = etypeId;
        this.params = params;
        this.price = price;
    }

}
