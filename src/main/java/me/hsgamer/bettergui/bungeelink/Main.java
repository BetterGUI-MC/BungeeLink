package me.hsgamer.bettergui.bungeelink;

import me.hsgamer.bettergui.builder.ActionBuilder;
import me.hsgamer.bettergui.bungeelink.action.AlertAction;
import me.hsgamer.bettergui.bungeelink.action.ServerAction;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;

public final class Main extends PluginAddon {

    private static BungeeUtils utils;

    public static BungeeUtils getUtils() {
        return utils;
    }

    @Override
    public void onEnable() {
        utils = new BungeeUtils(getPlugin());
        utils.register();

        ActionBuilder.INSTANCE.register(ServerAction::new, "server");
        ActionBuilder.INSTANCE.register(AlertAction::new, "alert");
    }

    @Override
    public void onDisable() {
        utils.unregister();
        utils = null;
    }
}
