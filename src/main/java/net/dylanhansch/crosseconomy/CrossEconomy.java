package net.dylanhansch.crosseconomy;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrossEconomy extends JavaPlugin implements Listener {
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		//getCommand("balance").setExecutor(new BalanceCommand(this));
	}
}
