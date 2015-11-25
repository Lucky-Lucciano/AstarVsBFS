package com.ai.algorithms.utility;

import com.ai.algorithms.elements.GridField;

public class Heuristics {
	
	
	public static Integer manhattanDistance(GridField startNode, GridField goalNode) {
		return Math.abs(goalNode.getRow() - startNode.getRow()) + Math.abs(goalNode.getCol() - startNode.getCol());
	}
}
