package de.cellum.tpsystem.util;

import de.cellum.tpsystem.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    //Filemanagement


    // TPPointsFile Management
    public static void TPPointsWriteDefaultFile() {
        FileConfiguration config = Main.getPlugin().getConfig();
        // 1.TPPoint 
        config.addDefault("TPPoint.1.world","0");
        config.addDefault("TPPoint.1.x", "0");
        config.addDefault("TPPoint.1.y","0");
        config.addDefault("TPPoint.1.z", "0");
        config.addDefault("TPPoint.1.yaw","0");
        config.addDefault("TPPoint.1.pitch", "0");
        // 2.TPPoint 
        config.addDefault("TPPoint.2.world","0");
        config.addDefault("TPPoint.2.x", "0");
        config.addDefault("TPPoint.2.y","0");
        config.addDefault("TPPoint.2.z", "0");
        config.addDefault("TPPoint.2.yaw","0");
        config.addDefault("TPPoint.2.pitch", "0");
        // 3.TPPoint 
        config.addDefault("TPPoint.3.world","0");
        config.addDefault("TPPoint.3.x", "0");
        config.addDefault("TPPoint.3.y","0");
        config.addDefault("TPPoint.3.z", "0");
        config.addDefault("TPPoint.3.yaw","0");
        config.addDefault("TPPoint.3.pitch", "0");
        // 4.TPPoint 
        config.addDefault("TPPoint.4.world","0");
        config.addDefault("TPPoint.4.x", "0");
        config.addDefault("TPPoint.4.y","0");
        config.addDefault("TPPoint.4.z", "0");
        config.addDefault("TPPoint.4.yaw","0");
        config.addDefault("TPPoint.4.pitch", "0");
        // 5.TPPoint 
        config.addDefault("TPPoint.5.world","0");
        config.addDefault("TPPoint.5.x", "0");
        config.addDefault("TPPoint.5.y","0");
        config.addDefault("TPPoint.5.z", "0");
        config.addDefault("TPPoint.5.yaw","0");
        config.addDefault("TPPoint.5.pitch", "0");
        // 6.TPPoint 
        config.addDefault("TPPoint.6.world","0");
        config.addDefault("TPPoint.6.x", "0");
        config.addDefault("TPPoint.6.y","0");
        config.addDefault("TPPoint.6.z", "0");
        config.addDefault("TPPoint.6.yaw","0");
        config.addDefault("TPPoint.6.pitch", "0");
        // 7.TPPoint 
        config.addDefault("TPPoint.7.world","0");
        config.addDefault("TPPoint.7.x", "0");
        config.addDefault("TPPoint.7.y","0");
        config.addDefault("TPPoint.7.z", "0");
        config.addDefault("TPPoint.7.yaw","0");
        config.addDefault("TPPoint.7.pitch", "0");
        config.options().copyDefaults(true);
        Main.getPlugin().saveConfig();
    }

    //PermsFile Management

    public static void PermsFileWriteDefaultConfig() {
        FileConfiguration config = Main.getPlugin().getConfig();
        // 1.TPPoint
        config.addDefault("Perms.admin", "tpsystem.admin");
        config.addDefault("Perms.tpuse", "tpsystem.tpuse");
        config.options().copyDefaults(true);
        Main.getPlugin().saveConfig();
    }
}
