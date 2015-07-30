package net.dylanhansch.crosseconomy;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultConnector {
	
	private final CrossEconomy plugin;
	
	public VaultConnector(CrossEconomy plugin) {
		this.plugin = plugin;
	}
	
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	public EconomyResponse createBank(String arg0, String arg1) { 
		return null;
	}

	public boolean createPlayerAccount(Player player) throws SQLException {
		plugin.setBalance(player, 0);
		return true;
	}

	public boolean createPlayerAccount(Player player, String world) throws SQLException {
		return createPlayerAccount(player);
	}

	public String currencyNamePlural() {
		return "Dollars";
	}

	public String currencyNameSingular() {
		return "Dollar";
	}

	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	public EconomyResponse depositPlayer(Player player, double amnt) throws SQLException {
		plugin.addBalance(player, amnt);
		return new EconomyResponse(amnt, plugin.getBalance(player), ResponseType.SUCCESS, "");
	}

	public EconomyResponse depositPlayer(Player player, String world, double amnt) throws SQLException {
		return depositPlayer(player, amnt);
	}

	public String format(double amnt) {
		return String.valueOf(amnt);
	}

	public int fractionalDigits() {
		return 2;
	}

	public double getBalance(Player player) throws SQLException {
		return plugin.getBalance(player);
	}

	public double getBalance(Player player, String world) throws SQLException {
		return getBalance(player);
	}

	public List<String> getBanks() {
		return null;
	}

	public String getName() {
		return "CrossEconomy";
	}

	public boolean has(Player player, double amnt) throws SQLException {
		return plugin.getBalance(player) >= amnt;
	}

	public boolean has(Player player, String world, double amnt) throws SQLException {
		return has(player, amnt);
	}

	public boolean hasAccount(String arg0) {
		return false;
	}

	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	public boolean hasBankSupport() {
		return false;
	}

	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	public EconomyResponse withdrawPlayer(Player player, double amnt) throws SQLException {
		return new EconomyResponse(amnt, plugin.getBalance(player) - amnt, plugin.removeBalance(player, amnt) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	public EconomyResponse withdrawPlayer(Player player, String world, double amnt) throws SQLException {
		return withdrawPlayer(player, amnt);
	}

	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createPlayerAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getBalance(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getBalance(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean has(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasAccount(OfflinePlayer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}
}
