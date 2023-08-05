package de.cellum.tpsystem.tp;

import de.cellum.tpsystem.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class TPManager implements Listener, CommandExecutor {
    private static boolean teleporting = false;
    private static BukkitRunnable teleportTask;
    private static Location teleportLocation;

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (teleporting && player.isOnline()) {
                cancelTeleport(player);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cAua! Dein Teleport wurde abgebrochen!"));
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (teleporting && player.isOnline()) {
            cancelTeleport(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cSneaky! Teleport abgebrochen!"));
        }
    }

    public static void startTeleport(Player player, double x, double y, double z, double yaw, double pitch) {
        if (teleporting) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cDu wirst bereits teleportiert"));
            player.closeInventory();
            return;
        }

        teleporting = true;

        player.sendMessage(Main.getPrefix() + "Du wirst in 20 Sekunden teleportiert. Sneak oder nimm Schaden, um den Vorgang abzubrechen.");

        teleportLocation = new Location(player.getWorld(), x, y, z, (float) yaw, (float) pitch);

        teleportTask = new BukkitRunnable() {
            int countdown = 20;

            @Override
            public void run() {
                if (countdown <= 0 || !player.isOnline()) {
                    teleportPlayer(player);
                    return;
                }

                if (teleporting && player.isOnline()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "Teleportiere in " + countdown + " Sekunden..."));
                    countdown--;
                } else {
                    cancelTeleport(player);
                }
            }
        };

        teleportTask.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

    public static void teleportPlayer(Player player) {
        if (teleportLocation != null) {
            player.teleport(teleportLocation);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1));
            player.playSound(player.getLocation(), Sound.ENTITY_MINECART_RIDING, 1.0f, 1.0f);
        }
        // Weitere Aktionen nach der Teleportation, falls erforderlich
        cancelTeleport(player);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§6Teleport abgeschlossen!"));
    }

    public static void cancelTeleport(Player player) {
        if (teleporting) {
            teleporting = false;
            if (teleportTask != null) {
                teleportTask.cancel();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equals("skipcount")){
                if (player.hasPermission(Objects.requireNonNull(Main.getPlugin().getConfig().getString("Perms.skipCountdown")))){
                    if (teleportLocation != null && teleporting) {
                        player.teleport(teleportLocation);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1));
                        player.playSound(player.getLocation(), Sound.ENTITY_MINECART_RIDING, 1.0f, 1.0f);
                    }else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cKein TP Countdown vorhanden!"));
                    }
                    cancelTeleport(player);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§6TP Countdown skipped!"));
                }else player.sendMessage(Main.getPrefix() + "§cKeine Rechte!");
            } //anderer Command
        } else Bukkit.getConsoleSender().sendMessage("Nix Console!");
        return false;
    }
}

