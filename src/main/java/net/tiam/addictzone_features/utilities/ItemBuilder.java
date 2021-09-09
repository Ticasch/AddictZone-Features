package net.tiam.addictzone_features.utilities;

import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    ItemStack itemStack;

    ItemMeta imeta;

    SkullMeta skullMeta;

    public ItemBuilder(String displayname, Material material, byte subid, int amount) {
        this.itemStack = new ItemStack(material, amount, (short)subid);
        this.imeta = this.itemStack.getItemMeta();
        this.imeta.setDisplayName(displayname);
    }

    public ItemBuilder(String displayname, Material material, int amount, String lore) {
        this.itemStack = new ItemStack(material, amount);
        this.imeta = this.itemStack.getItemMeta();
        this.imeta.setDisplayName(displayname);
        this.imeta.setLore(Arrays.asList(new String[] { lore }));
    }

    public ItemBuilder(String displayname, Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.imeta = this.itemStack.getItemMeta();
        this.imeta.setDisplayName(displayname);
    }

    public ItemBuilder(String displayname, Material material, int amount, Enchantment ench, int enchlvl) {
        this.itemStack = new ItemStack(material, amount);
        this.imeta = this.itemStack.getItemMeta();
        this.imeta.setDisplayName(displayname);
        this.imeta.addEnchant(ench, enchlvl, true);
    }

    public ItemBuilder(Material material, int amount, Enchantment ench, int enchlvl) {
        this.itemStack = new ItemStack(material, amount);
        this.imeta = this.itemStack.getItemMeta();
        this.imeta.addEnchant(ench, enchlvl, true);
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.imeta);
        return this.itemStack;
    }

    public ItemStack buildSkull() {
        this.itemStack.setItemMeta((ItemMeta)this.skullMeta);
        return this.itemStack;
    }
}

