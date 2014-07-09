package me.kapehh.CommandEnchanter.manager;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterManager {
    public static final String PERM = "commandenchanter.use";

    private List<EnchantItem> enchantItems;

    public CommandEnchanterManager() {

    }

    public CommandEnchanterManager(List<EnchantItem> enchantItems) {
        this.enchantItems = enchantItems;
    }

    public List<EnchantItem> getEnchantItems() {
        return enchantItems;
    }

    public void setEnchantItems(List<EnchantItem> enchantItems) {
        this.enchantItems = enchantItems;
    }

    public boolean enchant(Player player) {
        return true;
    }
}
