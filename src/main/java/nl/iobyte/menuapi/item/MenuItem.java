package nl.iobyte.menuapi.item;

import nl.iobyte.menuapi.action.HandlerAction;
import nl.iobyte.menuapi.interfaces.IActionHandler;
import nl.iobyte.menuapi.action.MenuAction;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MenuItem {

    private ItemStack item;
    private boolean cancel;
    private HashMap<ClickType, ArrayList<MenuAction>> actions = new HashMap<>();

    public MenuItem(ItemStack item, boolean cancel) {
        this.item = item;
        this.cancel = cancel;
    }

    /**
     * Get item
     * @return ItemStack instance
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Set item
     * @param item ItemStack instance
     * @return MenuItem instance
     */
    public MenuItem setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    /**
     * Get Actions for ClickType
     * @param type Type of Click
     * @return Array of MenuActions
     */
    public ArrayList<MenuAction> getActions(ClickType type) {
        if(type == null)
            return null;

        if(!actions.containsKey(type))
            return null;

        return actions.get(type);
    }

    /**
     * Add actions to MenuItem
     * @param type ClickType
     * @param actions Array of MenuActions
     * @return MenuItem Instance
     */
    public MenuItem addActions(ClickType type, MenuAction... actions) {
        if(type == null || actions == null)
            return this;

        ArrayList<MenuAction> array = new ArrayList<>();
        if(this.actions.containsKey(type)) {
            array = this.actions.get(type);
        } else {
            this.actions.put(type, array);
        }

        array.addAll(Arrays.asList(actions));
        return this;
    }

    /**
     * Add actions to MenuItem
     * @param types List of ClickType
     * @param actions Array of MenuActions
     * @return MenuItem Instance
     */
    public MenuItem addActions(List<ClickType> types, MenuAction... actions) {
        for(ClickType type : types)
            addActions(type, actions);

        return this;
    }

    /**
     * Add actions to MenuItem for any type of Click
     * @param actions Array of MenuActions
     * @return MenuItem Instance
     */
    public MenuItem addActions(MenuAction... actions) {
        for(ClickType type : ClickType.values()) {
            addActions(type, actions);
            addActions(type, actions);
        }

        return this;
    }

    /**
     * Add actions to MenuItem
     * @param handlers IActionHandler[]
     * @return MenuItem Instance
     */
    public MenuItem addActions(IActionHandler... handlers) {
        for(IActionHandler handler : handlers)
            addActions(handler.getClickTypes(), new MenuAction() {
                public void execute(Player player) {
                    handler.execute(player);
                }
            });

        return this;
    }

    /**
     * Get if should do cancel
     * @return boolean
     */
    public boolean doCancel() {
        return cancel;
    }

    /**
     * Set if should do cancel
     * @param cancel boolean
     * @return MenuItem Instance
     */
    public MenuItem setCancel(boolean cancel) {
        this.cancel = cancel;
        return this;
    }

    /**
     * Clone menu item
     * @return MenuItem
     */
    public MenuItem clone() {
        MenuItem mi = new MenuItem(item.clone(), cancel);
        mi.actions = actions;
        return mi;
    }

}
