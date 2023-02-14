package me.t3sl4.cac.listeners;

import me.t3sl4.cac.CustomAreaCoin;
import me.t3sl4.cac.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaltaEvent implements Listener {

    @EventHandler
    public void baltaDrop(PlayerDropItemEvent e) {
        Player esyaAtan = e.getPlayer();
        Item atilanEsya = e.getItemDrop();
        ItemMeta atilanMeta = atilanEsya.getItemStack().getItemMeta();

        if(atilanMeta != null && atilanMeta.hasDisplayName()) {
            if(atilanMeta.getDisplayName().equals(MessageUtil.AXENAME)) {
                e.setCancelled(true);
                esyaAtan.sendMessage(MessageUtil.BALTA_HATA);
            }
        }
    }

    @EventHandler
    public void baltaBreak(BlockBreakEvent e) {
        Player blokKiran = e.getPlayer();
        ItemStack kirdigiItem = blokKiran.getItemInHand();
        ItemMeta kirdigiMeta = kirdigiItem.getItemMeta();

        if(kirdigiMeta != null && kirdigiMeta.hasDisplayName()) {
            if(kirdigiMeta.getDisplayName().equals(MessageUtil.AXENAME)) {
                e.setCancelled(true);
                blokKiran.sendMessage(MessageUtil.BALTA_KIRMA_HATA);
            }
        }
    }

    @EventHandler
    public void rightClickBalta(PlayerInteractEvent e) {
        Player tiklayan = e.getPlayer();
        ItemStack tiklanilanItem = e.getItem();
        Map<Integer, String> koordinatlarX = new HashMap<>();
        Map<Integer, String> koordinatlarY = new HashMap<>();
        Map<Integer, String> koordinatlarZ = new HashMap<>();

        ItemMeta tiklanilanItemMeta = tiklanilanItem.getItemMeta();
        if(tiklanilanItemMeta != null && tiklanilanItemMeta.hasDisplayName()) {
            if(tiklanilanItemMeta.getDisplayName().equals(MessageUtil.AXENAME)) {
                if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    e.setCancelled(true);
                    tiklayan.sendMessage(MessageUtil.HAVA_KOORDINAT);
                }
                if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Block tiklanilanBlok = e.getClickedBlock();
                    if(koordinatlarX.get(0) == null & koordinatlarX.get(1) == null &&
                    koordinatlarY.get(0) == null & koordinatlarY.get(1) == null &&
                    koordinatlarZ.get(0) == null & koordinatlarZ.get(1) == null) {
                        koordinatlarX.put(0, String.valueOf(tiklanilanBlok.getX()));
                        koordinatlarY.put(0, String.valueOf(tiklanilanBlok.getY()));
                        koordinatlarZ.put(0, String.valueOf(tiklanilanBlok.getZ()));
                        for(String expLine : MessageUtil.EVENT_EXP) {
                            tiklayan.sendMessage(CustomAreaCoin.chatcolor(expLine.replaceAll("%ilkkoord%", MessageUtil.CONST_VAR_COORD.replaceAll("%x_coord%", String.valueOf(koordinatlarX.get(0))).replaceAll("%y_coord%", String.valueOf(koordinatlarY.get(0))).replaceAll("%z_coord%", String.valueOf(0))).replaceAll("%ikincikoord%", MessageUtil.CONST_VAR_COMMA)));
                        }
                    } else {
                        koordinatlarX.put(1, String.valueOf(tiklanilanBlok.getX()));
                        koordinatlarY.put(1, String.valueOf(tiklanilanBlok.getY()));
                        koordinatlarZ.put(1, String.valueOf(tiklanilanBlok.getZ()));
                        for(String expLine : MessageUtil.EVENT_EXP) {
                            tiklayan.sendMessage(CustomAreaCoin.chatcolor(expLine.replaceAll("%ilkkoord%", MessageUtil.CONST_VAR_COORD.replaceAll("%x_coord%", String.valueOf(koordinatlarX.get(0))).replaceAll("%y_coord%", String.valueOf(koordinatlarY.get(0))).replaceAll("%z_coord%", String.valueOf(0))).replaceAll("%ikincikoord%", MessageUtil.CONST_VAR_COORD.replaceAll("%x_coord%", String.valueOf(koordinatlarX.get(1))).replaceAll("%y_coord%", String.valueOf(koordinatlarY.get(1))).replaceAll("%z_coord%", String.valueOf(1)))));
                        }
                        tiklayan.setItemInHand(new ItemStack(Material.AIR));
                    }

                    /*if((koordinatlarX.get(0) != null && koordinatlarX.get(1) == null) &&
                            (koordinatlarY.get(0) != null && koordinatlarY.get(1) == null) &&
                            (koordinatlarZ.get(0) != null && koordinatlarZ.get(1) == null)) {
                        koordinatlarX.put(1, String.valueOf(tiklanilanBlok.getX()));
                        koordinatlarY.put(1, String.valueOf(tiklanilanBlok.getY()));
                        koordinatlarZ.put(1, String.valueOf(tiklanilanBlok.getZ()));
                        for(String expLine : MessageUtil.EVENT_EXP) {
                            tiklayan.sendMessage(CustomAreaCoin.chatcolor(expLine.replaceAll("%ilkkoord%", MessageUtil.CONST_VAR_COORD.replaceAll("%x_coord%", String.valueOf(koordinatlarX.get(0))).replaceAll("%y_coord%", String.valueOf(koordinatlarY.get(0))).replaceAll("%z_coord%", String.valueOf(0))).replaceAll("%ikincikoord%", MessageUtil.CONST_VAR_COORD.replaceAll("%x_coord%", String.valueOf(koordinatlarX.get(1))).replaceAll("%y_coord%", String.valueOf(koordinatlarY.get(1))).replaceAll("%z_coord%", String.valueOf(1)))));
                        }
                        tiklayan.setItemInHand(new ItemStack(Material.AIR));
                    }*/
                }
            }
        }
    }
}
