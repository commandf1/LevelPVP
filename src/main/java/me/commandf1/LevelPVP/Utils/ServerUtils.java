package me.commandf1.LevelPVP.Utils;

import me.commandf1.LevelPVP.Commands.JoinCommand;
import me.commandf1.LevelPVP.Commands.MainCommand;
import me.commandf1.LevelPVP.Listeners.*;
import me.commandf1.LevelPVP.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.io.File;

public class ServerUtils {

    private static YamlConfiguration game = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "game.yml"));

    private static YamlConfiguration data = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "data.yml"));

    private static YamlConfiguration message = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "message.yml"));

    public static void loadListeners() {
        loadListener(new PlayerJoin());
        loadListener(new PlayerLeave());
        loadListener(new PlayerDamager());
        loadListener(new BlockBreak());
        loadListener(new PlayerHunger());
        loadListener(new PlayerItemMove());
        loadListener(new WorldListener());
        loadListener(new BlockPlace());
        loadListener(new NoFall());
    }

    public static void loadListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.instance);
    }

    public static YamlConfiguration getGame() {
        return game;
    }

    public static YamlConfiguration getData() {
        return data;
    }

    public static YamlConfiguration getMessage() {
        return message;
    }

    public static void loadCommands() {
        loadCommand(new JoinCommand(), "join");
        loadCommand(new MainCommand(), "LevelPVP");
    }

    public static void reloadConfigs() {
        game = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "game.yml"));
        data = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "data.yml"));
        message = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder(), "message.yml"));
        Main.instance.reloadConfig();
        GameUtils.isSetUp = Main.instance.getConfig().getBoolean("isSetUp");
    }

    public static void loadCommand(CommandExecutor commandExecutor, String command) {
        Main.instance.getCommand(command).setExecutor(commandExecutor);
    }
}
