package nl.iobyte.menuapi.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material material) {
        item = new ItemStack(material, 1);
    }

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, short data) {
        item = new ItemStack(material, amount, data);
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemBuilder setType(Material material) {
        if(material == null)
            return this;

        item.setType(material);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean b) {
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(b);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setName(String name) {
        if(name == null)
            return this;

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Color.parse(name));
        this.item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if(lore == null)
            return this;

        ItemMeta meta = item.getItemMeta();
        for(String string : lore)
            lore.set(lore.indexOf(string), Color.parse(string));

        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        List<String> list = new ArrayList<>();
        list.add(lore);
        setLore(list);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> list = item.getItemMeta().getLore();
        list.add(lore);
        setLore(list);
        return this;
    }

    public ItemBuilder setEnchantment(HashMap<Enchantment, Integer> enchantments) {
        ItemMeta meta = item.getItemMeta();
        if(!meta.getEnchants().isEmpty())
            meta.getEnchants().clear();

        for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
            meta.addEnchant(entry.getKey(), entry.getValue(), true);

        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int i) {
        ItemMeta meta = item.getItemMeta();
        if (!meta.getEnchants().isEmpty())
            meta.getEnchants().clear();

        meta.addEnchant(enchantment, i, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int i) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, i, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flag);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addFlags(ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder clone() {
        return new ItemBuilder(item.clone());
    }

}