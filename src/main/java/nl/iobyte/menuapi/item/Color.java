package nl.iobyte.menuapi.item;

import org.bukkit.ChatColor;

public class Color {

    public static String parse(String str) {
        if(str == null || str.isEmpty())
            return str;

        return ChatColor.translateAlternateColorCodes('&', str);
    }

}
