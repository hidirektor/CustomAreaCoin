package me.t3sl4.cac.commands;

import me.t3sl4.cac.util.MadenCoinAPI;
import me.t3sl4.cac.util.MessageUtil;
import me.t3sl4.cac.util.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MadenCoinimCommand implements CommandExecutor {
    SettingsManager manager = SettingsManager.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("madencoinim")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Konsol bu komudu kullanamaz !");
                return true;
            }
            sender.sendMessage(MessageUtil.SHOW_CREDIT_SELF.replaceAll("%madencoin%", String.valueOf(MadenCoinAPI.getKredi(((Player)sender).getUniqueId().toString()))));
        }
        return true;
    }
}
