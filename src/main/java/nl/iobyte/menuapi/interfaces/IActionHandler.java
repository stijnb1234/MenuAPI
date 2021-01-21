package nl.iobyte.menuapi.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import java.util.List;

public interface IActionHandler {

    /**
     * Get ID of the handler
     * @return String
     */
    String getID();

    /**
     * Get ClickType's of handler
     * @return List<ClickType>
     */
    List<ClickType> getClickTypes();

    /**
     * Action to perform on click
     * @param player Player
     */
    void execute(Player player);

}
