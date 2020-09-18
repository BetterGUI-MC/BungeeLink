package me.hsgamer.bettergui.bungeelink;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.hsgamer.bettergui.util.MessageUtils;
import me.hsgamer.bettergui.util.common.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeUtils {

  private static final String BUNGEE = "BungeeCord";
  private final JavaPlugin plugin;

  public BungeeUtils(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void register() {
    if (!Bukkit.getMessenger().isOutgoingChannelRegistered(plugin, BUNGEE)) {
      Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, BUNGEE);
    }
  }

  public void unregister() {
    if (Bukkit.getMessenger().isOutgoingChannelRegistered(plugin, BUNGEE)) {
      Bukkit.getMessenger().unregisterOutgoingPluginChannel(plugin, BUNGEE);
    }
  }

  public void connect(Player player, String server) {
    if (Validate.isNullOrEmpty(server)) {
      MessageUtils.sendMessage(player, "&cTarget server was an empty string");
      return;
    }

    ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();

    dataOutput.writeUTF("ConnectOther");
    dataOutput.writeUTF(player.getName());
    dataOutput.writeUTF(server);

    sendToBungee(player, dataOutput.toByteArray());
  }

  public void alert(Player player, String message) {
    ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();

    dataOutput.writeUTF("Message");
    dataOutput.writeUTF("ALL");
    dataOutput.writeUTF(MessageUtils.colorize(message));

    sendToBungee(player, dataOutput.toByteArray());
  }

  private void sendToBungee(Player player, byte[] byteArray) {
    player.sendPluginMessage(plugin, BUNGEE, byteArray);
  }
}
