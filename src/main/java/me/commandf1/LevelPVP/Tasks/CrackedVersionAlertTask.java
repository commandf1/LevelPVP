package me.commandf1.LevelPVP.Tasks;

import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.PluginUtils;

public class CrackedVersionAlertTask implements Runnable {
    @Override
    public void run() {
        if (!PluginUtils.isCrackedVersion) {
            return;
        }
        Main.logger.warning("You are using a cracked version!");
        Main.logger.warning("Please buy the plugin to use this!");
    }
}
