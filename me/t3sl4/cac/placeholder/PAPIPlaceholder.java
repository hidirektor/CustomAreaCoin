package me.t3sl4.cac.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.util.MadenCoinAPI;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PAPIPlaceholder extends PlaceholderExpansion {
    SettingsManager manager = SettingsManager.getInstance();
    private CustomAreaCoin p;

    public PAPIPlaceholder(Plugin p) {
        this.p = (CustomAreaCoin)p;
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null)
            return "";
        if (identifier.equals("miktar"))
            return String.valueOf(MadenCoinAPI.getKredi(p.getUniqueId().toString()));
        return null;
    }

    public String getAuthor() {
        return p.getDescription().getAuthors().toString();
    }

    public String getIdentifier() {
        return "madencoin";
    }

    public String getVersion() {
        return p.getDescription().getVersion();
    }
}
