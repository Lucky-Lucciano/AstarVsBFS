package com.ai.algorithms.utility;

public class Heuristics {
	
	
	public static Integer manhattanDistance(int startX, int startY, int endX, int endY) {
		return Math.abs(endX - startX) + Math.abs(endY - startY);
	}
}
