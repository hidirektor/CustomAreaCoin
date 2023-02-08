package me.t3sl4.cac;

import me.t3sl4.cac.placeholder.MVdWPlaceholder;
import me.t3sl4.cac.placeholder.PAPIPlaceholder;
import me.t3sl4.cac.util.MessageUtil;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomAreaCoin extends JavaPlugin {
    SettingsManager manager = SettingsManager.getInstance();

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("   ");
        Bukkit.getConsoleSender().sendMessage("  ____   __   __  _   _   _____   _____   ____    _       _  _   ");
        Bukkit.getConsoleSender().sendMessage(" / ___|  \\ \\ / / | \\ | | |_   _| |___ /  / ___|  | |     | || |  ");
        Bukkit.getConsoleSender().sendMessage(" \\___ \\   \\ V /  |  \\| |   | |     |_ \\  \\___ \\  | |     | || |_ ");
        Bukkit.getConsoleSender().sendMessage("  ___) |   | |   | |\\  |   | |    ___) |  ___) | | |___  |__   _|");
        Bukkit.getConsoleSender().sendMessage(" |____/    |_|   |_| \\_|   |_|   |____/  |____/  |_____|    |_|  ");
        Bukkit.getConsoleSender().sendMessage("    ");
		Bukkit.getConsoleSender().sendMessage("T3SL4 Series: CustomAreaCoin");
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") || Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &aPlaceholder Destegi Aktif Edildi"));
            if(MessageUtil.PLACEHOLDER_SUPPORT && Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
                new MVdWPlaceholder();
                Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &cMVdWPlaceholder tespit edildi!"));
                Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &cPlaceholders: &e[ {madencoin_miktar} ]"));
            } else if(!MessageUtil.PLACEHOLDER_SUPPORT && Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new PAPIPlaceholder(this).register();
                Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &cPlaceholderAPI tespit edildi!"));
                Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &cPlaceholders: &e[ %madencoin_miktar% ]"));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&e[CAC] &cPlaceholder yuklu olmadigindan bazi ozellikler devre disi"));
        }
        this.manager.setup(this);
    }

    public void onDisable() {
        this.manager.stop();
    }

    public static String chatcolor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> colorizeList(List<String> str) {
        for(int x=0; x<str.size(); x++) {
            str.set(x, str.get(x).replace("&", "§"));
        }
        return str;
    }

    public static CustomAreaCoin getPlugin() {
        return (CustomAreaCoin)Bukkit.getPluginManager().getPlugin("CustomAreaCoin");
    }
}
