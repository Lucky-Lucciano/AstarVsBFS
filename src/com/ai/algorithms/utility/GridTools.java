package com.ai.algorithms.utility;

import com.ai.algorithms.elements.GridField;

import javafx.scene.paint.Color;

public class GridTools {
	
	
	public static void highlightVisitedNode(GridField node) {
		node.getRectangle().setFill(Color.BLACK);
	}
	
	public static void highlightStartNode(GridField node) {
		node.getRectangle().setFill(Color.GREEN);
	}
	
	public static void highlightGoalNode(GridField node) {
		node.getRectangle().setFill(Color.GOLD);
	}
	
	public static void highlightGoalRoad(GridField node) {
		node.getRectangle().setStroke(Color.RED);
	}
}
