package me.commandf1.LevelPVP.Tasks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.GameUtils;
import me.commandf1.LevelPVP.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public class ScoreBoardTask implements Runnable {
    @Override
    public void run() {
        if (!GameUtils.isSetUp) {
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            Objective objective = scoreboard.
                    registerNewObjective("LevelPVP", "dummy");
            objective.setDisplayName(StringUtils.toMinecraftColor(Main.instance.getConfig().getString("" +
                    "scoreboard.title")));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            List<String> list = StringUtils.toMinecraftColor(Main.instance.getConfig().getStringList("scoreboard.list"));
            for (int i = 0; i < list.size(); i++) {
                objective.getScore(PlaceholderAPI.setPlaceholders(player, list.get(i))).setScore(list.size() - i);
            }
            player.setScoreboard(scoreboard);
        }
    }
}
