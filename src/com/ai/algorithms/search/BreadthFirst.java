package com.ai.algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.algorithms.Grid.GridDisplay;
import com.ai.algorithms.elements.GridField;
import com.ai.algorithms.utility.GridTools;
import com.ai.algorithms.utility.Processing;


public class BreadthFirst {
	//private static GridDisplay grid;

	/*public static void main(String[] args) {
		
		recurBreadthFirst(new GridField(2, 2));
	}*/
	
	public static void recurBreadthFirst(GridDisplay grid, GridField startNode, GridField goalNode) {
		List<GridField> que = new ArrayList<GridField>();
		List<GridField> history = new ArrayList<GridField>();
		
		// Mozda ova dva polja ne trebaju ako cu dodatai ih kao svojstvo fielda
		// TODO Stanja koja treba da se vide: RxC current node-a; Distance; Came from 
		//Map<GridField , Integer> distance = new HashMap<GridField, Integer>();
		//Map<GridField , GridField> cameFrom = new HashMap<GridField, GridField>();
		boolean found = false;
		
		//TODO higlightstartNode
		GridTools.highlightStartNode(startNode);
		que.add(startNode);
		startNode.setDistance(0);
		startNode.setCameFrom(null);
		
		while(que.size() > 0 && !found) {
			GridField currentNode = que.remove(0);
			List<GridField> frontier = Processing.getFrontLine(grid, currentNode);
			System.out.println("Checking node : " + currentNode);
			history.add(currentNode);
			
			// TODO Insert into field current node value
			// TODO higlightCurrentNode();
			
			for (GridField nodeBeingChecked : frontier) {
				if(nodeBeingChecked.equals(goalNode)) {
					//TODO highlightGoal
					GridTools.highlightGoalNode(nodeBeingChecked);
					GridTools.highlightVisitedNode(currentNode);
					found = true;
					
					nodeBeingChecked.setDistance(currentNode.getDistance() + 1);
					nodeBeingChecked.setCameFrom(currentNode);

					System.out.println("GOAL NODE FOUND: " + nodeBeingChecked);
					Processing.createGoalRoad(goalNode);
					return;
				} else {
					if(!history.contains(nodeBeingChecked) && !que.contains(nodeBeingChecked)) {
						que.add(nodeBeingChecked);
						
						System.out.println("Adding new node to que: " + nodeBeingChecked);
						nodeBeingChecked.setDistance(currentNode.getDistance() + 1);
						nodeBeingChecked.setCameFrom(currentNode);
					}
				}
			}
			
			if(!currentNode.equals(startNode))
				GridTools.highlightVisitedNode(currentNode);
			
			System.out.println("Visited this node: " + currentNode);
		}
	}

}
