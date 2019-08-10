package com.rtm516.TheFloorIsLava;

import org.bukkit.Location;

public class LavaInfo {
	public Location bottomRight;
	public Location topLeft;
	
	public int currentLevel;

	public LavaInfo(Location bottomRight, Location topLeft, int currentLevel) {
		super();
		this.bottomRight = bottomRight;
		this.topLeft = topLeft;
		this.currentLevel = currentLevel;
		
		this.bottomRight.setY(this.currentLevel);
		this.topLeft.setY(this.currentLevel);
	}
	
	public void IncreaseCurrentLevel() {
		this.currentLevel++;
		
		this.bottomRight.setY(this.currentLevel);
		this.topLeft.setY(this.currentLevel);
	}
}
