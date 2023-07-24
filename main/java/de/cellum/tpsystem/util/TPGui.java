package de.cellum.tpsystem.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TPGui implements Listener {
    public static void openTPGUI(Player player){
        // GUI initialisieren
        Inventory gui = Bukkit.createInventory(null, 9 * 3, "§6§lWestonia Reise System");

        // GUI-Inhalt festlegen
        gui.setItem(0, new ItemStack(Material.STONE));

        // GUI öffnen
        player.openInventory(gui);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6§lWestonia Reise System")) {
            event.setCancelled(true);
        }
    }
}
