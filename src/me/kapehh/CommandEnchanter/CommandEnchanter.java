package me.kapehh.CommandEnchanter;

import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanter extends JavaPlugin {
    private static PluginConfig pluginConfig = null;

    @EventPluginConfig(EventType.LOAD)
    public void onConfigLoad() {
        getLogger().info("CONFIG LOADED KOKOKOKO");
    }

    @Override
    public void onEnable() {
        pluginConfig = new PluginConfig(this);
        getLogger().info("CONFIG LOADED KOKOKOKO");
    }

    @Override
    public void onDisable() {

    }
}
