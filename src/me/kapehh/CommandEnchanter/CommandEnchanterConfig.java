package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import me.kapehh.CommandEnchanter.manager.EnchantItem;
import me.kapehh.CommandEnchanter.manager.EnchantSet;
import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterConfig {

    @EventPluginConfig(EventType.LOAD)
    public void onConfigLoad() {
        FileConfiguration cfg = CommandEnchanter.getPluginConfig().getConfig();
        List<EnchantItem> enchantItems = new ArrayList<EnchantItem>();
        HashMap<String, EnchantSet> enchantSets = new HashMap<String, EnchantSet>();
        Set<String> keysSets = cfg.getConfigurationSection("commandenchanter.sets").getKeys(false);
        for (String key : keysSets) {
            String cfgPath = String.format("commandenchanter.sets.%s", key);
            EnchantSet enchantSet = new EnchantSet();
            enchantSet.setName(key);
            enchantSet.setIds(cfg.getIntegerList(cfgPath));
            enchantSets.put(key, enchantSet);
        }
        Set<String> keysItems = cfg.getConfigurationSection("commandenchanter.enchants").getKeys(false);
        for (String key : keysItems) {
            String cfgPath = String.format("commandenchanter.enchants.%s.", key);
            List<EnchantSet> enchantSetList = new ArrayList<EnchantSet>();
            EnchantItem enchantItem = new EnchantItem();
            enchantItem.setName(key);
            enchantItem.setEid(cfg.getInt(cfgPath + ".eid"));
            enchantItem.setMaxlevel(cfg.getInt(cfgPath + ".maxlevel"));
            for (String set : cfg.getStringList(cfgPath + ".allowsets")) {
                EnchantSet enchantSet = enchantSets.get(set);
                if (enchantSet != null) {
                    enchantSetList.add(enchantSet);
                }
            }
            enchantItem.setAllowsets(enchantSetList);
            enchantItems.add(enchantItem);
        }
        CommandEnchanter.setCommandEnchanterManager(new CommandEnchanterManager(enchantItems));
    }
}
