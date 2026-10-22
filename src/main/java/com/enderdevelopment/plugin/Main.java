package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("skyinventory").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openSkyInventory(player);
            return true;
        }
        return false;
    }

    private void openSkyInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Sky Inventory");

        // Create a blue background with a sky theme
        ItemStack background = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta meta = background.getItemMeta();
        meta.setDisplayName(" ");
        background.setItemMeta(meta);

        // Fill the inventory with the blue background
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, background);
        }

        // Add a sun in the top-right corner
        ItemStack sun = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta sunMeta = sun.getItemMeta();
        sunMeta.setDisplayName("Sun");
        sun.setItemMeta(sunMeta);
        inventory.setItem(2, sun);

        // Add clouds
        ItemStack cloud = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta cloudMeta = cloud.getItemMeta();
        cloudMeta.setDisplayName("Cloud");
        cloud.setItemMeta(cloudMeta);
        inventory.setItem(10, cloud);
        inventory.setItem(12, cloud);
        inventory.setItem(14, cloud);
        inventory.setItem(16, cloud);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Sky Inventory")) {
            event.setCancelled(true);
        }
    }
}