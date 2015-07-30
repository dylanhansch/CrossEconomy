package net.dylanhansch.crosseconomy.command;

import net.dylanhansch.crosseconomy.CrossEconomy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BalanceCommand implements CommandExecutor {
	private final CrossEconomy plugin;

	public BalanceCommand(CrossEconomy plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		return false;
	}
}
