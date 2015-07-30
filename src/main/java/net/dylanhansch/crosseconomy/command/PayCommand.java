package net.dylanhansch.crosseconomy.command;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dylanhansch.crosseconomy.CrossEconomy;

public class PayCommand implements CommandExecutor {
	private final CrossEconomy plugin;

	public PayCommand(CrossEconomy plugin) {
		this.plugin = plugin;
	}
	
	// Usage: /pay <player> <value>
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		
		if(args.length <= 1){
			if(!sender.hasPermission("crosseconomy.pay")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "Error: Not enough arguments.");
				return false;
			}
		}
		
		@SuppressWarnings("deprecation")
		Player targetPlayer = plugin.getServer().getPlayer(args[0]);
		int delta = Integer.parseInt(args[1]);
		if(args.length == 2){
			if(!sender.hasPermission("crosseconomy.pay")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				try {
					plugin.addBalance(targetPlayer, delta);
					sender.sendMessage(ChatColor.GREEN + "You sent $" + ChatColor.RED + delta + ChatColor.GREEN 
							+ " to " + ChatColor.RED + targetPlayer);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		
		if(args.length >= 3){
			if(!sender.hasPermission("crosseconomy.pay")){
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
