package net.Blxd.main.SIDHandler;

import java.util.UUID;

import net.Blxd.main.Main;
import net.Blxd.main.utils.SIDPackets;
import net.Blxd.main.utils.Utils;

import org.bukkit.event.Listener;

public class SIDHandler implements Listener{
	
	@SuppressWarnings("unused")
	private Main plugin;
	public SIDHandler(Main listener) {
		this.plugin = listener;	
		SIDPackets.createSID(UUID.randomUUID().toString(), Utils.color("&8&l[&eSID&8&l]"), "world", 931.5, 9.0, 77.5, 0, 0);
	}

}
