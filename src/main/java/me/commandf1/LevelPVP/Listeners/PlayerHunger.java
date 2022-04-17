package me.commandf1.LevelPVP.Listeners;

import me.commandf1.LevelPVP.Utils.GameUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerHunger implements Listener {
    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        if (!GameUtils.isSetUp) {
            return;
        }
        event.setFoodLevel(20);
    }
}
