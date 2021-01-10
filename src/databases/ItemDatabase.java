package src.databases;

import java.util.ArrayList;
import java.util.Comparator;

import src.items.usable.Item;

public class ItemDatabase {

    private static ArrayList<Item> items;

    private Comparator<Item> itemSorter = new Comparator<Item>() {
        @Override
        public int compare(Item a, Item b) {
            if (a.getId() < b.getId())
                return -1;
            return 1;
        }
    };

    public ItemDatabase(Item... passedItems) {
        items = new ArrayList<Item>();
        for (Item item : passedItems) {
            items.add(item);
        }
        items.sort(itemSorter);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

}
