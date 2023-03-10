package me.t3sl4.cac.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.market.Market;
import me.t3sl4.cac.onay.OnayMenuItems;
import org.bukkit.Bukkit;

public class MessageUtil {
    public static String PREFIX;
    public static String CONFIG_RELOADED;
    public static String NO_PERM_MESSAGE;
    public static String PLAYER_NOT_FOUND;
    public static String MUST_BE_NUMBER;
    public static String CMD_NEW_ERROR;
    public static String CONSOLE_ERROR;
    public static String BUY_ERROR;
    public static String FULL_INVENTORY;
    public static String MARKET_KAPALI;
    public static String WORLD_ERROR;
    public static String MARKET_MENU_ERROR;
    public static String BALTA_HATA;
    public static String BALTA_KIRMA_HATA;
    public static String HAVA_KOORDINAT;
    public static String SHOW_CREDIT;
    public static String SHOW_CREDIT_SELF;
    public static String NOT_ENOUGH_CREDIT;
    public static String COMMAND_ADDED;
    public static String CREDI_ADDED_OTHER;
    public static String CREDI_ADDED_YOUR;
    public static String CREDI_TAKEN_OTHER;
    public static String CREDI_TAKEN_YOUR;
    public static List<String> SUM_OF_TRANSACT;
    public static List<String> BUY_FROM_GUI;
    public static String GIVEN;
    public static String PURGE_SUCCESS;
    public static String INFO_LINE_1;
    public static String INFO_LINE_2;
    public static String INFO_LINE_3;
    public static String INFO_LINE_4;
    public static String INFO_LINE_5;
    public static String INFO_LINE_6;
    public static String INFO_LINE_7;
    public static String INFO_LINE_8;
    public static String INFO_LINE_9;
    public static String INFO_LINE_10;
    public static String INFO_LINE_11;
    public static String TEKSEFERLIK_KOMUT;
    public static int ONAY_ISTEME_SURESI;
    public static String ONAY_MENU_TITLE;
    public static boolean PLACEHOLDER_SUPPORT;
    public static boolean MARKET;
    public static boolean MENU_KAPATMA;
    public static String AYRAC;
    public static Boolean WORLD;
    public static List<String> WORLDLIST;
    public static String CMD_NEW;
    public static String GONDER;
    public static String GIVEALL;
    public static String PURGE;
    public static String EVENT;
    public static String AXENAME;
    public static List<String> AXELORE;
    public static List<String> AXEENCHANT;
    public static boolean AXEENCH;
    public static boolean AXELOR;
    public static List<String> EVENT_EXP;
    public static String CONST_VAR_COORD;
    public static String CONST_VAR_COMMA;

    public static ArrayList<OnayMenuItems> ITEMS = new ArrayList<>();

    public static ArrayList<Market> MARKETITEMS = new ArrayList<>();

    static SettingsManager manager = SettingsManager.getInstance();

