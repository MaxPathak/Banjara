package src.databases;

import java.util.ArrayList;
import java.util.Comparator;

import src.items.BaseItem;

public class ItemsDatabase {

    private ArrayList<BaseItem> items;

    private Comparator<BaseItem> itemSorter = new Comparator<BaseItem>() {
        @Override
        public int compare(BaseItem a, BaseItem b) {
            if (a.getId() < b.getId())
                return -1;
            return 1;
        }
    };

    public ItemsDatabase(BaseItem... passedItems) {
        items = new ArrayList<BaseItem>();
        for (BaseItem item : passedItems) {
            items.add(item);
        }
        items.sort(itemSorter);
    }

    public BaseItem getItemById(int id) {
        for (BaseItem item : items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

}
