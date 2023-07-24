package de.cellum.tpsystem.util;

import de.cellum.tpsystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class TPGui implements Listener {
    public static void openTPGUI(Player player){
        // GUI initialisieren
        Inventory gui = Bukkit.createInventory(null, 9 * 3, "§6§lWestonias Wegschrein");

        //Items erstellen

        //Port 1 Item
        FileConfiguration config = Main.getPlugin().getConfig();
        ItemStack Port1 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port1Meta = Port1.getItemMeta();
        Port1Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.1.name")).replaceAll("&","§"));
        ArrayList<String> Port1Lore = new ArrayList<String>();
        Port1Lore.add("§9Klicke zum teleportieren!");
        Port1Meta.setLore(Port1Lore);
        Port1Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port1.setItemMeta(Port1Meta);

        //Port 2 Item
        ItemStack Port2 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port2Meta = Port2.getItemMeta();
        Port2Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.2.name")).replaceAll("&","§"));
        ArrayList<String> Port2Lore = new ArrayList<String>();
        Port2Lore.add("§9Klicke zum teleportieren!");
        Port2Meta.setLore(Port2Lore);
        Port2Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port2.setItemMeta(Port2Meta);

        //Port 3 Item
        ItemStack Port3 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port3Meta = Port3.getItemMeta();
        Port3Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.3.name")).replaceAll("&","§"));
        ArrayList<String> Port3Lore = new ArrayList<String>();
        Port3Lore.add("§9Klicke zum teleportieren!");
        Port3Meta.setLore(Port3Lore);
        Port3Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port3.setItemMeta(Port3Meta);

        //Port 4 Item
        ItemStack Port4 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port4Meta = Port4.getItemMeta();
        Port4Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.4.name")).replaceAll("&","§"));
        ArrayList<String> Port4Lore = new ArrayList<String>();
        Port4Lore.add("§9Klicke zum teleportieren!");
        Port4Meta.setLore(Port4Lore);
        Port4Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port4.setItemMeta(Port4Meta);
        
        //Port 5 Item
        ItemStack Port5 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port5Meta = Port5.getItemMeta();
        Port5Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.5.name")).replaceAll("&","§"));
        ArrayList<String> Port5Lore = new ArrayList<String>();
        Port5Lore.add("§9Klicke zum teleportieren!");
        Port5Meta.setLore(Port5Lore);
        Port5Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port5.setItemMeta(Port5Meta);

        //Port 6 Item
        ItemStack Port6 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port6Meta = Port6.getItemMeta();
        Port6Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.6.name")).replaceAll("&","§"));
        ArrayList<String> Port6Lore = new ArrayList<String>();
        Port6Lore.add("§9Klicke zum teleportieren!");
        Port6Meta.setLore(Port6Lore);
        Port6Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port6.setItemMeta(Port6Meta);

        //Port 7 Item
        ItemStack Port7 = new ItemStack(Material.IRON_INGOT);
        ItemMeta Port7Meta = Port7.getItemMeta();
        Port7Meta.setDisplayName(Objects.requireNonNull(config.getString("TPPoint.7.name")).replaceAll("&","§"));
        ArrayList<String> Port7Lore = new ArrayList<String>();
        Port7Lore.add("§9Klicke zum teleportieren!");
        Port7Meta.setLore(Port7Lore);
        Port7Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        Port7.setItemMeta(Port7Meta);

        //Nix
        ItemStack Nix = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nixData = Nix.getItemMeta();
        nixData.setDisplayName("§c");
        Nix.setItemMeta(nixData);

        //GUi Gegenstände setzten
        int i;
        for (i = 0; i < 27; i++) {
            gui.setItem(i,Nix);
        }
        gui.setItem(2, Port1);
        gui.setItem(4, Port2);
        gui.setItem(6, Port3);
        gui.setItem(13,Port4);
        gui.setItem(20,Port5);
        gui.setItem(22,Port6);
        gui.setItem(24,Port7);

        // GUI öffnen
        player.openInventory(gui);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("§6§lWestonias Wegschrein")) {
            event.setCancelled(true);
        }
    }
}
