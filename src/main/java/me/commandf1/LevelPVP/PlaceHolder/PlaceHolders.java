package me.commandf1.LevelPVP.PlaceHolder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.commandf1.LevelPVP.Main;
import me.commandf1.LevelPVP.Utils.ServerUtils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceHolders extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "commandf1";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "LevelPVP";
    }

    @Override
    public @NotNull String getVersion() {
        return Main.instance.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (identifier.equals("deaths")) {
            return String.valueOf(ServerUtils.getData().getInt("deaths." + player.getName()));
        }

        if (identifier.equals("kills")) {
            return String.valueOf(ServerUtils.getData().getInt("kills." + player.getName()));
        }

        if (identifier.equals("version")) {
            return getVersion();
        }

        if (identifier.equals("author")) {
            return getAuthor();
        }

        return null;
    }
}
