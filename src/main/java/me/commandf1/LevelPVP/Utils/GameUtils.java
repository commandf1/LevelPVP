package me.commandf1.LevelPVP.Utils;

import me.commandf1.LevelPVP.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    public static List<Player> JoinedPlayers = new ArrayList<>();

    public static boolean isSetUp = Main.instance.getConfig().getBoolean("isSetUp");

    public static void update(Player player) {
        switch (player.getLevel()) {
            case 0:
                player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
                player.setLevel(player.getLevel() + 1);
                break;
            case 1:
                player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
                player.setLevel(player.getLevel() + 1);
                break;
            case 2:
                player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 3:
                player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 4:
                player.getInventory().setItem(0, new ItemStack(Material.GOLD_SWORD));
                player.setLevel(player.getLevel() + 1);
                break;
            case 5:
                player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                player.setLevel(player.getLevel() + 1);
                break;
            case 6:
                player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                player.setLevel(player.getLevel() + 1);
                break;
            case 7:
                player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 8:
                player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 9:
                player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));
                player.setLevel(player.getLevel() + 1);
                break;
            case 10:
                player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
                player.setLevel(player.getLevel() + 1);
                break;
            case 11:
                player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                player.setLevel(player.getLevel() + 1);
                break;
            case 12:
                player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 13:
                player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 14:
                player.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
                player.setLevel(player.getLevel() + 1);
                break;
            case 15:
                player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                player.setLevel(player.getLevel() + 1);
                break;
            case 16:
                player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                player.setLevel(player.getLevel() + 1);
                break;
            case 17:
                player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 18:
                player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                player.setLevel(player.getLevel() + 1);
                break;
            case 19:
                player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
                player.setLevel(player.getLevel() + 1);
                break;
            case 20:
                player.getInventory().setItem(1, new ItemStack(Material.BOW));
                player.setLevel(player.getLevel() + 1);
                break;
        }
        if (player.getLevel() == 50) {
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
            player.setLevel(player.getLevel() + 1);
            return;
        }
        if (player.getLevel() == 30) {
            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
            player.setLevel(player.getLevel() + 1);
            return;
        }
        if (player.getLevel() >= 21) {
            player.getInventory().addItem(new ItemStack(Material.ARROW));
            player.setLevel(player.getLevel() + 1);
        }
    }
}
