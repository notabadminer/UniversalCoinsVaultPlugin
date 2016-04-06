package universalcoinseconomy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import cpw.mods.fml.common.Loader;
import net.milkbowl.vault.economy.Economy;

public class UniversalCoinsMain extends JavaPlugin {

	public void onEnable() {
		if (Bukkit.getServer().getName().contains("Cauldron")) {
			if (Loader.isModLoaded("universalcoins")) {
				setupEconomy();
			} else {
				System.out.println("Universal Coins: Forge mod 1.7.10-1.6.21+ not detected. Initialization failed.");
			}
		} else {
			System.out.println("Universal Coins: Cauldron server not detected. Initialization failed.");
		}
	}

	private void setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") != null) {
			final ServicesManager sm = getServer().getServicesManager();
			sm.register(Economy.class, new UCEconomy(), this, ServicePriority.Highest);
			System.out.println("Universal Coins: Registered Vault interface.");
		} else {
			System.out.println("Universal Coins: Vault not detected. Initialization failed.");
		}
	}
}
