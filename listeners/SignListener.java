package de.cellum.tpsystem.listeners;

import de.cellum.tpsystem.main.Main;
import de.cellum.tpsystem.util.TPGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
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
}

