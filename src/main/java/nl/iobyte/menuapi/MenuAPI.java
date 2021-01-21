package nl.iobyte.menuapi;

import nl.iobyte.menuapi.interfaces.IActionHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;

public class MenuAPI {

    private static boolean registered = false;
    private static final HashMap<String, IActionHandler> actionHandlers = new HashMap<>();

    /**
     * Add ActionHandler
     * @param handler IActionHandler
     */
    public static void addActionHandler(IActionHandler handler) {
        actionHandlers.put(handler.getID(), handler);
    }

    /**
     * Get IActionHandler by ID
     * @param id String
     * @return IActionHandler
     */
    public static IActionHandler getActionHandler(String id) {
        return actionHandlers.get(id);
    }

    /**
     * Has IActionHandler with specific ID
     * @param id String
     * @return Boolean
     */
    public static boolean hasActionHandler(String id) {
        return actionHandlers.containsKey(id);
    }

    /**
     * Remove IActionHandler by ID
     * @param id String
     * @return IActionHandler
     */
    public static IActionHandler removeActionHandler(String id) {
        return actionHandlers.remove(id);
    }

    public static void register(Plugin plugin) {
        if(registered || plugin == null)
            return;

        Bukkit.getPluginManager().registerEvents(new MenuListener(), plugin);
        registered = true;
    }

}
