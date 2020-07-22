package me.LucasHeh.Suggestions;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.LucasHeh.Suggestions.Commands.Suggest;
import me.LucasHeh.Suggestions.Commands.Suggestions;
import me.LucasHeh.Suggestions.Inventory.MainInvListener;

public class Main extends JavaPlugin{
	
	private File dataFile = new File(getDataFolder(), "data.yml");
	private YamlConfiguration dataConfig = YamlConfiguration.loadConfiguration(dataFile);
	
	private static Main instance;
	private Utils utils;
	
	public void onEnable() {
		instance = this;
		utils = new Utils();
		new Suggest(this);
		new Suggestions(this);
		Bukkit.getPluginManager().registerEvents(new MainInvListener(), this);
		if(!dataFile.exists())
			saveResource("data.yml", false);
		if(dataConfig.contains("suggestions")) {
			utils.loadFromFile();
		}
	}
	
	public File getDataFile() {
		return dataFile;
	}

	public void onDisable() {
		if(!utils.getSuggestionList().isEmpty()) {
			System.out.println("Saving");
			utils.saveToFile();
		} else {
			System.out.println("Not saving");
		}
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public Utils getUtils() {
		return utils;
	}
	
	public YamlConfiguration getDataConfig() {
		return dataConfig;
	}

}
