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
		
		// Mozda ova dva polja ne trebaju ako cu dodatai ih kao svojstvo fielda
		// Stanja koja treba da se vide: RxC current node-a; Distance; Came from 
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
			
			// TODO Insert into field current node value
			// TODO higlightCurrentNode();
			
			for (GridField nodeBeingChecked : frontier) {
				if(nodeBeingChecked.equals("GOAL NODE RowXColumn")) {
					//TODO highlightGoal
					found = true;
					
					distance.put(nodeBeingChecked, distance.get(node) + 1);
					cameFrom.put(nodeBeingChecked, node);
					//TODO createGoalRoad();
				} else {
					if(!history.contains(nodeBeingChecked) && !que.contains(nodeBeingChecked)) {
						que.add(nodeBeingChecked);
						
						distance.put(nodeBeingChecked, distance.get(node) + 1);
						cameFrom.put(nodeBeingChecked, node);
					}
				}
			}
			
			//TODO LET EACH NODE HAVE CURRENT DISTANCE AND CAME FROM AS PROPERTY FIELDS???
		    //	   AND THEY UPDATE THEM FOR EACH WHILE CYCLE. They are always visible
			
			//TODO higlightVisitedNode(nodeBeingChecked);
			
			System.out.println(currentNode);
		}
	}

}
