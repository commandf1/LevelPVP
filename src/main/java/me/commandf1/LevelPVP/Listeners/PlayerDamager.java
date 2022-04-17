package me.commandf1.LevelPVP.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import me.commandf1.LevelPVP.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class PlayerDamager implements Listener {
    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getEntity();
        if ((player.getHealth() - event.getDamage() <= 0)) {
            GameUtils.JoinedPlayers.remove(player);
            Location spawnPoint = new Location(Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world")),
                    ServerUtils.getGame().getDouble("lobby.x"),
                    ServerUtils.getGame().getDouble("lobby.y"),
                    ServerUtils.getGame().getDouble("lobby.z")
            );
            event.setDamage(0);
            player.getInventory().clear();
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                player.getInventory().setItem(i, new ItemStack(Material.AIR));
            }
            player.getInventory().setHelmet(new ItemStack(Material.AIR));
            player.getInventory().setChestplate(new ItemStack(Material.AIR));
            player.getInventory().setLeggings(new ItemStack(Material.AIR));
            player.getInventory().setBoots(new ItemStack(Material.AIR));
            player.teleport(spawnPoint);
            if (player.getKiller() != null) {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player,
                        StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.alert.byKiller")).
                                replaceAll("%player%", player.getName()).
                                replaceAll("%killer%", player.getKiller().getName())));
            } else {
                Bukkit.broadcastMessage(PlaceholderAPI.setPlaceholders(player,
                        StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.alert.byNull")).
                                replaceAll("%player%", player.getName())));
            }
            player.setMaxHealth(20);
            player.setHealth(player.getMaxHealth());
            ((CraftPlayer) player).sendTitle(StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.message.title")),
                    StringUtils.toMinecraftColor(Main.instance.getConfig().getString("death.message.subtitle")));
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1000, 1000);
            Player killer = player.getKiller();
            if (player.getKiller() != null) {
                if (killer.getLocation().getWorld() != Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world"))) {
                    GameUtils.update(killer);
                    killer.playSound(killer.getLocation(), Sound.ANVIL_USE, 1000, 1000);
                }
            }
            player.setLevel(0);
            player.closeInventory();
            if (player.getKiller() != null) {
                ServerUtils.getData().set("kills." + killer.getName(), ServerUtils.getData().getInt("kills." + killer.getName()) + 1);
            }
            ServerUtils.getData().set("deaths." + player.getName(), ServerUtils.getData().getInt("deaths." + player.getName()) + 1);
            try {
                ServerUtils.getData().save(new File(Main.instance.getDataFolder(), "data.yml"));
            } catch (IOException ignored) {
            }
        }
        if (player.getWorld() == Bukkit.getWorld(ServerUtils.getGame().getString("lobby.world"))) {
            event.setCancelled(true);
        }
    }
}
