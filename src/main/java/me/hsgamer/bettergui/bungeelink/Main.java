package me.hsgamer.bettergui.bungeelink;

import me.hsgamer.bettergui.builder.CommandBuilder;
import me.hsgamer.bettergui.bungeelink.command.AlertCommand;
import me.hsgamer.bettergui.bungeelink.command.ServerCommand;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  private static BungeeUtils utils;

  public static BungeeUtils getUtils() {
    return utils;
  }

  @Override
  public void onEnable() {
    utils = new BungeeUtils(getPlugin());
    utils.register();

    CommandBuilder.register(ServerCommand::new, "server:?");
    CommandBuilder.register(AlertCommand::new, "alert:");
  }

  @Override
  public void onDisable() {
    utils.unregister();
    utils = null;
  }
}
