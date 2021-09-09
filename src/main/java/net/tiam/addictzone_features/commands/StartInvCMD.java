package net.tiam.addictzone_features.commands;

import net.tiam.addictzone_features.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.enchantments.Enchantment.*;
import static org.bukkit.enchantments.Enchantment.DIG_SPEED;

public class StartInvCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmnd, String string, String[] args) {
        if (sender.isOp())
            if (sender instanceof Player) {
                Player player = (Player)sender;
                player.getInventory().clear();
                player.getInventory().setItem(9, new ItemStack(Material.OAK_LOG, 16));
                player.getInventory().setItem(10, new ItemStack(Material.BIRCH_LOG, 16, (short)1));
                player.getInventory().setItem(11, new ItemStack(Material.SPRUCE_LOG, 16, (short)2));
                player.getInventory().setItem(13, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_HELMET, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(15, new ItemStack(Material.DARK_OAK_LOG, 16));
                player.getInventory().setItem(16, new ItemStack(Material.JUNGLE_LOG, 16));
                player.getInventory().setItem(17, new ItemStack(Material.ACACIA_LOG, 16));
                player.getInventory().setItem(18, new ItemStack(Material.OAK_SAPLING, 16));
                player.getInventory().setItem(19, new ItemStack(Material.BIRCH_SAPLING, 16));
                player.getInventory().setItem(20, new ItemStack(Material.SPRUCE_SAPLING, 16));
                player.getInventory().setItem(22, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_CHESTPLATE, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(24, new ItemStack(Material.DARK_OAK_SAPLING, 16));
                player.getInventory().setItem(25, new ItemStack(Material.JUNGLE_SAPLING, 16));
                player.getInventory().setItem(26, new ItemStack(Material.ACACIA_SAPLING, 16));
                player.getInventory().setItem(31, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_LEGGINGS, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(0, new ItemStack(Material.BREAD, 64));
                player.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF, 64));
                player.getInventory().setItem(2, new ItemBuilder("§9§lStarter Schwert", Material.IRON_SWORD, 1, DAMAGE_ALL, 2).build());
                player.getInventory().setItem(4, new ItemBuilder("§9§lStarter Rüstung", Material.IRON_BOOTS, 1, PROTECTION_ENVIRONMENTAL, 2).build());
                player.getInventory().setItem(6, new ItemBuilder("§9§lStarter Schaufel", Material.IRON_SHOVEL, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(7, new ItemBuilder("§9§lStarter Axt", Material.IRON_AXE, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(8, new ItemBuilder("§9§lStarter Spitzhacke", Material.IRON_PICKAXE, 1, DIG_SPEED, 2).build());
                player.getInventory().setItem(12, new ItemStack(Material.WARPED_STEM, 16));
                player.getInventory().setItem(14, new ItemStack(Material.CRIMSON_STEM, 16));
                player.getInventory().setItem(27, new ItemStack(Material.CHEST, 4));
                player.getInventory().setItem(28, new ItemStack(Material.FURNACE, 2));
                player.getInventory().setItem(34, new ItemStack(Material.CRAFTING_TABLE, 2));
                player.getInventory().setItem(35, new ItemStack(Material.BARREL, 4));
                player.updateInventory();
            }
        return false;
    }
}
