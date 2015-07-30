package net.dylanhansch.crosseconomy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import net.dylanhansch.crosseconomy.command.BalanceCommand;
import net.dylanhansch.crosseconomy.command.EconomyCommand;
import net.dylanhansch.crosseconomy.command.PayCommand;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CrossEconomy extends JavaPlugin implements Listener {
	
	private Economy econ = null;
	
	@Override
	public void onDisable() {
		this.getServer().getScheduler().cancelTasks(this);
		//new DatabaseSyncTask(this).run();
		econ = null;
	}
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("balance").setExecutor(new BalanceCommand(this));
		getCommand("pay").setExecutor(new PayCommand(this));
		getCommand("economy").setExecutor(new EconomyCommand(this));
		
		try {
			this.loadDatabase();
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			this.getLogger().log(Level.SEVERE, "Failed to load MySQL driver", e);
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		} catch (SQLException e) {
			this.getLogger().log(Level.SEVERE, "Database creation failed", e);
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}
		this.loadEconomy();
		getServer().getPluginManager().registerEvents(this, this);
		// sync the database every minute
		//this.getServer().getScheduler().runTaskTimerAsynchronously(this, new DatabaseSyncTask(this), 1_200, 1_200);
	}
	
	private void loadEconomy() {
		if (this.getServer().getPluginManager().isPluginEnabled("Vault")) {
			RegisteredServiceProvider<Economy> rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
			if (rsp != null) {
				this.econ = rsp.getProvider();
			}
		}
		if (this.econ == null) {
			this.getServer().getLogger().log(Level.WARNING, "Cannot find an economy plugin! Monitary sync disabled.");
		}
	}
	
	private void loadDatabase() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try (Connection db = this.getSQLDatabase();
			Statement stmt = db.createStatement()) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS players (id INTEGER PRIMARY KEY AUTO_INCREMENT," +
					"uuid VARCHAR(255) UNIQUE, balance VARCHAR(255))");
		}
	}
	
	protected Connection getSQLDatabase() throws SQLException {
		return DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",
						this.getConfig().getString("mysql.host"), this.getConfig().getInt("mysql.port"), this.getConfig().getString("mysql.database")),
				this.getConfig().getString("mysql.username"), this.getConfig().getString("mysql.password"));
	}
	
	public Economy getEconomy() {
		return econ;
	}
	
	public double getBalance(Player player) throws SQLException {
		try(
				Connection db = this.getSQLDatabase();
				PreparedStatement stmt = db.prepareStatement("SELECT balance FROM players WHERE uuid = ?")
		){
			stmt.setString(1, player.getUniqueId().toString());
			try (ResultSet rs = stmt.executeQuery()){
				if (rs.next()){
					return rs.getDouble("balance");
				}else{
					return 0.00;
				}
			}
		}
	}
	
	public void addBalance(Player player, double delta) throws SQLException {
		try (Connection conn = this.getSQLDatabase();
			 PreparedStatement stmt = conn.prepareStatement("UPDATE players SET balance = balance + ? WHERE uuid = ?")
		){
			stmt.setDouble(1, delta);
			stmt.setString(2, player.getUniqueId().toString());
			stmt.executeUpdate();
		}
	}
	
	public boolean removeBalance(Player player, double delta) throws SQLException {
		try (Connection conn = this.getSQLDatabase();
			 PreparedStatement stmt = conn.prepareStatement("UPDATE players SET balance = balance - ? WHERE uuid = ?")
		){
			stmt.setDouble(1, delta);
			stmt.setString(2, player.getUniqueId().toString());
			stmt.executeUpdate();
		}
		return false;
	}
	
	public void setBalance(Player player, double delta) throws SQLException {
		try (Connection conn = this.getSQLDatabase();
			 PreparedStatement stmt = conn.prepareStatement("UPDATE players SET balance = ? WHERE uuid = ?")
		){
			stmt.setDouble(1, delta);
			stmt.setString(2, player.getUniqueId().toString());
			stmt.executeUpdate();
		}
	}
	
	public void resetBalance(Player player) throws SQLException {
		try(Connection conn = getSQLDatabase()){
			try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM players WHERE uuid = ?")){
				stmt.setString(1, player.getUniqueId().toString());
				stmt.executeUpdate();
			}
		}
	}
}
