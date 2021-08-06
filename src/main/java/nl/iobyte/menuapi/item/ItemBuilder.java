package nl.iobyte.menuapi.item;

import java.util.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

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

    public ItemBuilder(Material material, int amount, int data) {
        this(material, amount);
        setCustomModelData(data);
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

    public ItemBuilder setCustomModelData(int i) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.setCustomModelData(i);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder setUnbreakable(boolean b) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.setUnbreakable(b);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder setName(String name) {
        if(name == null)
            return this;

        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.setDisplayName(Color.parse(name));
            this.item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        if(lore == null)
            return this;

        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            for (String string : lore)
                lore.set(lore.indexOf(string), Color.parse(string));

            meta.setLore(lore);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder setLore(String lore) {
        List<String> list = new ArrayList<>();
        list.add(lore);
        setLore(list);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            List<String> list = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            list.add(lore);
            setLore(list);
        });
        return this;
    }

    public ItemBuilder setEnchantment(HashMap<Enchantment, Integer> enchantments) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            if (!meta.getEnchants().isEmpty())
                meta.getEnchants().clear();

            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
                meta.addEnchant(entry.getKey(), entry.getValue(), true);

            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int i) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            if (!meta.getEnchants().isEmpty())
                meta.getEnchants().clear();

            meta.addEnchant(enchantment, i, true);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int i) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.addEnchant(enchantment, i, true);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.addItemFlags(flag);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder addFlags(ItemFlag... flags) {
        Optional.ofNullable(item.getItemMeta()).ifPresent(meta -> {
            meta.addItemFlags(flags);
            item.setItemMeta(meta);
        });
        return this;
    }

    public ItemBuilder clone() {
        return new ItemBuilder(item.clone());
    }

}