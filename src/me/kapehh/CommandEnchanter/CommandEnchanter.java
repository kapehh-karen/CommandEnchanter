package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import me.kapehh.main.pluginmanager.checker.PluginChecker;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import me.kapehh.main.pluginmanager.logger.PluginLogger;
import me.kapehh.main.pluginmanager.logger.PluginLoggerFormatter;
import me.kapehh.main.pluginmanager.vault.PluginVault;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanter extends JavaPlugin {
    private static CommandEnchanter instance;
    private static PluginConfig pluginConfig;
    private static PluginLogger pluginLogger;
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

    public static PluginLogger getPluginLogger() {
        return pluginLogger;
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;

        PluginChecker pluginChecker = new PluginChecker(this);
        if (!pluginChecker.check("Vault")) {
            return;
        }

        economy = PluginVault.setupEconomy();
        permission = PluginVault.setupPermissions();
        if (economy == null) {
            getLogger().info("Economy plugin not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        pluginConfig = new PluginConfig(this); // Initialize
        pluginConfig.addEventClasses(new CommandEnchanterConfig())
                    .setup()
                    .loadData();

        pluginLogger = new PluginLogger(this, "enchant");
        pluginLogger.setup();

        getCommand("enchanterx").setExecutor(new CommandEnchanterExecuter());
    }

    @Override
    public void onDisable() {
        // PluginManager not found
        if (instance == null) {
            return;
        }

        if (pluginConfig != null) {
            pluginConfig.saveData();
        }

        if (pluginLogger != null) {
            pluginLogger.shutDown();
        }
    }
}
