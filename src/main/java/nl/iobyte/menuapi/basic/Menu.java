package nl.iobyte.menuapi.basic;

import nl.iobyte.menuapi.action.InteractAction;
import nl.iobyte.menuapi.action.MenuAction;
import nl.iobyte.menuapi.interfaces.IMenu;
import nl.iobyte.menuapi.item.Color;
import nl.iobyte.menuapi.item.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu implements IMenu {

    private String title;
    private int size;
    private final HashMap<Integer, MenuItem> items = new HashMap<>();
    private InteractAction open, close;
    private Inventory inventory;
    private boolean locked = true;

    public Menu(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public Menu(String title, int size, boolean locked) {
        this.title = title;
        this.size = size;
        this.locked = locked;
    }

    /**
     * Return inventory size
     * @return int
     */
    public int getSize() {
        return size;
    }

    /**
     * Set size
     * @param size int
     * @return IMenu
     */
    public IMenu setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Get item
     * @param slot int
     * @return MenuItem
     */
    public MenuItem getItem(int slot) {
        if(!items.containsKey(slot))
            return null;

        return items.get(slot);
    }

    /**
     * Get menu items
     * @return MenuItem
     */
    public HashMap<Integer, MenuItem> getItems() {
        return items;
    }

    /**
     * Set item to slot
     * @param slot int
     * @param item MenuItem
     * @return IMenu
     */
    public IMenu setItem(int slot, MenuItem item) {
        if(slot < 0 || slot >= size)
            return this;

        items.put(slot, item);
        return this;
    }

    /**
     * Set item to slot
     * @param slot int
     * @param item ItemStack
     * @return IMenu
     */
    public IMenu setItem(int slot, ItemStack item) {
        MenuItem mi = getItem(slot);
        if(mi == null)
            return this;

        mi.setItem(item);
        return this;
    }

    public IMenu setItems(HashMap<Integer, MenuItem> items) {
        items.replaceAll((k, v) -> v.clone());
        return this;
    }

    /**
     * Update item
     * @param slot int
     * @return IMenu
     */
    public IMenu updateItem(int slot) {
        MenuItem item = getItem(slot);
        if(item == null)
            return this;

        inventory.setItem(slot, item.getItem());
        return this;
    }

    /**
     * Get action on open
     * @return InteractAction
     */
    public InteractAction getOpenAction() {
        return open;
    }

    /**
     * Set action on open
     * @param action InteractAction
     * @return IMenu
     */
    public IMenu setOpenAction(InteractAction action) {
        open = action;
        return this;
    }

    /**
     * Get action on close
     * @return InteractAction
     */
    public InteractAction getCloseAction() {
        return close;
    }

    /**
     * Set action on close
     * @param action InteractAction
     * @return IMenu
     */
    public IMenu setCloseAction(InteractAction action) {
        close = action;
        return this;
    }

    /**
     * Get menu title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set menu title
     * @param title String
     * @return IMenu
     */
    public IMenu setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get if Menu is locked
     * @return Boolean
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Set if menu is locked
     * @param locked Boolean
     * @return IMenu
     */
    public IMenu setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * Build Menu
     * @return IMenu
     */
    public IMenu build() {
        inventory = Bukkit.createInventory(this, toSize(size, 9), Color.parse(title));
        for(Map.Entry<Integer, MenuItem> entrySet : items.entrySet())
            if(entrySet.getKey() < size)
                inventory.setItem(entrySet.getKey(), entrySet.getValue().getItem());

        return this;
    }

    /**
     * Number to size
     * @param i int
     * @param size int
     * @return int
     */
    protected static int toSize(int i, int size) {
        int r = i % size;
        if(r > 0)
            i += size - r;

        return i;
    }

    /**
     * Get inventory
     * @return Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Execute Click
     * @param player Player
     * @param slot int
     * @param type ClickType
     * @return cancel
     */
    public boolean execute(Player player, int slot, ClickType type) {
        if(player == null || type == null)
            return locked;

        if(slot < 0 || slot >= size)
            return locked;

        MenuItem item = getItem(slot);
        if(item == null)
            return locked;

        ArrayList<MenuAction> actions = item.getActions(type);
        if(actions != null && !actions.isEmpty())
            for(MenuAction action : actions)
                action.execute(player);

        return item.doCancel();
    }

    /**
     * Open menu for player
     * @param player Player
     */
    public void open(Player player) {
        if(inventory == null)
            return;

        player.openInventory(inventory);
    }

    @Override
    public Menu clone() {
        Menu menu = new Menu(title, size);
        menu.setItems(items);
        return menu;
    }

}
