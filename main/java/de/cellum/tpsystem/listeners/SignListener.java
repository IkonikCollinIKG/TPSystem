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
            if (block.getType() == Material.OAK_SIGN) {
                Sign sign = (Sign) block.getState();
                if (sign.getLine(0).contains("Westonia") && sign.getLine(1).contains("Reise") && sign.getLine(2).contains("System")) {
                    TPGui.openTPGUI(event.getPlayer());
                }
            }
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.OAK_SIGN) {
            Sign sign = (Sign) block.getState();
            if (sign.getLine(0).contains("Westonia") &&
                    sign.getLine(1).contains("Reise") &&
                    sign.getLine(2).contains("System")) {
                 if (!event.getPlayer().hasPermission(Objects.requireNonNull(Main.getPlugin().getConfig().getString("Perms.admin"))) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
                     event.setCancelled(true);
                     event.getPlayer().sendMessage(Main.getPrefix() + "Du Schlingel darfst das nicht tun! Reise mit den offiziellen Schildern!");
                 }else event.getPlayer().sendMessage(Main.getPrefix() + "Neues Schild platziert!");
            }
        }
    }
}

