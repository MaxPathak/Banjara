package src.databases;

import src.items.equip.Weapon;
import src.items.usable.Item;

public class DatabaseManager {
    private static ItemsDatabase itemsDatabase;
    private static ItemsDatabase weaponsDatabase;
    private static ItemsDatabase armorsDatabase;

    public DatabaseManager() {
        itemsDatabase = new ItemsDatabase(new Item(1, 0, "Dummy Item", "", "", 1, 0, 0, 0, 0, 0, 0, true, 0, 0),
                new Item(2, 0, "Item 2", "", "", 1, 0, 0, 0, 0, 0, 0, true, 0, 0));
        weaponsDatabase = new ItemsDatabase(new Weapon(1, 0, "Short Sword", "", "", 1, 0, new int[] { 0, 0 }, 0, 0, 0));
    }

    public static ItemsDatabase getItemsDatabase() {
        return itemsDatabase;
    }

    public static ItemsDatabase getWeaponsDatabase() {
        return weaponsDatabase;
    }

    public static ItemsDatabase getArmorDatabase() {
        return armorsDatabase;
    }

}
