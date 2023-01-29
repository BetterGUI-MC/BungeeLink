package me.hsgamer.bettergui.bungeelink;

import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.bettergui.bungeelink.action.AlertAction;
import me.hsgamer.bettergui.bungeelink.action.ServerAction;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.bukkit.channel.BungeeUtils;

public final class Main extends PluginAddon {
    @Override
    public void onEnable() {
        BungeeUtils.register(getPlugin());
        ActionBuilder.INSTANCE.register(input -> new ServerAction(getPlugin(), input), "server");
        ActionBuilder.INSTANCE.register(input -> new AlertAction(getPlugin(), input), "alert");
    }

    @Override
    public void onDisable() {
        BungeeUtils.unregister(getPlugin());
    }
}
