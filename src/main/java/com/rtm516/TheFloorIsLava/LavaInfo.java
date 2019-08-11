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
		
		int increaseAmount = TheFloorIsLava.instance.getConfig().getInt("increaseAmount");
		
		this.bottomRight.setY(this.currentLevel);
		this.topLeft.setY(this.currentLevel + increaseAmount - 1);
	}
	
	public void IncreaseCurrentLevel() {
		int increaseAmount = TheFloorIsLava.instance.getConfig().getInt("increaseAmount");
		
		this.currentLevel += increaseAmount;
		
		this.bottomRight.setY(this.currentLevel);
		this.topLeft.setY(this.currentLevel + increaseAmount - 1);
	}
}
