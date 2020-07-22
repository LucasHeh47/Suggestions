package me.LucasHeh.Suggestions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

	private List<Suggestion> suggestionList;
	private Main main = Main.getInstance();
	
	public Utils() {
		suggestionList = new ArrayList<Suggestion>();
	}

	public List<Suggestion> getSuggestionList() {
		return suggestionList;
	}
	
	public List<String> listTranslate(List<String> list) {
        List<String> converted = new ArrayList<String>();
        
        for(String string : list) {
            converted.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return converted;
    }
	
	public void itemToInventory(Material mat, String displayName, List<String> lore, Inventory inv, int slot) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName);
		if(lore != null)
			meta.setLore(listTranslate(lore));
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public Suggestion getSuggestion(ItemStack item) {
		String suggestionStr = ChatColor.stripColor(item.getItemMeta().getDisplayName());
		//Player p = Bukkit.getPlayer(item.getItemMeta().getLore().get(0).split("\\s+")[1]);
		for(Suggestion suggestion : suggestionList) {
			if(suggestion.getSuggestion().equals(suggestionStr))
				return suggestion;
		}
		return null;
	}
	
	public void saveToFile() {
		YamlConfiguration config = main.getDataConfig();
		int x = 1;
		for(Suggestion suggestion : suggestionList) {
			config.set("suggestions." + "suggestion" + x + ".suggestion", suggestion.getSuggestion());
			config.set("suggestions." + "suggestion" + x + ".player", suggestion.getPlayerWhoSuggestedName());
			x++;
		}
		try {
			config.save(main.getDataFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String string) {
		System.out.println("----------------------------------");
		System.out.println(" ");
		System.out.println(string);
		System.out.println(" ");
		System.out.println("----------------------------------");
	}
	
	public void loadFromFile() {
		log("LOADING SUGGESTIONS");
		YamlConfiguration config = main.getDataConfig();
		
		config.getConfigurationSection("suggestions").getKeys(false).forEach(key -> {
			suggestionList.add(new Suggestion(
					config.getString("suggestions." + key + ".suggestion"), 
					config.getString("suggestions." + key + ".player")));
		});
		log("LOADED SUGGESTIONS");
	}
	
}
