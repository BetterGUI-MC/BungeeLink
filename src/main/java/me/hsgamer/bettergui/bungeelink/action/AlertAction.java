package me.hsgamer.bettergui.bungeelink.action;

import me.hsgamer.bettergui.BetterGUI;
import me.hsgamer.bettergui.api.action.BaseAction;
import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.hscore.bukkit.channel.BungeeUtils;
import me.hsgamer.hscore.task.element.TaskProcess;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class AlertAction extends BaseAction {
    private final Plugin plugin;

    public AlertAction(Plugin plugin, ActionBuilder.Input input) {
        super(input);
        this.plugin = plugin;
    }

    @Override
    public void accept(UUID uuid, TaskProcess process) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            process.next();
            return;
        }
        String replaced = getReplacedString(uuid);
        Bukkit.getScheduler().runTask(BetterGUI.getInstance(), () -> {
            BungeeUtils.sendMessage(plugin, player, "ALL", replaced);
            process.next();
        });
    }
}
