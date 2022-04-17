package me.commandf1.LevelPVP;

import me.commandf1.LevelPVP.PlaceHolder.PlaceHolders;
import me.commandf1.LevelPVP.Tasks.CrackedVersionAlertTask;
import me.commandf1.LevelPVP.Tasks.DeathTask;
import me.commandf1.LevelPVP.Tasks.ScoreBoardTask;
import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.PluginUtils;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import me.commandf1.LevelPVP.Utils.VerificationUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static Logger logger;
    public static Main instance;

    @Override
    public void onEnable() {
        logger = getLogger();
        instance = this;
        logger.info("Plugin loading...");
        if (!VerificationUtils.verification()) {
            logger.severe("Verification failed!");
            logger.info("HWID: " + VerificationUtils.getHWID());
            logger.info("Please contact the author!");
            Bukkit.getPluginManager().disablePlugin(Main.instance);
            return;
        }
        PluginUtils.CheckCrackedVersion();

        ServerUtils.loadListeners();
        new PlaceHolders().register();
        if (!new File(getDataFolder(), "game.yml").exists()) {
            saveResource("game.yml", false);
        }
        if (!new File(getDataFolder(), "data.yml").exists()) {
            saveResource("data.yml", false);
        }
        if (!new File(getDataFolder(), "message.yml").exists()) {
            saveResource("message.yml", false);
        }
        if (!new File(getDataFolder(), "config.yml").exists()) {
            getConfig().options().copyDefaults();
            saveDefaultConfig();
        }
        ServerUtils.loadCommands();
        Bukkit.setSpawnRadius(0);
        if (GameUtils.isSetUp) {
            World world = Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world"));
            world.setSpawnLocation((int) ServerUtils.getGame().getDouble("lobby.x"),
                    (int) ServerUtils.getGame().getDouble("lobby.y"),
                    (int) ServerUtils.getGame().getDouble("lobby.z"));
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ScoreBoardTask(), 20, 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new DeathTask(), 0, 0);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new CrackedVersionAlertTask(), 20 * 60, 20 * 60);
        logger.info("Plugin loaded");
        if (!PluginUtils.isCrackedVersion) {
            logger.info("Thanks for buying the plugin!");
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        logger.info("Bye, Thanks for using.");
    }

}
