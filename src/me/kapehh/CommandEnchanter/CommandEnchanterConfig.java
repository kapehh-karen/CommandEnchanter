package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterConfig {

    @EventPluginConfig(EventType.LOAD)
    public void onConfigLoad() {
        CommandEnchanter.setCommandEnchanterManager(new CommandEnchanterManager());
    }
}
