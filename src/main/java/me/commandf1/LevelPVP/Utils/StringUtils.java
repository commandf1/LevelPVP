package me.commandf1.LevelPVP.Utils;

import org.bukkit.ChatColor;

import java.util.List;

public class StringUtils {
    public static String toMinecraftColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> toMinecraftColor(List<String> text) {
        text.replaceAll(StringUtils::toMinecraftColor);
        return text;
    }

    public static String ListToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }
}
