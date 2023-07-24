package de.cellum.tpsystem.listeners;

import de.cellum.tpsystem.main.Main;
import de.cellum.tpsystem.util.TPGui;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class SignListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType() == Material.OAK_SIGN || block.getType() == Material.OAK_WALL_SIGN || block.getType() == Material.SPRUCE_SIGN || block.getType() == Material.SPRUCE_WALL_SIGN) {
                Sign sign = (Sign) block.getState();
                if (sign.getLine(0).contains("-Wegschrein-") && sign.getLine(1).contains("Willkommen auf") && sign.getLine(2).contains("dem Pfad!") && sign.getLine(3).contains("[TPSystem]")) {
                    TPGui.openTPGUI(event.getPlayer());
                }
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.OAK_SIGN || block.getType() == Material.OAK_WALL_SIGN || block.getType() == Material.SPRUCE_SIGN || block.getType() == Material.SPRUCE_WALL_SIGN) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).contains("-Wegschrein-") && sign.getLine(1).contains("Willkommen auf") && sign.getLine(2).contains("dem Pfad!") && sign.getLine(3).contains("[TPSystem]")) {
                 if (event.getPlayer().hasPermission(Objects.requireNonNull(Main.getPlugin().getConfig().getString("Perms.admin"))) || event.getPlayer().hasPermission(Objects.requireNonNull(Main.getPlugin().getConfig().getString("Perms.createSign")))){
                     event.getPlayer().sendMessage(Main.getPrefix() + "Neues Schild platziert!");
                 }else{
                     event.setCancelled(true);
                     event.getPlayer().sendMessage(Main.getPrefix() + "Du Schlingel darfst das nicht tun! Reise nur mit den offiziellen Schildern!");
                 }
            }
        }
    }
}

