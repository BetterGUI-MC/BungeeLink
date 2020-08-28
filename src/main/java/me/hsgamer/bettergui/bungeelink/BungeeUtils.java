package me.hsgamer.bettergui.bungeelink;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import me.hsgamer.bettergui.util.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtils {

  private final JavaPlugin plugin;

  public BungeeUtils(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void connect(Player player, String server) {
    try {
      if (server.length() == 0) {
        player.sendMessage("§cTarget server was \"\" (empty string) cannot connect to it.");
        return;
      }

      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(byteArray);

      out.writeUTF("Connect");
      out.writeUTF(server); // Target Server

      sendToBungee(player, byteArray);
    } catch (IOException ex) {
      player.sendMessage(ChatColor.RED
          + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
      plugin.getLogger().log(Level.WARNING, ex, () ->
          "Could not connect \"" + player.getName() + "\" to the server \"" + server + "\".");
    }
  }

  public void alert(Player player, String message) {
    try {
      ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(byteArray);

      out.writeUTF("Message");
      out.writeUTF("ALL");
      out.writeUTF(MessageUtils.colorize(message));

      sendToBungee(player, byteArray);
    } catch (IOException ex) {
      player.sendMessage(ChatColor.RED
          + "An unexpected exception has occurred. Please notify the server's staff about this. (They should look at the console).");
      plugin.getLogger().log(Level.WARNING,
          "Could not send alert", ex);
    }
  }

  private void sendToBungee(Player player, ByteArrayOutputStream byteArray) {
    player.sendPluginMessage(plugin, "BungeeCord", byteArray.toByteArray());
  }
}
