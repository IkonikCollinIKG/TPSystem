package de.cellum.tpsystem.main;

import de.cellum.tpsystem.commands.AdminCommands;
import de.cellum.tpsystem.listeners.SignListener;
import de.cellum.tpsystem.util.Config;
import de.cellum.tpsystem.util.TPGui;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main plugin;
    public static String Prefix = "§7[§6TP§aSystem§7] §6>> §6";
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("Westonia Teleport System geladen!");
        plugin = this;
        TPPointsConfig();
        PermsFileConfig();
        Objects.requireNonNull(getCommand("tpsystem")).setExecutor(new AdminCommands());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new TPGui(), this);
        pm.registerEvents(new SignListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void TPPointsConfig(){
        FileConfiguration config = Main.getPlugin().getConfig();
        Config.TPPointsWriteDefaultFile();
        if (config.getConfigurationSection("TPPoint") == null){
            config.createSection("TPPoint");
            config.options().copyDefaults(true);
            getPlugin().saveConfig();
        }
    }
    public void PermsFileConfig(){
        FileConfiguration config = Main.getPlugin().getConfig();
        Config.PermsFileWriteDefaultConfig();
        if (config.getConfigurationSection("Perms") == null){
            config.createSection("Perms");
            config.options().copyDefaults(true);
            getPlugin().saveConfig();
        }
    }
    public static Main getPlugin() {
        return plugin;
    }
    public static String getPrefix() {
        return Prefix;
    }
}
