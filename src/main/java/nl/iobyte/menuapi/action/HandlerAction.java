package nl.iobyte.menuapi.action;

import nl.iobyte.menuapi.MenuAPI;
import nl.iobyte.menuapi.interfaces.IActionHandler;
import org.bukkit.entity.Player;

public class HandlerAction extends MenuAction {

    private final String handler;

    public HandlerAction(String handler) {
        this.handler = handler;
    }

    public void execute(Player player) {
        IActionHandler handler = MenuAPI.getActionHandler(this.handler);
        if(handler == null)
            return;

        handler.execute(player);
    }

}
