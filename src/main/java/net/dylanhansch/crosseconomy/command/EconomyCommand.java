package net.dylanhansch.crosseconomy.command;

import java.sql.SQLException;

import net.dylanhansch.crosseconomy.CrossEconomy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {
	private final CrossEconomy plugin;

	public EconomyCommand(CrossEconomy plugin) {
		this.plugin = plugin;
	}
	
	// Usage: /eco <player> <add/remove/set/reset> <value>
	public boolean onCommand(CommandSender sender, Command cmd, String commandLable, String[] args){
		
		if(args.length <= 2){
			if(!sender.hasPermission("crosseconomy.eco")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				sender.sendMessage(ChatColor.RED + "Error: Not enough arguments.");
				return false;
			}
		}
		
		@SuppressWarnings("deprecation")
		Player targetPlayer = plugin.getServer().getPlayer(args[1]);
		double delta = Double.parseDouble(args[2]);
		if(args.length == 3){
			if(!sender.hasPermission("crosseconomy.eco")){
				sender.sendMessage(ChatColor.DARK_RED + "You don't have permission for that command.");
				return true;
			}else{
				if(args[1].equalsIgnoreCase("add")){
					try {
						plugin.addBalance(targetPlayer, delta);
						//sender.sendMessage(ChatColor.GREEN + "You added $" + ChatColor.RED + delta + ChatColor.GREEN 
						//		+ " to " + ChatColor.RED + targetPlayer + ChatColor.GREEN + "'s account.");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}else if(args[1].equalsIgnoreCase("remove")){
					try {
						plugin.removeBalance(targetPlayer, delta);
						sender.sendMessage(ChatColor.GREEN + "You removed $" + ChatColor.RED + delta + ChatColor.GREEN 
								+ " from " + ChatColor.RED + targetPlayer + ChatColor.GREEN + "'s balance.");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}else if(args[1].equalsIgnoreCase("set")){
					try {
						plugin.setBalance(targetPlayer, delta);
						sender.sendMessage(ChatColor.GREEN + "You set " + ChatColor.RED + targetPlayer + ChatColor.GREEN 
								+ "'s balance to " + ChatColor.RED + delta);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}else if(args[1].equalsIgnoreCase("reset")){
					try {
						plugin.resetBalance(targetPlayer);
						sender.sendMessage(ChatColor.GREEN + "You reset " + ChatColor.RED + targetPlayer + ChatColor.GREEN + "'s balance to ZERO.");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}else{
					return false;
				}
			}
		}
		
		if(args.length >= 4){
			if(!sender.hasPermission("crosseconomy.eco")){
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
