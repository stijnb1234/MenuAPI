package nl.iobyte.menuapi.multi;

import nl.iobyte.menuapi.basic.Menu;

public class MenuPage extends Menu {

    private final MultiMenu menu;

    public MenuPage(String title, int size, MultiMenu menu) {
        super(title, size);
        this.menu = menu;
    }

    public MenuPage(String title, int size, boolean locked, MultiMenu menu) {
        super(title, size, locked);
        this.menu = menu;
    }

    public MultiMenu getParent() {
        return menu;
    }

}
