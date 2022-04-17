package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Utils.GameUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFall implements Listener {
    @EventHandler
    public void onEntityDanger(EntityDamageEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        if (event.getCause() != (EntityDamageEvent.DamageCause.FALL)) {
            return;
        }
        event.setCancelled(true);
    }
}
