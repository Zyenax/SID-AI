package net.Blxd.main.utils;

import java.util.HashMap;
import java.util.UUID;

import net.Blxd.main.Main;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.MinecraftServer;
import net.minecraft.server.v1_11_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_11_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import net.minecraft.server.v1_11_R1.PlayerInteractManager;
import net.minecraft.server.v1_11_R1.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_11_R1.CraftServer;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mojang.authlib.GameProfile;

public class SIDPackets implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;
	public SIDPackets(Main listener) {
		SIDPackets.plugin = listener;
	}
	
	public static EntityPlayer SID;
	static String SIDMadeMessage = "&8[&eSID-AI&8] &cSID has already been made!";
	public static HashMap<Integer, EntityPlayer> g = new HashMap<Integer, EntityPlayer>();
	public static void createSID(String UserID, String name, String worldname, Double X, Double Y, Double Z, Integer Yaw, Integer Pitch){
		if(!g.containsKey(1)){
			MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
			WorldServer world = ((CraftWorld) Bukkit.getServer().getWorld(worldname)).getHandle();
			GameProfile profile = new GameProfile(UUID.fromString(UserID), name);
			SID = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
			SID.setPositionRotation(X, Y, Z, Yaw, Pitch);
			SID.getBukkitEntity().setPlayerListName("SID");
			g.put(1, SID);
		}else{
			ConsoleCommandSender console = Bukkit.getConsoleSender();
			console.sendMessage(Utils.color(SIDMadeMessage));
		}
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
            PlayerConnection connection = ((CraftPlayer) e.getPlayer()).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, SID));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(SID));
            //connection.sendPacket(new PacketPlayOutCamera());
	}
	
	public static void setRotation(Entity entity, float yaw, float pitch) {
	    EntityLiving nmsEntity = (EntityLiving) ((CraftEntity) entity).getHandle();
	    nmsEntity.pitch = pitch;
	    nmsEntity.lastPitch = pitch;
	    nmsEntity.yaw = yaw;
	    nmsEntity.lastYaw = yaw;
	    nmsEntity.aQ = yaw;
	    nmsEntity.aR = yaw;
	    nmsEntity.aO = yaw;
	    nmsEntity.aP = yaw;
	}

}
