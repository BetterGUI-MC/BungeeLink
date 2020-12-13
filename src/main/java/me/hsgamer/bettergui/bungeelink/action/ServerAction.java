package me.hsgamer.bettergui.bungeelink.action;

import me.hsgamer.bettergui.api.action.BaseAction;
import me.hsgamer.bettergui.bungeelink.Main;
import me.hsgamer.bettergui.lib.taskchain.TaskChain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ServerAction extends BaseAction {

    public ServerAction(String string) {
        super(string);
    }

    @Override
    public void addToTaskChain(UUID uuid, TaskChain<?> taskChain) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            return;
        }
        String replaced = getReplacedString(uuid);
        taskChain.sync(() -> Main.getUtils().connect(player, replaced));
    }
}
