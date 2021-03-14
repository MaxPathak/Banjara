package src.entities;

import java.util.LinkedList;

public class PageList {
    private LinkedList<Page> pages;

    public PageList(Page... pages) {
        this.pages = new LinkedList<Page>();
        for (Page page : pages) {
            this.pages.add(page);
        }
    }

    public LinkedList<Page> getPages() {
        return pages;
    }

    public void setPages(LinkedList<Page> pages) {
        this.pages = pages;
    }

}
