package me.kapehh.CommandEnchanter.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterManager {
    public static final String PERM = "commandenchanter.use";

    private List<EnchantItem> enchantItems;
    private String evalCost;

    public CommandEnchanterManager() {

    }

    public CommandEnchanterManager(List<EnchantItem> enchantItems, String evalCost) {
        this.enchantItems = enchantItems;
        this.evalCost = evalCost;
    }

    public List<EnchantItem> getEnchantItems() {
        return enchantItems;
    }

    public void setEnchantItems(List<EnchantItem> enchantItems) {
        this.enchantItems = enchantItems;
    }

    public String getEvalCost() {
        return evalCost;
    }

    public void setEvalCost(String evalCost) {
        this.evalCost = evalCost;
    }

    public void check(Player player, String enchantName, int enchantLvl) throws CommandEnchanterException {
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getType().equals(Material.AIR) || itemStack.getAmount() != 1) {
            throw new CommandEnchanterException("Invalid item!");
        }

        boolean finded = false;
        for (EnchantItem enchantItem : enchantItems) {
            if (enchantItem.getName().equalsIgnoreCase(enchantName)) {
                boolean isCorrect = false;
                for (EnchantSet enchantSet : enchantItem.getAllowsets()) {
                    if (enchantSet.getIds().contains(itemStack.getTypeId())) {
                        isCorrect = true;
                        break;
                    }
                }
                if (!isCorrect) {
                    throw new CommandEnchanterException("Not support item: " + itemStack.getType().toString());
                }

                if (enchantItem.getMaxlevel() < enchantLvl) {
                    throw new CommandEnchanterException("Not support level: " + enchantLvl);
                }

                finded = true;
                break;
            }
        }

        if (!finded) {
            throw new CommandEnchanterException("Enchant not found: " + enchantName);
        }

        return;
    }

    public boolean enchant(Player player, String enchantName, int enchantLvl) throws CommandEnchanterException {
        ItemStack itemStack = player.getItemInHand();
        Enchantment enchantment = null;

        for (EnchantItem enchantItem : enchantItems) {
            if (enchantItem.getName().equalsIgnoreCase(enchantName)) {
                enchantment = Enchantment.getById(enchantItem.getEid());
            }
        }

        if (enchantment == null) {
            return false;
        }

        if (itemStack.getEnchantments().containsKey(enchantment)) {
            itemStack.removeEnchantment(enchantment);
        }
        itemStack.addUnsafeEnchantment(enchantment, enchantLvl);

        return true;
    }
}
