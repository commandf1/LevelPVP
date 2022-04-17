package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Utils.GameUtils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerItemMove implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission("LevelPVP.item"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        if (!(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission("LevelPVP.item"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(InventoryClickEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        if (!(event.getWhoClicked().getGameMode() == GameMode.CREATIVE && event.getWhoClicked().hasPermission("LevelPVP.item"))) {
            event.setCancelled(true);
        }
    }
}
