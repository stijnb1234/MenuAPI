package nl.iobyte.menuapi.multi;

import nl.iobyte.menuapi.action.InteractAction;
import nl.iobyte.menuapi.interfaces.IMenu;
import nl.iobyte.menuapi.item.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MultiMenu implements IMenu {

    private String title;
    private ArrayList<Integer> page_sizes;
    private InteractAction open, close;
    private boolean locked = true;

    private final HashMap<Integer, MenuPage> pages = new HashMap<>();
    private final HashMap<UUID, MenuPage> page_data = new HashMap<>();

    public MultiMenu(String title, ArrayList<Integer> page_sizes) {
        this.title = title;
        this.page_sizes = page_sizes;
        buildPages();
    }

    public MultiMenu(String title, ArrayList<Integer> page_sizes, boolean locked) {
        this.title = title;
        this.page_sizes = page_sizes;
        this.locked = locked;
        buildPages();
    }

    /**
     * Return inventory sizes
     * @return int
     */
    public ArrayList<Integer> getPageSizes() {
        return page_sizes;
    }

    /**
     * Set sizes
     * @param page_sizes int
     * @return IMenu
     */
    public IMenu setPageSizes(ArrayList<Integer> page_sizes) {
        this.page_sizes = page_sizes;
        buildPages();

        return this;
    }

    /**
     * Get item of first page
     * @param slot int
     * @return MenuItem
     */
    public MenuItem getItem(int slot) {
        int[] data = getSlot(slot);
        if(data == null)
            return null;

        return getItem(data[0], data[1]);
    }

    /**
     * Get item on page
     * @param page int
     * @param slot int
     * @return MenuItem
     */
    public MenuItem getItem(int page, int slot) {
        MenuPage mp = getPage(page);
        if(mp == null)
            return null;

        return mp.getItem(slot);
    }

    /**
     * Get items of first page
     * @return IMenu
     */
    public HashMap<Integer, MenuItem> getItems() {
        return getItems(1);
    }

    /**
     * Get items of MenuPage
     * @param page int
     * @return HashMap
     */
    public HashMap<Integer, MenuItem> getItems(int page) {
        if(page < 1)
            return null;

        MenuPage mp = pages.get(page);
        if(mp == null)
            return null;

        return mp.getItems();
    }

    /**
     * Set item to slot on first page
     * @param slot int
     * @param item MenuItem
     * @return IMenu
     */
    public IMenu setItem(int slot, MenuItem item) {
        int[] data = getSlot(slot);
        if(data == null)
            return this;

        return setItem(data[0], data[1], item);
    }

    /**
     * Set item to slot on page
     * @param page int
     * @param slot int
     * @param item MenuItem
     * @return IMenu
     */
    public IMenu setItem(int page, int slot, MenuItem item) {
        MenuPage mp = getPage(page);
        if(mp == null)
            return this;

        return mp.setItem(slot, item);
    }

    /**
     * Set item to slot on first page
     * @param slot int
     * @param item ItemStack
     * @return IMenu
     */
    public IMenu setItem(int slot, ItemStack item) {
        int[] data = getSlot(slot);
        if(data == null)
            return this;

        return setItem(data[0], data[1], item);
    }

    /**
     * Set item to slot on page
     * @param page int
     * @param slot int
     * @param item ItemStack
     * @return IMenu
     */
    public IMenu setItem(int page, int slot, ItemStack item) {
        MenuPage mp = getPage(page);
        if(mp == null)
            return this;

        mp.setItem(slot, item);
        return mp.setItem(slot, item);
    }

    /**
     * Set items for first page
     * @param items MenuItems
     * @return IMenu
     */
    public MultiMenu setItems(HashMap<Integer, MenuItem> items) {
        return setItems(1, items);
    }

    /**
     * Set items for MenuPage
     * @param page int
     * @param items HashMap
     * @return IMenu
     */
    public MultiMenu setItems(int page, HashMap<Integer, MenuItem> items) {
        if(page < 1)
            return this;

        MenuPage mp = pages.get(page);
        if(mp == null)
            return this;

        mp.setItems(items);
        return this;
    }

    /**
     * Update item on first page
     * @param slot int
     * @return IMenu
     */
    public IMenu updateItem(int slot) {
        int[] data = getSlot(slot);
        if(data == null)
            return this;

        return updateItem(data[0], data[1]);
    }

    /**
     * Update item on page
     * @param page int
     * @param slot int
     * @return IMenu
     */
    public IMenu updateItem(int page, int slot) {
        MenuPage mp = getPage(page);
        if(mp == null)
            return this;

        mp.updateItem(slot);
        return this;
    }

    /**
     * Get page from slot
     * @param slot int
     * @return int
     */
    public int[] getSlot(int slot) {
        int page = 1;
        int temp = slot;
        for(int size : page_sizes) {
            temp -= size;
            if(temp < 0) {
                temp += size;
                return new int[]{
                    page, temp
                };
            }

            page++;
        }

        return null;
    }

    /**
     * Get MenuPage
     * @param page int
     * @return MenuPage
     */
    public MenuPage getPage(int page) {
        if(!pages.containsKey(page))
            return null;

        return pages.get(page);
    }

    /**
     * Get MenuPages
     * @return HashMap
     */
    public HashMap<Integer, MenuPage> getPages() {
        return pages;
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
     * Get if menu is locked
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
        for(MenuPage page : page_data.values())
            page.setLocked(locked);

        return this;
    }

    /**
     * Build Menu
     * @return IMenu
     */
    public IMenu build() {
        for(MenuPage page : pages.values())
            page.build();

        return this;
    }

    /**
     * Get menu pages
     * @return IMenu
     */
    public IMenu buildPages() {
        int i = 1;
        for(int size : page_sizes) {
            MenuPage page = getPage(i);
            if(page == null) {
                page = new MenuPage(title, size, locked,this);
            } else{
                page.setSize(size);
            }

            pages.put(i, page);
            i++;
        }

        int difference = pages.size() - page_sizes.size();
        if(difference > 0)
            for(int j = 0; j < difference; j++)
                pages.remove(page_sizes.size() + j);

        return this;
    }

    /**
     * Return null
     * @return Inventory
     */
    public Inventory getInventory() {
        MenuPage mp = pages.get(1);
        if(mp == null)
            return null;

        return mp.getInventory();
    }

    /**
     * Execute non existing Click
     * @param player Player
     * @param slot int
     * @param type ClickType
     * @return cancel
     */
    public boolean execute(Player player, int slot, ClickType type) {
        return true;
    }

    /**
     * See if MenuPage is open for User
     * @param uuid UUID
     * @param page MenuPage
     * @return boolean
     */
    public boolean isActivePage(UUID uuid, MenuPage page) {
        if(uuid == null || page == null)
            return false;

        if(!page_data.containsKey(uuid))
            return false;

        MenuPage mp = page_data.get(uuid);
        return mp == page;
    }

    /**
     * Open first page of inventory
     * @param player Player
     */
    public void open(Player player) {
        open(player, 1);
    }

    /**
     * Open page of invetory
     * @param player Player
     * @param page int
     */
    public void open(Player player, int page) {
        if(player == null || page < 1)
            return;

        if(!pages.containsKey(page))
            return;

        MenuPage mp = pages.get(page);
        page_data.put(player.getUniqueId(), mp);
        mp.open(player);
    }

    /**
     * Close inventory of Player
     * @param player Player
     */
    public void close(Player player) {
        if(player == null)
            return;

        page_data.remove(player.getUniqueId());
        player.closeInventory();
    }

    /**
     * Clone Menu
     * @return MultiMenu
     */
    public MultiMenu clone() {
        MultiMenu menu = new MultiMenu(title, page_sizes);
        for(Map.Entry<Integer, MenuPage> entrySet : pages.entrySet())
            menu.setItems(entrySet.getKey(), entrySet.getValue().getItems());

        return menu;
    }

}
