package me.hsgamer.bettergui.bungeelink;

import me.hsgamer.bettergui.api.addon.GetPlugin;
import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.bettergui.bungeelink.action.AlertAction;
import me.hsgamer.bettergui.bungeelink.action.ServerAction;
import me.hsgamer.hscore.bukkit.channel.BungeeUtils;
import me.hsgamer.hscore.expansion.common.Expansion;

public final class Main implements Expansion, GetPlugin {
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
