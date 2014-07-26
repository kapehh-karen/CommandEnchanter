package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterException;
import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterExecuter implements CommandExecutor {

    @Override
    public synchronized boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length < 1) {
            return false;
        }

        if (args[0].equalsIgnoreCase("reload") && CommandEnchanter.getPermission().has(commandSender, CommandEnchanterManager.PERM_RELOAD)) {
            CommandEnchanter.getPluginConfig().loadData();
            commandSender.sendMessage(ChatColor.GREEN + "Reloaded!");
            return true;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player!");
            return true;
        }

        if (!CommandEnchanter.getPermission().has(commandSender, CommandEnchanterManager.PERM_USE)) {
            commandSender.sendMessage(ChatColor.RED + "You don't have permissions!");
            return true;
        }

        if (args.length < 2) {
            return false;
        }

        CommandEnchanterManager commandEnchanterManager = CommandEnchanter.getCommandEnchanterManager();
        Economy economy = CommandEnchanter.getEconomy();

        Player player = (Player) commandSender;
        String enchantName = args[0];
        int enchantLvl = Integer.parseInt(args[1]);
        double playerMoney = economy.getBalance(player.getName());

        try {
            // Проверяем все условия перед зачаркой
            commandEnchanterManager.check(player, enchantName, enchantLvl);

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
            engine.put("lvl", enchantLvl);
            double moneyCost = (Double) engine.eval(commandEnchanterManager.getEvalCost());

            if (moneyCost <= playerMoney) {
                EconomyResponse r = economy.withdrawPlayer(player.getName(), moneyCost);
                if(r.transactionSuccess()) {
                    if (commandEnchanterManager.enchant(player, enchantName, enchantLvl)) {
                        player.sendMessage(ChatColor.GREEN + "Enchant success!");
                        CommandEnchanter.getPluginLogger().getLog().info(
                            String.format(
                                "Player '%s' enchant '%s' on level '%d'. Withdraw '%f'",
                                player.getName(),
                                enchantName,
                                enchantLvl,
                                moneyCost
                            )
                        );
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + r.errorMessage);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You need " + economy.format(moneyCost));
            }
        } catch (CommandEnchanterException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
        } catch (ScriptException e) {
            e.printStackTrace();
        }


        /*public static void main(String[] args) throws ScriptException {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            engine.put("lvl", 9);
            Object result = engine.eval("if (lvl > 10) (lvl - 10) * 300 + 1500; else if (lvl > 5) (lvl - 5) * 200 + 500; else lvl * 100;");
            System.out.println(result);
        }*/

        /*
        ChatColor.RED + "An error occured: " + error;

        EconomyResponse r = CFVault.getEconomy().depositPlayer(p.getName(), k.getGiveMoney());
        if(r.transactionSuccess()) {
            k.setEnabled(false);
            LocationEx.broadcastMessage(String.format(k.getMessageWorld(), p.getName()));
            CFLogger.PrintInfo("[FortBoyard] " + p.getName() + " winner!");
        } else {
            sender.sendMessage(PermEx.getOccuredError(r.errorMessage));
        }
         */

        return true;
    }
}
