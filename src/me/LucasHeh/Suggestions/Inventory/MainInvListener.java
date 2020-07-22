package me.LucasHeh.Suggestions.Inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.LucasHeh.Suggestions.Main;
import me.LucasHeh.Suggestions.Suggestion;
import me.LucasHeh.Suggestions.Utils;

public class MainInvListener implements Listener{
	
	Utils utils = Main.getInstance().getUtils();

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		System.out.println("Click");
		Player p = (Player) e.getWhoClicked();
		InventoryView inv = p.getOpenInventory();
		ItemStack item = e.getCurrentItem();
		if(inv.getTitle().equals(ChatColor.AQUA+"Suggestions")) {
			System.out.println("Click 2");
			if(item.getType() == Material.BOOK) {
				System.out.println("Click 3");
				e.setCancelled(true);
				Suggestion suggestion = utils.getSuggestion(item);
				utils.getSuggestionList().remove(suggestion);
				ItemStack background =  new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
				ItemMeta meta = background.getItemMeta();
				meta.setDisplayName(" ");
				item.setItemMeta(meta);
				inv.setItem(e.getSlot(), background);
			} else e.setCancelled(true);
		}
	}
	
}
