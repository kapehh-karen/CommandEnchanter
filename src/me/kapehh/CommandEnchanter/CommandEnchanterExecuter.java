package me.kapehh.CommandEnchanter;

import me.kapehh.CommandEnchanter.manager.CommandEnchanterManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Karen on 09.07.2014.
 */
public class CommandEnchanterExecuter implements CommandExecutor {

    @Override
    public synchronized boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "You must be a player!");
            return true;
        }

        if (!CommandEnchanter.getPermission().has(commandSender, CommandEnchanterManager.PERM)) {
            commandSender.sendMessage(ChatColor.RED + "You don't have permissions!");
            return true;
        }

        CommandEnchanter.getCommandEnchanterManager().enchant((Player)commandSender);

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
