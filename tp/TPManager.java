package de.cellum.tpsystem.tp;

import de.cellum.tpsystem.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TPManager implements Listener {
    private static boolean teleporting = false;
    private static BukkitRunnable teleportTask;
    private static Location teleportLocation;

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (teleporting && player.isOnline()) {
                cancelTeleport(player);
            }
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (teleporting && player.isOnline()) {
            cancelTeleport(player);
        }
    }

    public static void startTeleport(Player player, double x, double y, double z, double yaw, double pitch) {
        if (teleporting) {
            player.sendMessage(Main.getPrefix() + "Du wirst bereits teleportiert.");
            return;
        }

        teleporting = true;

        player.sendMessage(Main.getPrefix() + "Du wirst in 20 Sekunden teleportiert. Sneak oder nimm Schaden, um den Vorgang abzubrechen.");

        teleportLocation = new Location(player.getWorld(), x, y, z, (float) yaw, (float) pitch);

        teleportTask = new BukkitRunnable() {
            int countdown = Main.getPlugin().getConfig().getInt("Countdown.time");

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
        }
        player.sendMessage(Main.getPrefix() + "Teleport abgeschlossen.");
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5*20, 1));
        player.playSound(player.getLocation(), Sound.ENTITY_MINECART_RIDING, 1.0f, 1.0f);
        // Weitere Aktionen nach der Teleportation, falls erforderlich
        cancelTeleport(player);
    }

    public static void cancelTeleport(Player player) {
        if (teleporting) {
            teleporting = false;
            if (teleportTask != null) {
                teleportTask.cancel();
            }
            if (teleportLocation != null) {
                player.teleport(teleportLocation);
            }
        }
    }
}

