package com.ai.algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.etfbl.ui.Grid.GridDisplay;
import net.etfbl.ui.elements.GridField;
import com.ai.algorithms.utility.Processing;


public class BreadthFirst {
	private static GridDisplay grid;

	public static void main(String[] args) {
		
		recurBreadthFirst(new GridField(2, 2));
	}
	
	public static void recurBreadthFirst(GridField node) {
		List<GridField> que = new ArrayList<GridField>();
		List<GridField> history = new ArrayList<GridField>();
		Map<GridField , Integer> distance = new HashMap<GridField, Integer>();
		Map<GridField , GridField> cameFrom = new HashMap<GridField, GridField>();
		boolean found = false;
		
		//TODO higlightstartNode
		que.add(node);
		distance.put(node, 0);
		cameFrom.put(node, null);
		
		while(que.size() > 0 && !found) {
			GridField currentNode = que.remove(0);
			List<GridField> frontier = Processing.getFrontLine(grid, currentNode);
			//GridField nodeBeingChecked;
			
			history.add(currentNode);
			
			// Insert into field current node value
			// higlightCurrentNode();
			
			for (GridField gridField : frontier) {
				if(gridField.equals("GOAL NODE RowXColumn")) {
					//highlightGoal
					found = true;
					
					distance.put(gridField, distance.get(node) + 1);
					cameFrom.put(gridField, node);
					//createGoalRoad();
				}
			}
			
			
			
			System.out.println(currentNode);
		}
	}

}
