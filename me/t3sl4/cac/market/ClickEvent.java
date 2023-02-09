package me.t3sl4.cac.market;

import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickEvent implements Listener {
    SettingsManager manager = SettingsManager.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMarketClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        String uuid = p.getUniqueId().toString();
        Inventory inv = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if(inv != null) {
            if(inv.getTitle().equals(CustomAreaCoin.chatcolor(manager.marketmenusu.getConfig().getString("Market.Name")))) {
                e.setCancelled(true);
            }
        }
    }
}
