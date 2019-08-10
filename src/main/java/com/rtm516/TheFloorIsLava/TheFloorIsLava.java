package com.rtm516.TheFloorIsLava;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class TheFloorIsLava extends JavaPlugin {
	public static TheFloorIsLava instance;
	
	private Location spawn;
	private LavaInfo lavaInfo;
	
	private int size = 200;
	private int startLevel = 64;
	
    @Override
    public void onEnable() {
    	instance = this;
        
        spawn = getServer().getWorlds().get(0).getSpawnLocation();
        
        getServer().getWorlds().get(0).getWorldBorder().setCenter(spawn);
        getServer().getWorlds().get(0).getWorldBorder().setSize(size);
        
        Location bottomRight = spawn.clone().subtract((double)size / 2D, 0, (double)size / 2D);
        Location topLeft = spawn.clone().add((double)size / 2D, 0, (double)size / 2D);
        
        lavaInfo = new LavaInfo(bottomRight, topLeft, startLevel);
        
        this.getCommand("start").setExecutor(new CommandStart());
    }
    
    @Override
    public void onDisable() { }
    
    public Location getSpawn() {
    	return spawn;
    }
    
    public LavaInfo getLavaInfo() {
    	return lavaInfo;
    }
    
    public Material getBlock() {
    	return Material.LAVA;
    }
    
    public void sendMessage(String msg) { 
    	getServer().broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "The Floor Is Lava" + ChatColor.YELLOW + "] " + ChatColor.WHITE + msg);
    }
    
    public void sendCommand(String cmd) { 
    	ConsoleCommandSender console = getServer().getConsoleSender();
    	Bukkit.dispatchCommand(console, cmd);
    }
}