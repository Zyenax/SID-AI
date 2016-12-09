package net.Blxd.main;

import net.Blxd.main.SIDHandler.SIDHandler;
import net.Blxd.main.utils.SIDPackets;
import net.Blxd.main.utils.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void onEnable(){
		Listeners();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(Utils.color("&8[&eSID-AI&8] &aSID has been enabled!"));
	}
	
	public void onDisable(){
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(Utils.color("&8[&eSID-AI&8] &cSID has been disabled!"));
	}
	
	public void Listeners(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Utils(this), this);
		pm.registerEvents(new SIDPackets(this), this);
		pm.registerEvents(new SIDHandler(this), this);
	}
	
}
