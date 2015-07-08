package universalcoinseconomy;

import java.text.DecimalFormat;
import java.util.List;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import universalcoins.util.UniversalAccounts;

public class UCEconomy implements Economy {

	public static Economy econ = null;

	@Override
	public String getName() {
		return "UniversalCoins";
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean createPlayerAccount(String arg0) {
		Player player = Bukkit.getPlayer(arg0);
		if (player == null)
			return false;
		return createPlayerAccount(player);
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		UniversalAccounts.getInstance().addPlayerAccount(arg0.getUniqueId().toString());
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		Player player = Bukkit.getPlayer(arg0);
		if (player == null)
			return false;
		return createPlayerAccount(player);
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		return createPlayerAccount(arg0);
	}

	@Override
	public String currencyNamePlural() {
		return "coins";
	}

	@Override
	public String currencyNameSingular() {
		return "coin";
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		Player player = Bukkit.getPlayer(arg0);
		if (player == null)
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to get offline player");
		return depositPlayer(player, arg1);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		String accountNumber = UniversalAccounts.getInstance().getOrCreatePlayerAccount(arg0.getUniqueId().toString());
		if (UniversalAccounts.getInstance().creditAccount(accountNumber, (int) arg1)) {
			return new EconomyResponse(arg1, 0, ResponseType.SUCCESS, null);
		}
		return new EconomyResponse(arg1, 0, ResponseType.FAILURE, "Deposit failed.");
	}

	@SuppressWarnings("deprecation")
	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to get offline player");
		depositPlayer(player.getUniqueId().toString(), (int) arg2);
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return depositPlayer(arg0, arg2);
	}

	@Override
	public String format(double arg0) {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		String s = format.format(arg0);
		if (s.endsWith(".")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public double getBalance(String arg0) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return 0;
		return getBalance(player);
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		String accountNumber = UniversalAccounts.getInstance().getPlayerAccount(arg0.getUniqueId().toString());
		return UniversalAccounts.getInstance().getAccountBalance(accountNumber);
	}

	@SuppressWarnings("deprecation")
	@Override
	public double getBalance(String arg0, String arg1) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return 0;
		return getBalance(player);
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		return getBalance(arg0);
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean has(String arg0, double arg1) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return false;
		return hasAccount(player);
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		String accountNumber = UniversalAccounts.getInstance().getPlayerAccount(arg0.getUniqueId().toString());
		int playerBalance = UniversalAccounts.getInstance().getAccountBalance(accountNumber);
		if (playerBalance >= arg1) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return false;
		return has(player, arg2);
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		return has(arg0, arg2);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasAccount(String arg0) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return false;
		return hasAccount(player);
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		String accountNumber = UniversalAccounts.getInstance().getPlayerAccount(arg0.getUniqueId().toString());
		if (accountNumber != null && !accountNumber.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean hasAccount(String arg0, String arg1) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return false;
		return hasAccount(player);
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		return hasAccount(arg0);
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to get offline player");
		return withdrawPlayer(player, arg1);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		String accountNumber = UniversalAccounts.getInstance().getPlayerAccount(arg0.getUniqueId().toString());
		if (UniversalAccounts.getInstance().debitAccount(accountNumber, (int) arg1)) {
			return new EconomyResponse(arg1, 0, ResponseType.SUCCESS, null);
		}
		return new EconomyResponse(arg1, 0, ResponseType.FAILURE, "Withdrawal failed");
	}

	@SuppressWarnings("deprecation")
	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		OfflinePlayer player = Bukkit.getOfflinePlayer(arg0);
		if (player == null)
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to get offline player");
		return withdrawPlayer(player, arg2);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return withdrawPlayer(arg0, arg2);
	}
}
