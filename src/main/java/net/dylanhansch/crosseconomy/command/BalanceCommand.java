package net.dylanhansch.crosseconomy.command;

import java.sql.SQLException;

import net.dylanhansch.crosseconomy.CrossEconomy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {
	private final CrossEconomy plugin;

	public BalanceCommand(CrossEconomy plugin) {
		this.plugin = plugin;
	}
	
	// Usage: /balance (player)
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		
		if(args.length == 0){
			if(!sender.hasPermission("crosseconomy.balance")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "Error: Not enough arguments.");
				return false;
			}
		}
		
		Player player = (Player) sender;
		if(args.length == 1){
			if(!sender.hasPermission("crosseconomy.balance")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				try {
					sender.sendMessage(ChatColor.GREEN + "Balance: " + ChatColor.RED + plugin.getBalance(player));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		
		@SuppressWarnings("deprecation")
		Player targetPlayer = plugin.getServer().getPlayer(args[1]);
		if(args.length == 2){
			if(!sender.hasPermission("crosseconomy.balance.others")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				try {
					sender.sendMessage(ChatColor.GREEN + "Balance: " + ChatColor.RED + plugin.getBalance(targetPlayer));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		
		if(args.length >= 3){
			if(!sender.hasPermission("crosseconomy.balance")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "Error: Too many arguments.");
				return false;
			}
		}
		
		return false;
	}
}
