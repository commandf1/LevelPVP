package me.commandf1.LevelPVP.Commands;

import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import me.commandf1.LevelPVP.Utils.StringUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Errors.CommandsRunByConsole")));
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("LevelPVP.command.Main")) {
            player.sendMessage("§aLevelPVP Made By commandf1");
            player.sendMessage("§aVersion: " + Main.instance.getDescription().getVersion());
            return true;
        }
        if (strings.length <= 0) {
            player.sendMessage(StringUtils.toMinecraftColor(StringUtils.ListToString(ServerUtils.getMessage().getStringList("CommandInfo.Help"))));
            return true;
        }
        String type = strings[0].toLowerCase();
        World world = player.getWorld();
        Location location = player.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        switch (type) {
            case "setarea":
                ServerUtils.getGame().set("area.world", world.getName());
                ServerUtils.getGame().set("area.x", x);
                ServerUtils.getGame().set("area.y", y);
                ServerUtils.getGame().set("area.z", z);
                try {
                    ServerUtils.getGame().save(new File(Main.instance.getDataFolder(), "game.yml"));
                } catch (IOException ignored) {
                }
                commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Message.SetupComplete")));
                break;
            case "setlobby":
                ServerUtils.getGame().set("lobby.world", world.getName());
                ServerUtils.getGame().set("lobby.x", x);
                ServerUtils.getGame().set("lobby.y", y);
                ServerUtils.getGame().set("lobby.z", z);
                try {
                    ServerUtils.getGame().save(new File(Main.instance.getDataFolder(), "game.yml"));
                } catch (IOException ignored) {
                }
                commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Message.SetupComplete")));
                break;
            case "reload":
                ServerUtils.reloadConfigs();
                commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Message.SetupComplete")));
                break;
        }
        return true;
    }
}
