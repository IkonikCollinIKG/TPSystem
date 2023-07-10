package de.cellum.tpsystem;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TPSystem extends JavaPlugin implements Listener {

    private Map<Material, Location> teleportLocations;
    private Map<Player, Integer> teleportCounters;

    @Override
    public void onEnable() {
        // Plugin aktivieren
        teleportLocations = new HashMap<Material, Location>();
        teleportCounters = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && clickedBlock != null && clickedBlock.getType() == Material.OAK_SIGN) {
            Sign sign = (Sign) clickedBlock.getState();
            if (sign.getLine(0).equalsIgnoreCase("[Teleport]")) {
                openTeleportGUI(player);
            }
        }
    }

    private void openTeleportGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Teleport Points");

        // Gold Block
        ItemStack goldBlockItem = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta goldBlockMeta = goldBlockItem.getItemMeta();
        goldBlockMeta.setDisplayName("Gold Block");
        goldBlockItem.setItemMeta(goldBlockMeta);
        gui.addItem(goldBlockItem);
        teleportLocations.put(Material.GOLD_BLOCK, new Location(player.getWorld(), 10, 20, 30));

        // Diamond Block
        ItemStack diamondBlockItem = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta diamondBlockMeta = diamondBlockItem.getItemMeta();
        diamondBlockMeta.setDisplayName("Diamond Block");
        diamondBlockItem.setItemMeta(diamondBlockMeta);
        gui.addItem(diamondBlockItem);
        teleportLocations.put(Material.DIAMOND_BLOCK, new Location(player.getWorld(), 40, 50, 60));

        // Iron Block
        ItemStack ironBlockItem = new ItemStack(Material.IRON_BLOCK);
        ItemMeta ironBlockMeta = ironBlockItem.getItemMeta();
        ironBlockMeta.setDisplayName("Iron Block");
        ironBlockItem.setItemMeta(ironBlockMeta);
        gui.addItem(ironBlockItem);
        teleportLocations.put(Material.IRON_BLOCK, new Location(player.getWorld(), 70, 80, 90));

        // Weitere Blöcke mit entsprechenden Koordinaten...

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && event.getView().getTitle().equals("Teleport Points")) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && teleportLocations.containsKey(clickedItem)) {
                Location destination = teleportLocations.get(clickedItem);
                if (destination != null) {
                    startTeleportation(player, destination);
                    startTeleportCooldown(player);
                }
            }
        }
    }

    private void startTeleportation(Player player, Location destination) {
        teleportCounters.put(player, 5);

        // Spielereigenschaften ändern (Blindheit, Bewegung deaktivieren)
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
        player.setWalkSpeed(0);
        player.setFlySpeed(0);

        // Karren-Sound abspielen
        player.playSound(player.getLocation(), Sound.ENTITY_MINECART_RIDING, 1, 1);

        // Teleportation nach 5 Sekunden
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(destination);
                player.setWalkSpeed(0.2f);
                player.setFlySpeed(0.1f);
                teleportCounters.remove(player);
            }
        }.runTaskLater(this, 100L);
    }

    private void startTeleportCooldown(Player player) {
        // Cooldown von 20 Sekunden
        teleportCounters.put(player, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                int counter = teleportCounters.getOrDefault(player, 0);
                if (counter <= 0) {
                    teleportCounters.remove(player);
                    cancel();
                } else {
                    teleportCounters.put(player, counter - 1);

                    // Actionbar aktualisieren
                    updateActionbar(player, counter);
                }
            }
        }.runTaskTimer(this, 20L, 20L);
    }

    private void updateActionbar(Player player, int counter) {
        String message = "Teleport in " + counter + " seconds";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

}




