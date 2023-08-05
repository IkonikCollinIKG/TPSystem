package de.cellum.tpsystem.commands;

import de.cellum.tpsystem.main.Main;
import de.cellum.tpsystem.util.Config;
import de.cellum.tpsystem.util.TPGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(Objects.requireNonNull(Main.getPlugin().getConfig().getString("Perms.admin")))){
            if (args[0].equals("setpoint")){
                FileConfiguration config = Main.getPlugin().getConfig();
                switch (args[1]){
                    case "1":
                        config.set("TPPoint.1.world", player.getWorld().getName());
                        config.set("TPPoint.1.x", player.getLocation().getX());
                        config.set("TPPoint.1.y",player.getLocation().getY());
                        config.set("TPPoint.1.z", player.getLocation().getZ());
                        config.set("TPPoint.1.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.1.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 1 neu gesetzt!");
                        break;
                    case "2":
                        config.set("TPPoint.2.world", player.getWorld().getName());
                        config.set("TPPoint.2.x", player.getLocation().getX());
                        config.set("TPPoint.2.y",player.getLocation().getY());
                        config.set("TPPoint.2.z", player.getLocation().getZ());
                        config.set("TPPoint.2.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.2.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 2 neu gesetzt!");
                        break;
                    case "3":
                        config.set("TPPoint.3.world", player.getWorld().getName());
                        config.set("TPPoint.3.x", player.getLocation().getX());
                        config.set("TPPoint.3.y",player.getLocation().getY());
                        config.set("TPPoint.3.z", player.getLocation().getZ());
                        config.set("TPPoint.3.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.3.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 3 neu gesetzt!");
                        break;
                    case "4":
                        config.set("TPPoint.4.world", player.getWorld().getName());
                        config.set("TPPoint.4.x", player.getLocation().getX());
                        config.set("TPPoint.4.y",player.getLocation().getY());
                        config.set("TPPoint.4.z", player.getLocation().getZ());
                        config.set("TPPoint.4.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.4.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 4 neu gesetzt!");
                        break;
                    case "5":
                        config.set("TPPoint.5.world", player.getWorld().getName());
                        config.set("TPPoint.5.x", player.getLocation().getX());
                        config.set("TPPoint.5.y",player.getLocation().getY());
                        config.set("TPPoint.5.z", player.getLocation().getZ());
                        config.set("TPPoint.5.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.5.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 4 neu gesetzt!");
                        break;
                    case "6":
                        config.set("TPPoint.6.world", player.getWorld().getName());
                        config.set("TPPoint.6.x", player.getLocation().getX());
                        config.set("TPPoint.6.y",player.getLocation().getY());
                        config.set("TPPoint.6.z", player.getLocation().getZ());
                        config.set("TPPoint.6.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.6.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 6 neu gesetzt!");
                        break;
                    case "7":
                        config.set("TPPoint.7.world", player.getWorld().getName());
                        config.set("TPPoint.7.x", player.getLocation().getX());
                        config.set("TPPoint.7.y",player.getLocation().getY());
                        config.set("TPPoint.7.z", player.getLocation().getZ());
                        config.set("TPPoint.7.yaw",player.getLocation().getYaw());
                        config.set("TPPoint.7.pitch", player.getLocation().getPitch());
                        Main.getPlugin().saveConfig();
                        player.sendMessage(Main.Prefix + "Du hast Point 7 neu gesetzt!");
                        break;
                    default:
                        player.sendMessage(Main.getPrefix() + "Es gibt nur TP-Punkte von 1 bis 7!");
                        break;
                }
            }else{
                if (args[0].equals("gui")){
                    TPGui.openTPGUI(player);
                    player.sendMessage(Main.getPrefix() + "Admin GUI geöffnet!");
                }
            }
        }else {
            player.sendMessage(Main.getPrefix() + "§cKeine Rechte für diesen Command!");
        }
        return false;
    }
}
