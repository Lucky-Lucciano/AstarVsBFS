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

public class GreedyBestFirst {
	private static GridDisplay grid;
	private static GridField goalNode;
	private static PriorityQueue<GridField> frontier;
	
	public GreedyBestFirst(GridDisplay startGrid, GridField start, GridField goal) {
		grid = startGrid;
		goalNode = goal;
		frontier = new PriorityQueue<GridField>();
		
		recurGreedyBestFirst(start);
	}
	
	public static void recurGreedyBestFirst(GridField startNode) {
//		List<GridField, Integer> frontier = new <GridField, Integer>();
//		Map<GridField, Integer> frontier = new HashMap<GridField, Integer>();
		
		Map<GridField, GridField> cameFrom = new HashMap<GridField, GridField>();
		
		boolean found = false;
		
		GridTools.highlightStartNode(startNode);
		frontier.enqueue(startNode, 0);
//		startNode.setCostSoFar(0);
		//node.setCameFrom(null);
		cameFrom.put(startNode, null);
		
		while(frontier.size() > 0 && !found) {
//			GridField firstEl = frontier.entrySet().iterator().next().getKey();
//			GridField currentNode = frontier.keySet().iterator().remove();
			GridField currentNode = frontier.dequeue();
			List<GridField> neighbours = Processing.getFrontLine(grid, currentNode);
			System.out.println("Checking node : " + currentNode);
//			history.add(currentNode);
			
			if(currentNode.equals(goalNode)) {
//				GridTools.highlightVisitedNode(currentNode);
				found = true;
				
//				currentNode.setDistance(currentNode.getDistance() + 1);
//				currentNode.setCameFrom(currentNode);

				System.out.println("GOAL NODE FOUND: " + currentNode);
				Processing.createGoalRoadFromList(goalNode, cameFrom);
				GridTools.highlightGoalNode(currentNode);
				GridTools.highlightGoalRoad(currentNode);
				return;
			}
			
			for(int i = 0; i < neighbours.size(); i++) {
				GridField nodeBeingChecked = neighbours.get(i);
				
				if(!cameFrom.containsKey(nodeBeingChecked)) {
//					System.out.println("Manhattan: " + nodeBeingChecked.getRow() + " - " + goalNode.getRow() + "; " + nodeBeingChecked.getCol());
					Integer priority = Heuristics.manhattanDistance(nodeBeingChecked.getRow(), nodeBeingChecked.getCol(), goalNode.getRow(), goalNode.getCol());
//					cameFrom.add(nodeBeingChecked);
					
					frontier.enqueue(nodeBeingChecked, priority);
					System.out.println("Adding new node to que: " + nodeBeingChecked + "; Priority: " + priority);
//					nodeBeingChecked.setDistance(currentNode.getDistance() + 1);
					cameFrom.put(nodeBeingChecked, currentNode);
//					nodeBeingChecked.setCameFrom(currentNode);
				}
			}
			
			if(!currentNode.equals(startNode))
				GridTools.highlightVisitedNode(currentNode);
			
			System.out.println("Visited this node: " + currentNode);
		}
	}
}
