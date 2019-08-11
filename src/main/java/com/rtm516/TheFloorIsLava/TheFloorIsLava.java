package com.rtm516.TheFloorIsLava;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TheFloorIsLava extends JavaPlugin {
	public static TheFloorIsLava instance;
	FileConfiguration configuration = getConfig();
	
	private Location spawn;
	private LavaInfo lavaInfo;
	
	private int size;
	private int startLevel;
	
    @Override
    public void onEnable() {
    	instance = this;
    	
    	configuration.options().copyDefaults(true);
    	saveConfig();
    	
    	size = configuration.getInt("borderSize", 200);
    	startLevel = configuration.getInt("startHeight", 64);
    	
    	//Check material is valid
    	if (Material.getMaterial(configuration.getString("block")) == null) {
    		getLogger().info("Invalid block ('" + configuration.getString("block") + "') found in config, using LAVA");
    		configuration.set("block", "LAVA");
    	}
    	
        
        spawn = getServer().getWorlds().get(0).getSpawnLocation();
        
        getServer().getWorlds().get(0).getWorldBorder().setCenter(spawn);
        getServer().getWorlds().get(0).getWorldBorder().setSize(size);
        
        Location bottomRight = spawn.clone().subtract((double)size / 2D, 0, (double)size / 2D);
        Location topLeft = spawn.clone().add((double)size / 2D, 0, (double)size / 2D);
        
        lavaInfo = new LavaInfo(bottomRight, topLeft, startLevel);
        
        this.getCommand("start").setExecutor(new CommandStart());
    }
    
    public Location getSpawn() {
    	return spawn;
    }
    
    public LavaInfo getLavaInfo() {
    	return lavaInfo;
    }
    
    public FileConfiguration getConfig() {
    	return configuration;
    }
    
    public Material getBlock() {
    	return Material.getMaterial(configuration.getString("block"));
    }
    
    public void sendMessage(String msg) { 
    	getServer().broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "The Floor Is Lava" + ChatColor.YELLOW + "] " + ChatColor.WHITE + msg);
    }
    
    public void sendCommand(String cmd) { 
    	ConsoleCommandSender console = getServer().getConsoleSender();
    	Bukkit.dispatchCommand(console, cmd);
    }
}