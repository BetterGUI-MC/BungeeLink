package me.hsgamer.bettergui.bungeelink.command;

import me.hsgamer.bettergui.bungeelink.Main;
import me.hsgamer.bettergui.lib.taskchain.TaskChain;
import me.hsgamer.bettergui.object.Command;
import org.bukkit.entity.Player;

public class AlertCommand extends Command {

  public AlertCommand(String string) {
    super(string);
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    taskChain.sync(() -> Main.getUtils().alert(player, getParsedCommand(player)));
  }
}
