package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import me.kapehh.main.pluginmanager.vault.PluginVault;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanter extends JavaPlugin {
    private static CommandEnchanter instance;
    private static PluginConfig pluginConfig;
    private static CommandEnchanterManager commandEnchanterManager;
    private static Permission permission;
    private static Economy economy;

    public static CommandEnchanterManager getCommandEnchanterManager() {
        return commandEnchanterManager;
    }

    public static void setCommandEnchanterManager(CommandEnchanterManager commandEnchanterManager) {
        CommandEnchanter.commandEnchanterManager = commandEnchanterManager;
    }

    public static PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static Permission getPermission() {
        return permission;
    }

    public static CommandEnchanter getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        pluginConfig = new PluginConfig(this)
            .addEventClasses(new CommandEnchanterConfig())
            .setup()
            .loadData();
        economy = PluginVault.setupEconomy();
        permission = PluginVault.setupPermissions();
        getCommand("enchanterx").setExecutor(new CommandEnchanterExecuter());
    }

    @Override
    public void onDisable() {
        pluginConfig.saveData();
    }
}
