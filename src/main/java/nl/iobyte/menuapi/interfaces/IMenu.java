package nl.iobyte.menuapi.interfaces;

import nl.iobyte.menuapi.action.InteractAction;
import nl.iobyte.menuapi.item.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public interface IMenu extends InventoryHolder {

    /**
     * Get item
     * @param slot int
     * @return MenuItem
     */
    MenuItem getItem(int slot);

    /**
     * Get menu items
     * @return MenuItem
     */
    HashMap<Integer, MenuItem> getItems();

    /**
     * Set item to slot
     * @param slot int
     * @param item MenuItem
     * @return IMenu
     */
    IMenu setItem(int slot, MenuItem item);

    /**
     * Set item to slot
     * @param slot int
     * @param item ItemStack
     * @return IMenu
     */
    IMenu setItem(int slot, ItemStack item);

    /**
     * Set menu items
     * @param items MenuItems
     * @return IMenu
     */
    IMenu setItems(HashMap<Integer, MenuItem> items);

    /**
     * Update item
     * @param slot int
     * @return IMenu
     */
    IMenu updateItem(int slot);

    /**
     * Get action on open
     * @return InteractAction
     */
    InteractAction getOpenAction();

    /**
     * Set action on open
     * @param action InteractAction
     * @return IMenu
     */
    IMenu setOpenAction(InteractAction action);

    /**
     * Get action on close
     * @return InteractAction
     */
    InteractAction getCloseAction();

    /**
     * Set action on close
     * @param action InteractAction
     * @return IMenu
     */
    IMenu setCloseAction(InteractAction action);

    /**
     * Get menu title
     * @return String
     */
    String getTitle();

    /**
     * Set menu title
     * @param title String
     * @return IMenu
     */
    IMenu setTitle(String title);

    /**
     * Build Menu
     * @return IMenu
     */
    IMenu build();

    /**
     * Get inventory
     * @return Inventory
     */
    Inventory getInventory();

    /**
     * Execute Click
     * @param player Player
     * @param slot int
     * @param type ClickType
     * @return cancel
     */
    boolean execute(Player player, int slot, ClickType type);

    /**
     * Open menu for player
     * @param player Player
     */
    void open(Player player);

    /**
     * Clone Menu
     * @return IMenu
     */
    IMenu clone();

}
