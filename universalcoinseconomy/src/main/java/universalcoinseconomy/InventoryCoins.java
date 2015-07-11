package universalcoinseconomy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCoins {

	private final Player player;
	private final Inventory inventory;
	private String[] itemNames = { "UNIVERSALCOINS_ITEMITEMCOIN", "UNIVERSALCOINS_ITEMITEMSMALLCOINSTACK",
			"UNIVERSALCOINS_ITEMITEMLARGECOINSTACK", "UNIVERSALCOINS_ITEMITEMSMALLCOINBAG",
			"UNIVERSALCOINS_ITEMITEMLARGECOINBAG" };

	public InventoryCoins(Player player) {
		this.player = player;
		this.inventory = player.getInventory();
	}

	public int balance() {
		int value = 0;
		for (ItemStack stack : inventory) {
			value += stackValue(stack);
		}
		return value;
	}

	public double collectCoinPayment(double amount) {
		double coinsCollected = 0;
		for (int i = 0; i < inventory.getSize(); i++) {
			int value = stackValue(inventory.getItem(i));
			if (value > 0) {
				coinsCollected += value;
				inventory.clear(i);
			}
			if (coinsCollected >= amount) {
				// return change
				returnChange(coinsCollected - amount);
				return 0;
			}
		}
		return amount - coinsCollected;
	}

	public void returnChange(double change) {
		while (change > 0) {
			// use logarithm to find largest cointype for coins being sent
			int logVal = Math.min((int) (Math.log(change) / Math.log(9)), 4);
			int stackSize = Math.min((int) (change / Math.pow(9, logVal)), 64);
			// add a stack to the recipients inventory
			HashMap<Integer, ItemStack> test = inventory.addItem(new ItemStack(Material.getMaterial(itemNames[logVal]),
					stackSize));
			change -= stackSize * Math.pow(9, logVal);
			// TODO throw excess coins to world when player inventory is full
			if (test.size() > 0) {
				Iterator<Entry<Integer, ItemStack>> it = test.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<Integer, ItemStack> pair = (Map.Entry<Integer, ItemStack>) it.next();
					player.getWorld().dropItemNaturally(player.getLocation(), pair.getValue());
					it.remove(); // avoids a ConcurrentModificationException

				}
			}
		}
	}

	private int stackValue(ItemStack stack) {
		int[] coins = { 1, 9, 81, 729, 6561 };
		int inventoryValue = 0;
		for (int i = 0; i < coins.length; i++) {
			if (stack != null && stack.getType().name().matches(itemNames[i])) {
				inventoryValue += coins[i] * stack.getAmount();
			}
		}

		return inventoryValue;
	}

}
