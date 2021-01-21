package nl.iobyte.menuapi;

import nl.iobyte.menuapi.interfaces.IMenu;
import nl.iobyte.menuapi.action.InteractAction;
import nl.iobyte.menuapi.multi.MenuPage;
import nl.iobyte.menuapi.multi.MultiMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null)
            return;

        Inventory inventory = e.getClickedInventory();
        if(!(inventory.getHolder() instanceof IMenu))
            return;

        if(e.isCancelled())
            return;

        Player player = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();
        ClickType type = e.getClick();

        IMenu menu = (IMenu) inventory.getHolder();
        e.setCancelled(menu.execute(player, slot, type));
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        if(!(inventory.getHolder() instanceof IMenu))
            return;

        if(!(inventory.getHolder() instanceof MenuPage)) {
            IMenu menu = (IMenu) inventory.getHolder();
            InteractAction open = menu.getOpenAction();
            if (open == null)
                return;

            open.execute(player);
            e.setCancelled(open.doCancel());
        } else {
            MenuPage page = (MenuPage) inventory.getHolder();
            MultiMenu menu = page.getParent();
            if(!menu.isActivePage(player.getUniqueId(), page))
                return;

            InteractAction open = menu.getOpenAction();
            if (open == null)
                return;

            open.execute(player);
            e.setCancelled(open.doCancel());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        if(!(inventory.getHolder() instanceof IMenu))
            return;

        if(!(inventory.getHolder() instanceof MenuPage)) {
            IMenu menu = (IMenu) inventory.getHolder();
            InteractAction close = menu.getCloseAction();
            if(close == null)
                return;

            close.execute(player);
            if(close.doCancel())
                menu.open(player);
        } else {
            MenuPage page = (MenuPage) inventory.getHolder();
            MultiMenu menu = page.getParent();
            if(!menu.isActivePage(player.getUniqueId(), page))
                return;

            InteractAction close = menu.getCloseAction();
            if (close == null)
                return;

            close.execute(player);
            if(close.doCancel()) {
                page.open(player);
            } else {
                menu.close(player);
            }
        }
    }

}
