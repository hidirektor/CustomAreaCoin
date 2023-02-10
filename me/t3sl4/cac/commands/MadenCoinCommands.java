package me.t3sl4.cac.commands;

import java.util.*;

import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.market.Market;
import me.t3sl4.cac.onay.OnayMenuItems;
import me.t3sl4.cac.util.MadenCoinAPI;
import me.t3sl4.cac.util.MessageUtil;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MadenCoinCommands implements CommandExecutor, Listener {
    SettingsManager manager = SettingsManager.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("madencoin")) {
            if (args.length == 0) {
                sender.sendMessage(MessageUtil.INFO_LINE_1);
                sender.sendMessage(MessageUtil.INFO_LINE_2);
                sender.sendMessage(MessageUtil.INFO_LINE_3);
                sender.sendMessage(MessageUtil.INFO_LINE_4);
                sender.sendMessage(MessageUtil.INFO_LINE_5);
                if (sender.isOp()) {
                    sender.sendMessage(MessageUtil.INFO_LINE_6);
                    sender.sendMessage(MessageUtil.INFO_LINE_7);
                    sender.sendMessage(MessageUtil.INFO_LINE_8);
                    sender.sendMessage(MessageUtil.INFO_LINE_9);
                    sender.sendMessage(MessageUtil.INFO_LINE_10);
                }
                return true;
            }
            if (args.length == 1 && !args[0].equalsIgnoreCase("reload") &&
                    !args[0].equalsIgnoreCase("yeni") &&
                    !args[0].equalsIgnoreCase("+") &&
                    !args[0].equalsIgnoreCase("-") &&
                    !args[0].equalsIgnoreCase("gonder") &&
                    !args[0].equalsIgnoreCase("giveall") &&
                    !args[0].equalsIgnoreCase("purge") &&
                    !args[0].equalsIgnoreCase("market")) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(MessageUtil.PLAYER_NOT_FOUND.replaceAll("%player%", args[0]));
                    return true;
                }
                sender.sendMessage(MessageUtil.SHOW_CREDIT
                        .replaceAll("%player%", target.getName())
                        .replaceAll("%madencoin%", String.valueOf(MadenCoinAPI.getKredi(target.getUniqueId().toString()))));
            }
            if (args[0].equalsIgnoreCase("market")) {
                Player komutGonderen = null;
                if(sender instanceof Player) {
                    komutGonderen = (Player) sender;
                    if(MessageUtil.MARKET) {
                        if(MessageUtil.WORLD) {
                            if(MessageUtil.WORLDLIST.contains(komutGonderen.getWorld().getName())) {
                                if(manager.marketmenusu.getConfig().getInt("Market.Size") % 9 == 0) {
                                    Inventory inv = Bukkit.createInventory(null, this.manager.marketmenusu.getConfig().getInt("Market.Size"), CustomAreaCoin.chatcolor(this.manager.marketmenusu.getConfig().getString("Market.Name")));
                                    for (Market marketItems : MessageUtil.MARKETITEMS) {
                                        if (marketItems.getItemStack() != null) {
                                            inv.setItem(marketItems.getSlot(), marketItems.getItemStack());
                                        }
                                    }
                                    komutGonderen.openInventory(inv);
                                } else {
                                    komutGonderen.sendMessage(MessageUtil.MARKET_MENU_ERROR);
                                }
                            } else {
                                komutGonderen.sendMessage(MessageUtil.WORLD_ERROR.replaceAll("%dunya%", komutGonderen.getWorld().getName()));
                            }
                        } else {
                            Inventory inv = Bukkit.createInventory(null, this.manager.marketmenusu.getConfig().getInt("Market.Size"), CustomAreaCoin.chatcolor(this.manager.marketmenusu.getConfig().getString("Market.Name")));
                            for (Market marketItems : MessageUtil.MARKETITEMS) {
                                if (marketItems.getItemStack() != null) {
                                    inv.setItem(marketItems.getSlot(), marketItems.getItemStack());
                                }
                            }
                            komutGonderen.openInventory(inv);
                        }
                    } else {
                        komutGonderen.sendMessage(MessageUtil.MARKET_KAPALI);
                    }
                } else {
                    sender.sendMessage(MessageUtil.CONSOLE_ERROR);
                }
            }
            if (args[0].equalsIgnoreCase("gonder")) {
                int islemMiktari;
                Player komutGonderen = null;
                if(sender instanceof Player) {
                    komutGonderen = (Player) sender;
                } else {
                    sender.sendMessage(MessageUtil.CONSOLE_ERROR);
                    return true;
                }
                if(args.length >= 2 && args.length <= 3) {
                    Player p = Bukkit.getPlayer(args[1]);
                    if (p == null) {
                        sender.sendMessage(MessageUtil.PLAYER_NOT_FOUND.replaceAll("%player%", args[1]));
                        return true;
                    }
                    if(args.length == 2) {
                        islemMiktari = 1;
                    } else {
                        try {
                            islemMiktari = Integer.parseInt(args[2]);
                        } catch (Exception e) {
                            sender.sendMessage(MessageUtil.MUST_BE_NUMBER);
                            return true;
                        }
                    }
                    if(MadenCoinAPI.getKredi(komutGonderen.getUniqueId().toString()) < islemMiktari) {
                        sender.sendMessage(MessageUtil.NOT_ENOUGH_CREDIT.replaceAll("%madencoin%", String.valueOf(islemMiktari)));
                    } else {
                        MadenCoinAPI.removeKredi(komutGonderen.getUniqueId().toString(), islemMiktari);
                        MadenCoinAPI.addKredi(p.getUniqueId().toString(), islemMiktari);
                        p.sendMessage(MessageUtil.CREDI_ADDED_YOUR
                                .replaceAll("%madencoin%", "" + islemMiktari)
                                .replaceAll("%toplam_madencoin%", "" + MadenCoinAPI.getKredi(p.getUniqueId().toString())));
                        for(String satir : MessageUtil.SUM_OF_TRANSACT) {
                            komutGonderen.sendMessage(satir.replaceAll("%player%", p.getDisplayName()).replaceAll("%madencoin%", String.valueOf(islemMiktari)).replaceAll("%kalancoin%", String.valueOf(MadenCoinAPI.getKredi(komutGonderen.getUniqueId().toString()))));
                        }
                    }
                } else {
                    komutGonderen.sendMessage(MessageUtil.GONDER);
                }
            }
            if (args[0].equalsIgnoreCase("giveall")) {
                int islemMiktari;
                Player komutGonderen = null;
                if(!(sender instanceof Player) || sender.isOp()) {
                    if(args.length >= 1 && args.length <= 2) {
                        if(args.length == 2) {
                            islemMiktari = 1;
                        } else {
                            try {
                                islemMiktari = Integer.parseInt(args[2]);
                            } catch (Exception e) {
                                sender.sendMessage(MessageUtil.MUST_BE_NUMBER);
                                return true;
                            }
                        }
                        for(Player p : Bukkit.getOnlinePlayers()) {
                            MadenCoinAPI.addKredi(p.getUniqueId().toString(), islemMiktari);
                            p.sendMessage(MessageUtil.CREDI_ADDED_YOUR
                                    .replaceAll("%madencoin%", "" + islemMiktari)
                                    .replaceAll("%toplam_madencoin%", "" + MadenCoinAPI.getKredi(p.getUniqueId().toString())));
                        }
                        sender.sendMessage(MessageUtil.GIVEN.replaceAll("%madencoin%", String.valueOf(islemMiktari)));
                    } else {
                        komutGonderen.sendMessage(MessageUtil.GIVEALL);
                    }
                } else {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                }
            }
            if (args[0].equalsIgnoreCase("purge")) {
                if(!(sender instanceof Player) || sender.isOp()) {
                    if(args.length == 1) {
                        MadenCoinAPI.purgeKredi();
                        sender.sendMessage(MessageUtil.PURGE_SUCCESS);
                    } else {
                        sender.sendMessage(MessageUtil.PURGE);
                    }
                } else {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                }
            }
            if (args[0].equalsIgnoreCase("+")) {
                int ekle;
                if (!sender.isOp()) {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                    return false;
                }
                Player p = Bukkit.getPlayer(args[1]);
                if (p == null) {
                    sender.sendMessage(MessageUtil.PLAYER_NOT_FOUND.replaceAll("%player%", args[1]));
                    return true;
                }
                try {
                    ekle = Integer.parseInt(args[2]);
                } catch (Exception e) {
                    sender.sendMessage(MessageUtil.MUST_BE_NUMBER);
                    return true;
                }
                MadenCoinAPI.addKredi(p.getUniqueId().toString(), ekle);
                p.sendMessage(MessageUtil.CREDI_ADDED_YOUR
                        .replaceAll("%madencoin%", "" + ekle)
                        .replaceAll("%toplam_madencoin%", "" + MadenCoinAPI.getKredi(p.getUniqueId().toString())));
                if (sender instanceof Player &&
                        !Objects.equals(p.getUniqueId().toString(), ((Player)sender).getUniqueId().toString()))
                    sender.sendMessage(MessageUtil.CREDI_ADDED_OTHER.replaceAll("%player%", p.getName()).replaceAll("%madencoin%", "" + ekle));
            }
            if (args[0].equalsIgnoreCase("-")) {
                int eksilt;
                if (!sender.isOp()) {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                    return false;
                }
                Player p = Bukkit.getPlayer(args[1]);
                if (p == null) {
                    sender.sendMessage(MessageUtil.PLAYER_NOT_FOUND.replaceAll("%player%", args[1]));
                    return true;
                }
                try {
                    eksilt = Integer.parseInt(args[2]);
                } catch (Exception e) {
                    sender.sendMessage(MessageUtil.MUST_BE_NUMBER);
                    return true;
                }
                MadenCoinAPI.removeKredi(p.getUniqueId().toString(), eksilt);
                p.sendMessage(MessageUtil.CREDI_TAKEN_YOUR.replaceAll("%madencoin%", "" + eksilt).replaceAll("%toplam_madencoin%", "" + MadenCoinAPI.getKredi(p.getUniqueId().toString())).replace("%madencoin%", "" + eksilt));
                sender.sendMessage(MessageUtil.CREDI_TAKEN_OTHER.replaceAll("%player%", p.getName()).replaceAll("%madencoin%", "" + eksilt));
            }
            if (args[0].equalsIgnoreCase("yeni")) {
                if (!sender.isOp()) {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                    return false;
                }
                if (args.length < 4) {
                    sender.sendMessage(MessageUtil.CMD_NEW);
                    return true;
                }
                if(!args[1].startsWith("'") || !args[1].endsWith("'")) {
                    sender.sendMessage(MessageUtil.CMD_NEW);
                    return true;
                }
                if(!args[3].startsWith("'") || !args[3].endsWith("'")) {
                    sender.sendMessage(MessageUtil.CMD_NEW);
                    return true;
                }
                ArrayList<String> arguments = new ArrayList<>();
                arguments.addAll(Arrays.asList(args));
                String first = "";
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                    sb.append(args[i] + " ");
                String command = "/" + label + " " + sb.toString();
                String real = ArgumentDecomposition.getRealCommand(command);
                String shown = ArgumentDecomposition.getShownCommnad(command);
                int price = ArgumentDecomposition.getPrice(command).intValue();
                if (real == null || real.equalsIgnoreCase("") || shown == null || shown
                        .equalsIgnoreCase("") || price < 0 || real.equalsIgnoreCase(shown)) {
                    sender.sendMessage(MessageUtil.CMD_NEW_ERROR);
                    return true;
                }
                ArrayList<String> reals = new ArrayList<>(Arrays.asList(new String[] { real }));
                sender.sendMessage(MessageUtil.COMMAND_ADDED);
                this.manager.commands.getConfig().set("Komutlar." + shown + ".Kredi", Integer.valueOf(price));
                this.manager.commands.save();
                this.manager.commands.getConfig().set("Komutlar." + shown + ".Komut", reals);
                this.manager.commands.save();
                this.manager.commands.getConfig().set("Komutlar." + shown + ".Onay", Boolean.valueOf(false));
                this.manager.commands.save();
                this.manager.commands.getConfig().set("Komutlar." + shown + ".TekSeferlik", Boolean.valueOf(false));
                this.manager.commands.save();
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.isOp()) {
                    sender.sendMessage(MessageUtil.NO_PERM_MESSAGE);
                    return false;
                }
                this.manager.config.load();
                MessageUtil.loadMessages();
                this.manager.commands.load();
                this.manager.data.load();
                this.manager.onaymenusu.load();
                sender.sendMessage(MessageUtil.CONFIG_RELOADED);
                return true;
            }
        }
        return true;
    }
}
