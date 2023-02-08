package me.t3sl4.cac.placeholder;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.util.MadenCoinAPI;
import org.bukkit.plugin.Plugin;

public class MVdWPlaceholder implements PlaceholderReplacer {
    public MVdWPlaceholder() {
        PlaceholderAPI.registerPlaceholder((Plugin) CustomAreaCoin.getPlugin(), "madencoin_miktar", this);
    }

    public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
        if (e.getPlayer() == null)
            return "Player needed!";
        return String.valueOf(MadenCoinAPI.getKredi(e.getPlayer().getUniqueId().toString()));
    }
}
