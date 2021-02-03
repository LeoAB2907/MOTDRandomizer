package xyz.leonardoarias.plugins.motdRandomizer;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.leonardoarias.plugins.motdRandomizer.utilities.TextFormatter;
import xyz.leonardoarias.plugins.motdRandomizer.utilities.PingListener;

public class Main extends JavaPlugin {

	public final ConsoleCommandSender console = getServer().getConsoleSender();

	public final String prefix = "&7[&9MOTD&6Randomizer&7]";
	
	private List<String> motdlist = this.getConfig().getStringList("MOTD-List");
	public String[] motd = motdlist.toArray(new String[0]);
	
	private List<String> iconsList = this.getConfig().getStringList("Custom-Icon.List");
	public String[] icons = iconsList.toArray(new String[0]);

	@Override
	public void onEnable() {
		console.sendMessage(TextFormatter.Color(prefix + " &ePluging has been enabled!"));
		Bukkit.getPluginManager().registerEvents(new PingListener(this), this);
		this.getConfig().options().copyDefaults();
		saveDefaultConfig();
	}

	@Override
	public void onDisable() {
		console.sendMessage(TextFormatter.Color(prefix + " &ePluging has been disabled!"));
	}
}
