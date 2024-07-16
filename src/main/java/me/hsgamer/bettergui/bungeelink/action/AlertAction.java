package me.hsgamer.bettergui.bungeelink.action;

import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.bettergui.util.SchedulerUtil;
import me.hsgamer.hscore.action.common.Action;
import me.hsgamer.hscore.bukkit.channel.BungeeUtils;
import me.hsgamer.hscore.common.StringReplacer;
import me.hsgamer.hscore.task.element.TaskProcess;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class AlertAction implements Action {
    private final Plugin plugin;
    private final String value;

    public AlertAction(Plugin plugin, ActionBuilder.Input input) {
        this.plugin = plugin;
        this.value = input.getValue();
    }

    @Override
    public void apply(UUID uuid, TaskProcess process, StringReplacer stringReplacer) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            process.next();
            return;
        }
        String replaced = stringReplacer.replaceOrOriginal(value, uuid);
        SchedulerUtil.entity(player)
                .run(() -> {
                    try {
                        BungeeUtils.sendMessage(plugin, player, "ALL", replaced);
                    } finally {
                        process.next();
                    }
                }, process::next);
    }
}
