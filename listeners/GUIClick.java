package de.cellum.tpsystem.listeners;

import de.cellum.tpsystem.main.Main;
import de.cellum.tpsystem.tp.TPManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class GUIClick implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        FileConfiguration config = Main.getPlugin().getConfig();
        if (!p.hasPermission(Objects.requireNonNull(config.getString("Perms.tpuse")))) {
            p.sendMessage(Main.getPrefix() + "§cKeine Rechte fürs teleportieren!");
            p.closeInventory();
        if (event.getView().getTitle().equals("§6§lWestonias Wegschrein")) {
            if (event.getSlot() == 2){
                //TPPoint 1
                TPManager.startTeleport(p, config.getDouble("TPPoint.1.x") , config.getDouble("TPPoint.1.y"), config.getDouble("TPPoint.1.z"),config.getDouble("TPPoint.1.yaw"), config.getDouble("TPPoint.1.pitch"));
                p.closeInventory();
            }else{
                if (event.getSlot() == 4){
                    //TPPoint 2
                    TPManager.startTeleport(p, config.getDouble("TPPoint.2.x") , config.getDouble("TPPoint.2.y"), config.getDouble("TPPoint.2.z"),config.getDouble("TPPoint.2.yaw"), config.getDouble("TPPoint.2.pitch"));
                    p.closeInventory();
                }else{
                    if (event.getSlot() == 6){
                        //TPPoint 3
                        TPManager.startTeleport(p, config.getDouble("TPPoint.3.x") , config.getDouble("TPPoint.3.y"), config.getDouble("TPPoint.3.z"),config.getDouble("TPPoint.3.yaw"), config.getDouble("TPPoint.3.pitch"));
                        p.closeInventory();
                    }else{
                        if (event.getSlot() == 13){
                            //TPPoint 4
                            TPManager.startTeleport(p, config.getDouble("TPPoint.4.x") , config.getDouble("TPPoint.4.y"), config.getDouble("TPPoint.4.z"),config.getDouble("TPPoint.4.yaw"), config.getDouble("TPPoint.4.pitch"));
                            p.closeInventory();
                        } else if (event.getSlot() == 20) {
                            //TPPoint 5
                            TPManager.startTeleport(p, config.getDouble("TPPoint.5.x") , config.getDouble("TPPoint.5.y"), config.getDouble("TPPoint.5.z"),config.getDouble("TPPoint.5.yaw"), config.getDouble("TPPoint.5.pitch"));
                            p.closeInventory();
                        } else if (event.getSlot() == 22) {
                            //TPPoint 6
                            TPManager.startTeleport(p, config.getDouble("TPPoint.6.x") , config.getDouble("TPPoint.6.y"), config.getDouble("TPPoint.6.z"),config.getDouble("TPPoint.6.yaw"), config.getDouble("TPPoint.6.pitch"));
                            p.closeInventory();
                        }else if (event.getSlot() == 24){
                            //TPPoint 7
                            TPManager.startTeleport(p, config.getDouble("TPPoint.7.x") , config.getDouble("TPPoint.7.y"), config.getDouble("TPPoint.7.z"),config.getDouble("TPPoint.7.yaw"), config.getDouble("TPPoint.7.pitch"));
                            p.closeInventory();
                        }
                    }
                }
            }
        }
      }
    }
}

