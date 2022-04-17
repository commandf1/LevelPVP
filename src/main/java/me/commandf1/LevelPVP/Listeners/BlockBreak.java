package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Utils.GameUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBlocKBreak(BlockBreakEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        Player player = event.getPlayer();
        if (!(player.hasPermission("LevelPVP.block") && player.getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }
}
