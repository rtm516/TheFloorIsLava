package com.rtm516.TheFloorIsLava;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandStart implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		TheFloorIsLava.instance.sendMessage("Starting The Floor Is Lava!");
		TheFloorIsLava.instance.sendMessage("20 minutes until the first lava rise");
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				doLava();
			}
		}, 20L * 60L * 20L - (20L * 10L));

		return true;
	}

	private void lavaTimer() {		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				doLava();
			}
		}, 20L * 60L - (20L * 10L));
	}
	
	private void doLava() {		
		//TheFloorIsLava.instance.sendMessage("10s until next lava rise");

		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("5s until next lava rise");
			}
		}, 20L * 5L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("4s until next lava rise");
			}
		}, 20L * 6L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("3s until next lava rise");
			}
		}, 20L * 7L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("2s until next lava rise");
			}
		}, 20L * 8L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("1s until next lava rise");
			}
		}, 20L * 9L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {				
				TheFloorIsLava.instance.sendMessage("Lava rising!");
				
				LavaInfo lavaInfo = TheFloorIsLava.instance.getLavaInfo();
				
				World world = lavaInfo.bottomRight.getWorld();
				Location edgeMin = lavaInfo.bottomRight;
				Location edgeMax = lavaInfo.topLeft;
			   
			    for (int x = edgeMin.getBlockX(); x <= edgeMax.getBlockX(); x ++) {
			        for (int y = edgeMin.getBlockY(); y <= edgeMax.getBlockY(); y ++) {
			            for (int z = edgeMin.getBlockZ(); z <= edgeMax.getBlockZ(); z ++) {
			            	Block block = new Location(world, x, y, z).getBlock();
			            	
			                if (block.getType() == Material.AIR) {
			                    block.setType(TheFloorIsLava.instance.getBlock());
			                }
			            }
			        }
			    }
				
				lavaInfo.IncreaseCurrentLevel();
				
				lavaTimer();
			}
		}, 20L * 10L);
	}
}
