package com.ai.algorithms.heuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.algorithms.Grid.GridDisplay;
import com.ai.algorithms.elements.GridField;
import com.ai.algorithms.utility.GridTools;
import com.ai.algorithms.utility.Heuristics;
import com.ai.algorithms.utility.PriorityQueue;
import com.ai.algorithms.utility.Processing;

public class AStar {
	private static GridDisplay grid;
//	private static GridField goalNode;
	private static PriorityQueue<GridField> frontier;
	
//	public AStar(GridDisplay startGrid, GridField start, GridField goal) {
//		
//		
//		recurGreedyBestFirst(start);
//	}
	
	public static void run(GridDisplay startGrid, GridField startNode, GridField goalNode) {
		grid = startGrid;
//		goalNode = goal;
		frontier = new PriorityQueue<GridField>();
		
		Map<GridField, GridField> cameFrom = new HashMap<GridField, GridField>();
		boolean found = false;
		
		GridTools.highlightStartNode(startNode);
		frontier.enqueue(startNode, 0);
		cameFrom.put(startNode, null);
		
		while(frontier.size() > 0 && !found) {
			GridField currentNode = frontier.dequeue();
			List<GridField> neighbours = Processing.getFrontLine(grid, currentNode);
			System.out.println("Checking node : " + currentNode);
			
			if(currentNode.equals(goalNode)) {
				found = true;

				System.out.println("GOAL NODE FOUND: " + currentNode);
				Processing.createGoalRoadFromList(goalNode, cameFrom);
				GridTools.highlightGoalNode(currentNode);
				GridTools.highlightGoalRoad(currentNode);
				return;
			}
			
			for(int i = 0; i < neighbours.size(); i++) {
				GridField nodeBeingChecked = neighbours.get(i);
				
				if(!cameFrom.containsKey(nodeBeingChecked)) {
					Integer priority = Heuristics.manhattanDistance(nodeBeingChecked.getRow(), nodeBeingChecked.getCol(), goalNode.getRow(), goalNode.getCol());
					
					frontier.enqueue(nodeBeingChecked, priority);
					System.out.println("Adding new node to que: " + nodeBeingChecked + "; Priority: " + priority);
					cameFrom.put(nodeBeingChecked, currentNode);
				}
			}
			
			if(!currentNode.equals(startNode))
				GridTools.highlightVisitedNode(currentNode);
			
			System.out.println("Visited this node: " + currentNode);
		}
	}
}
