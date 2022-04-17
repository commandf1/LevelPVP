package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.GameUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        Player player = event.getPlayer();
        if (Main.instance.getConfig().getBoolean("disable_join_leave_message")) {
            event.setQuitMessage(null);
        }
        GameUtils.JoinedPlayers.remove(player);
    }
}
