package me.hsgamer.bettergui.bungeelink;

import me.hsgamer.bettergui.builder.CommandBuilder;
import me.hsgamer.bettergui.bungeelink.command.AlertCommand;
import me.hsgamer.bettergui.bungeelink.command.ServerCommand;
import me.hsgamer.bettergui.object.addon.Addon;
import org.bukkit.Bukkit;

public final class Main extends Addon {

  private static final String BUNGEE = "BungeeCord";
  private static BungeeUtils utils;

  public static BungeeUtils getUtils() {
    return utils;
  }

  @Override
  public void onEnable() {
    utils = new BungeeUtils(getPlugin());
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), BUNGEE)) {
      Bukkit.getMessenger().registerOutgoingPluginChannel(getPlugin(), BUNGEE);
    }

    CommandBuilder.register("server:?", ServerCommand.class);
    CommandBuilder.register("alert:", AlertCommand.class);
  }

  @Override
  public void onDisable() {
    utils = null;
    if (Bukkit.getMessenger().isOutgoingChannelRegistered(getPlugin(), BUNGEE)) {
      Bukkit.getMessenger().unregisterOutgoingPluginChannel(getPlugin(), BUNGEE);
    }
  }
}
