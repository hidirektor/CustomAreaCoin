package me.t3sl4.cac.market;

import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.util.MadenCoinAPI;
import me.t3sl4.cac.util.MessageUtil;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
                if(manager.marketmenusu.getConfig().getBoolean("Market.Items." + e.getSlot() + ".Buy.Enable")) {
                    e.setCancelled(true);
                    if(MadenCoinAPI.getKredi(uuid) < manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin")) {
                        if(MessageUtil.MENU_KAPATMA) {
                            p.closeInventory();
                        }
                        p.sendMessage(MessageUtil.BUY_ERROR.replaceAll("%madencoin%", String.valueOf(manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin"))));
                    } else {
                        if(!checkInventory(p)) {
                            p.sendMessage(MessageUtil.FULL_INVENTORY);
                        } else {
                            int verilecekSayi = manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.GivenItem.ItemCount");
                            for(int i=0; i<verilecekSayi; i++) {
                                String itemID = manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".ID");
                                int vermeMiktari = manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".Count");
                                String[] a = itemID.split(":");
                                int id = 0;
                                int data = 0;
                                if (a.length == 1) {
                                    id = Integer.parseInt(a[0]);
                                    data = 0;
                                }
                                if (a.length == 2) {
                                    id = Integer.parseInt(a[0]);
                                    data = Integer.parseInt(a[1]);
                                }
                                ItemStack verilecekItem = new ItemStack(Material.getMaterial(id), 1, (short)0, Byte.valueOf((byte)data));
                                ItemMeta m = verilecekItem.getItemMeta();
                                m.setDisplayName(CustomAreaCoin.chatcolor(manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".Name")));
                                m.setLore(CustomAreaCoin.colorizeList(manager.marketmenusu.getConfig().getStringList("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".Lore")));
                                verilecekItem.setItemMeta(m);
                                if(manager.marketmenusu.getConfig().getBoolean("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".EnchantSystem")) {
                                    List<String> enchantListesi = manager.marketmenusu.getConfig().getStringList("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".Enchants");
                                    for(String enchant : enchantListesi) {
                                        if(enchant != null) {
                                            String[] enchantVal = enchant.split(":");
                                            String enchantName = enchantVal[0];
                                            int enchantVals = Integer.parseInt(enchantVal[1]);
                                            verilecekItem.addEnchantment(Enchantment.getByName(enchantName), enchantVals);
                                        }
                                    }
                                }
                                if(manager.marketmenusu.getConfig().getBoolean("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".CommandSystem")) {
                                    List<String> komutListesi = manager.marketmenusu.getConfig().getStringList("Market.Items." + e.getSlot() + ".Buy.GivenItem." + i + ".Commands");
                                    for(String command : komutListesi) {
                                        if(command != null) {
                                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replaceAll("%player%", p.getDisplayName()));
                                        }
                                    }
                                }
                                for(int j=0; j<vermeMiktari; j++) {
                                    p.getInventory().addItem(verilecekItem);
                                }
                            }
                            MadenCoinAPI.removeKredi(uuid, manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin"));
                            if (manager.history.getConfig().get(uuid.toString() + ".kredi") == null) {
                                manager.history.getConfig().set(uuid + ".Displayname", p.getDisplayName());
                                manager.history.getConfig().set(uuid + ".MadenCoin", String.valueOf(manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin")));
                                manager.history.getConfig().set(uuid + ".Product.Name", manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Name"));
                                manager.history.getConfig().set(uuid + ".Product.Lore", manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Lore"));
                                manager.history.save();
                            }
                            if(verilecekSayi == 1) {
                                for(String satir : MessageUtil.BUY_FROM_GUI) {
                                    p.sendMessage(satir.replaceAll("%esya%", CustomAreaCoin.chatcolor(manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Buy.GivenItem." + 0 + ".Name"))).replaceAll("%madencoin%", String.valueOf(manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin"))).replaceAll("%kalancoin%", String.valueOf(MadenCoinAPI.getKredi(uuid))));
                                }
                            } else {
                                String alinanEsyalar = "";
                                for(int k=0; k<verilecekSayi; k++) {
                                    alinanEsyalar += manager.marketmenusu.getConfig().getString("Market.Items." + e.getSlot() + ".Buy.GivenItem." + k + ".Name");
                                    alinanEsyalar += MessageUtil.AYRAC;
                                }
                                for(String satir : MessageUtil.BUY_FROM_GUI) {
                                    p.sendMessage(satir.replaceAll("%esya%", CustomAreaCoin.chatcolor(alinanEsyalar)).replaceAll("%madencoin%", String.valueOf(manager.marketmenusu.getConfig().getInt("Market.Items." + e.getSlot() + ".Buy.Coin"))).replaceAll("%kalancoin%", String.valueOf(MadenCoinAPI.getKredi(uuid))));
                                }
                            }
                        }
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    public boolean checkInventory(Player player){
        Inventory inv = player.getInventory();
        Boolean check=false;
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                check = true;
                break;
            }
        }

        return check;
    }
}
