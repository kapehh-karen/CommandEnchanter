package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import me.kapehh.main.pluginmanager.config.PluginConfig;
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

    /*public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("lvl", 9);
        Object result = engine.eval("if (lvl > 10) (lvl - 10) * 300 + 1500; else if (lvl > 5) (lvl - 5) * 200 + 500; else lvl * 100;");
        System.out.println(result);
    }*/

    @Override
    public void onEnable() {
        instance = this;
        economy = PluginVault.setupEconomy();
        permission = PluginVault.setupPermissions();
        if (economy == null || permission == null) {
            getLogger().info("Vault not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        pluginConfig = new PluginConfig(this); // Initialize
        pluginConfig.addEventClasses(new CommandEnchanterConfig())
                    .setup()
                    .loadData();
        getCommand("enchanterx").setExecutor(new CommandEnchanterExecuter());
    }

    @Override
    public void onDisable() {
        pluginConfig.saveData();
    }
}
