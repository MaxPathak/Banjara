package src.databases;

import src.items.usable.Item;

public class DatabaseManager {
    private static ItemDatabase itemDatabase;

    public DatabaseManager() {
        itemDatabase = new ItemDatabase(new Item(1, 0, "Dummy Item", "", "", 1, 0, 0, 0, 0, 0, 0, true, 0, 0),
                new Item(2, 0, "Item 2", "", "", 1, 0, 0, 0, 0, 0, 0, true, 0, 0));
    }

    public static ItemDatabase getItemDatabase() {
        return itemDatabase;
    }

}
