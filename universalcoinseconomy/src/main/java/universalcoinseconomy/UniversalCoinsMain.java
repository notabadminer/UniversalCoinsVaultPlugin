package universalcoinseconomy;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UniversalCoinsMain extends JavaPlugin {

	public void onEnable() {
		if (getServer().getPluginManager().getPlugin("Vault") != null) {
			final ServicesManager sm = getServer().getServicesManager();
			sm.register(Economy.class, new UCEconomy(), this, ServicePriority.Highest);
			System.out.println("Universal Coins: Registered Vault interface.");
		} else {
			System.out.println("Universal Coins: Vault not found. Initialization failed.");
		}
	}
}