    public static void loadMessages() {
        PREFIX = CustomAreaCoin.chatcolor(manager.config.getConfig().getString("PREFIX"));
        ONAY_ISTEME_SURESI = manager.config.getConfig().getInt("ONAY_ISTEME_SURESI");
        ONAY_MENU_TITLE = CustomAreaCoin.chatcolor(manager.onaymenusu.getConfig().getString("menuismi"));
        CONFIG_RELOADED = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CONFIG_RELOADED"));
        NO_PERM_MESSAGE = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("NO_PERM_MESSAGE"));
        PLAYER_NOT_FOUND = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("PLAYER_NOT_FOUND"));
        TEKSEFERLIK_KOMUT = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("TEKSEFERLIK_KOMUT"));
        MUST_BE_NUMBER = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("MUST_BE_NUMBER"));
        CMD_NEW_ERROR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CMD_NEW_ERROR"));
        CONSOLE_ERROR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CONSOLE_ERROR"));
        BUY_ERROR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("BUY_ERROR"));
        FULL_INVENTORY = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("FULL_INVENTORY"));
        MARKET_KAPALI = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("MARKET_KAPALI"));
        WORLD_ERROR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("WORLD_ERROR"));
        MARKET_MENU_ERROR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("MARKET_MENU_ERROR"));
        BALTA_HATA = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("BALTA_HATA"));
        BALTA_KIRMA_HATA = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("BALTA_KIRMA_HATA"));
        HAVA_KOORDINAT = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("HAVA_KOORDINAT"));
        CREDI_ADDED_OTHER = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CREDI_ADDED_OTHER"));
        SHOW_CREDIT = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("SHOW_CREDIT"));
        SHOW_CREDIT_SELF = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("SHOW_CREDIT_SELF"));
        NOT_ENOUGH_CREDIT = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("NOT_ENOUGH_CREDIT"));
        COMMAND_ADDED = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("COMMAND_ADDED"));
        CREDI_ADDED_YOUR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CREDI_ADDED_YOUR"));
        CREDI_TAKEN_OTHER = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CREDI_TAKEN_OTHER"));
        CREDI_TAKEN_YOUR = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CREDI_TAKEN_YOUR"));
        SUM_OF_TRANSACT = CustomAreaCoin.colorizeList(manager.config.getConfig().getStringList("SUM_OF_TRANSACT"));
        GIVEN = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("GIVEN"));
        PURGE_SUCCESS = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("PURGE_SUCCESS"));
        BUY_FROM_GUI = CustomAreaCoin.colorizeList(manager.config.getConfig().getStringList("BUY_FROM_GUI"));
        PLACEHOLDER_SUPPORT = manager.config.getConfig().getBoolean("PLACEHOLDER_SUPPORT");
        MARKET = manager.config.getConfig().getBoolean("MARKET");
        MENU_KAPATMA = manager.config.getConfig().getBoolean("MENU_KAPATMA");
        AYRAC = manager.config.getConfig().getString("AYRAC");
        WORLD = manager.config.getConfig().getBoolean("WORLDS.SYSTEM");
        WORLDLIST = manager.config.getConfig().getStringList("WORLDS.LIST");
        INFO_LINE_1 = CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_1"));
        INFO_LINE_2 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_2"));
        INFO_LINE_3 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_3"));
        INFO_LINE_4 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_4"));
        INFO_LINE_5 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_5"));
        INFO_LINE_6 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_6"));
        INFO_LINE_7 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_7"));
        INFO_LINE_8 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_8"));
        INFO_LINE_9 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_9"));
        INFO_LINE_10 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_10"));
        INFO_LINE_11 = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("INFO_LINE_11"));
        CMD_NEW = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CMD_NEW"));
        GONDER = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("GONDER"));
        GIVEALL = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("GIVEALL"));
        PURGE = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("PURGE"));
        EVENT = PREFIX + CustomAreaCoin.chatcolor(manager.config.getConfig().getString("EVENT"));
        AXENAME = CustomAreaCoin.chatcolor(manager.config.getConfig().getString("AXE.Name"));
        AXELORE = CustomAreaCoin.colorizeList(manager.config.getConfig().getStringList("AXE.Lore"));
        AXEENCHANT = manager.config.getConfig().getStringList("AXE.Enchant");
        AXELOR = manager.config.getConfig().getBoolean("AXE.Settings.Lore");
        AXEENCH = manager.config.getConfig().getBoolean("AXE.Settings.Enchant");
        EVENT_EXP = CustomAreaCoin.colorizeList(manager.config.getConfig().getStringList("EVENT_EXP"));
        CONST_VAR_COORD = CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CONST_VAR_COORD"));
        CONST_VAR_COMMA = CustomAreaCoin.chatcolor(manager.config.getConfig().getString("CONST_VAR_COMMA"));

        ArrayList<OnayMenuItems> test = new ArrayList<>();
        ArrayList<Market> test2 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            test.add(new OnayMenuItems());
        }
        for (String s : manager.onaymenusu.getConfigurationSection("").getKeys(false)) {
            if (!Objects.equals(s, "menuismi")) {
                int j = 298;
                try {
                    j = Integer.parseInt(s);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&4Onay Menu Hata!"));
                    return;
                }
                if (manager.onaymenusu.getConfig().get(j + ".item") != null &&
                        manager.onaymenusu.getConfig().get(j + ".tiklayinca") != null &&
                        manager.onaymenusu.getConfig().get(j + ".lore") != null &&
                        manager.onaymenusu.getConfig().get(j + ".isim") != null) {
                    String item = manager.onaymenusu.getConfig().getString(j + ".item");
                    String tiklayinca = manager.onaymenusu.getConfig().getString(j + ".tiklayinca");
                    List<String> lore = CustomAreaCoin.colorizeList(manager.onaymenusu.getConfig().getStringList(j + ".lore"));
                    String isim = manager.onaymenusu.getConfig().getString(j + ".isim");
                    String[] a = item.split(":");
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
                    OnayMenuItems omi = test.get(j);
                    omi.setData(data);
                    omi.setId(id);
                    if (!lore.isEmpty())
                        if (lore.size() == 1) {
                            if (!Objects.equals(lore.get(0), ""))
                                omi.setLore(lore);
                        } else {
                            omi.setLore(lore);
                        }
                    omi.setName(isim);
                    omi.setSlot(j);
                    omi.setOlay(tiklayinca);
                    ITEMS.add(omi);
                }
            }
        }

        for (int i = 0; i < manager.marketmenusu.getConfig().getInt("Market.Size"); i++) {
            test2.add(new Market());
        }
        for (String s : manager.marketmenusu.getConfigurationSection("Market.Items").getKeys(false)) {
            //TODO
            if (!Objects.equals(s, "Market.Name")) {
                int j = 298;
                try {
                    j = Integer.parseInt(s);
                } catch (Exception e) {
                    Bukkit.getConsoleSender().sendMessage(CustomAreaCoin.chatcolor("&4Market Menu Hata!"));
                    return;
                }
                if (manager.marketmenusu.getConfig().get("Market.Items." + j + ".ID") != null &&
                        manager.marketmenusu.getConfig().get("Market.Items." + j + ".Name") != null &&
                        manager.marketmenusu.getConfig().get("Market.Items." + j + ".Lore") != null) {
                    String item = manager.marketmenusu.getConfig().getString("Market.Items." + j + ".ID");
                    List<String> lore = CustomAreaCoin.colorizeList(manager.marketmenusu.getConfig().getStringList("Market.Items." + j + ".Lore"));
                    String isim = manager.marketmenusu.getConfig().getString("Market.Items." + j + ".Name");
                    String[] a = item.split(":");
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
                    Market omi = test2.get(j);
                    omi.setData(data);
                    omi.setId(id);
                    if (!lore.isEmpty())
                        if (lore.size() == 1) {
                            if (!Objects.equals(lore.get(0), ""))
                                omi.setLore(lore);
                        } else {
                            omi.setLore(lore);
                        }
                    omi.setName(isim);
                    omi.setSlot(j);
                    //omi.setOlay(tiklayinca);
                    MARKETITEMS.add(omi);
                }
            }
        }
    }
}
