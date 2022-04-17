package me.commandf1.LevelPVP.Tasks;

import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import me.commandf1.LevelPVP.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class DeathTask implements Runnable {
    @Override
    public void run() {
        if (!GameUtils.isSetUp) {
            return;
        }
        Location spawnPoint = new Location(Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world")),
                ServerUtils.getGame().getDouble("lobby.x"),
                ServerUtils.getGame().getDouble("lobby.y"),
                ServerUtils.getGame().getDouble("lobby.z")
        );
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getY() <= 0) {
                player.getInventory().setHelmet(new ItemStack(Material.AIR));
                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                player.getInventory().setBoots(new ItemStack(Material.AIR));
                player.teleport(spawnPoint);
                player.setMaxHealth(20);
                player.setHealth(player.getMaxHealth());
                ((CraftPlayer) player).sendTitle(StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.message.title")),
                        StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.message.subtitle")));
                player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1000, 1000);
                ServerUtils.getData().set("deaths." + player.getName(), ServerUtils.getData().getInt("deaths." + player.getName()) + 1);
                try {
                    ServerUtils.getData().save(new File(Main.instance.getDataFolder(), "data.yml"));
                } catch (IOException ignored) {
                }
            }
        }
    }
}
