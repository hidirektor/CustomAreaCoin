package me.t3sl4.cac.onay;

import java.io.Serializable;

import me.t3sl4.cac.util.MadenCoinAPI;
import me.t3sl4.cac.util.SettingsManager;
import me.t3sl4.cac.CustomAreaCoin;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class OnayTask extends BukkitRunnable implements Serializable {
    private static final long serialVersionUID = -789479476104765653L;
    SettingsManager manager = SettingsManager.getInstance();

    private String uuid;

    private String command;

    public OnayTask(String uuid, String command) {
        this.uuid = uuid;
        this.command = command;
        runTaskLater((Plugin) CustomAreaCoin.getPlugin(), (manager.config.getConfig().getInt("ONAY_ISTEME_SURESI") * 86400 * 20));
    }

    public void run() {
        MadenCoinAPI.getStringToOnay(this.uuid).remOnayTasks(this);
        cancel();
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getCommand() {
        return this.command;
    }
}
