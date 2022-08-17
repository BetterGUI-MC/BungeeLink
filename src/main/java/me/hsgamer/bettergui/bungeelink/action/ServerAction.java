package me.hsgamer.bettergui.bungeelink.action;

import me.hsgamer.bettergui.BetterGUI;
import me.hsgamer.bettergui.api.action.BaseAction;
import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.bettergui.bungeelink.Main;
import me.hsgamer.hscore.task.BatchRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ServerAction extends BaseAction {
    public ServerAction(ActionBuilder.Input input) {
        super(input);
    }

    @Override
    public void accept(UUID uuid, BatchRunnable.Process process) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            process.next();
            return;
        }
        String replaced = getReplacedString(uuid);
        Bukkit.getScheduler().runTask(BetterGUI.getInstance(), () -> {
            Main.getUtils().connect(player, replaced);
            process.next();
        });
    }
}
