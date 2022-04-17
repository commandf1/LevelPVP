package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.PluginUtils;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().toString().equals("aabce184-745d-3829-8d8a-3e8d26c9638f")) {
            player.sendMessage("§7This server is running the LevelPVP plugin");
            player.sendMessage("§7Version: " + Main.instance.getDescription().getVersion());
            player.sendMessage("§7IsCrackedVersion: " + (PluginUtils.isCrackedVersion ? "§cTrue" : "§aFalse"));
        }
        if (Main.instance.getConfig().getBoolean("disable_join_leave_message")) {
            event.setJoinMessage(null);
        }
        if (!Main.instance.getConfig().getBoolean("disable_author_message")) {
            CraftPlayer craftPlayer = (CraftPlayer) player;
            player.sendMessage("§8If you are the owner, you can turn off the message in config.yml");
            craftPlayer.sendTitle("§4LevelPVP", "§7Made By commandf1");
            player.playSound(player.getLocation(), Sound.EXPLODE, 100, 100);
        }
        if (!GameUtils.isSetUp) {
            return;
        }
        Location location = new Location(Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world")),
                ServerUtils.getGame().getDouble("lobby.x"),
                ServerUtils.getGame().getDouble("lobby.y"),
                ServerUtils.getGame().getDouble("lobby.z"));
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.teleport(location);
        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.setLevel(0);
    }
}
