package me.commandf1.LevelPVP.Commands;

import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import me.commandf1.LevelPVP.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class JoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Errors.CommandsRunByConsole")));
            return true;
        }
        if (!GameUtils.isSetUp) {
            commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Errors.GameNotSetUp")));
            return true;
        }
        Player player = (Player) commandSender;
        if (GameUtils.JoinedPlayers.contains(player)) {
            commandSender.sendMessage(StringUtils.toMinecraftColor(ServerUtils.getMessage().getString("Errors.AlreadyJoined")));
            return true;
        }
        Location location = new Location(Bukkit.getWorld(ServerUtils.getGame().getString("area.world")),
                ServerUtils.getGame().getDouble("area.x"),
                ServerUtils.getGame().getDouble("area.y"),
                ServerUtils.getGame().getDouble("area.z"));
        player.teleport(location);
        GameUtils.JoinedPlayers.add(player);

        player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));

        player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));

        player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));

        player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));

        player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        return true;
    }
}
