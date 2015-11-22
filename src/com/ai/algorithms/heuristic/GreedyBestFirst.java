package com.ai.algorithms.heuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.algorithms.Grid.GridDisplay;
import com.ai.algorithms.elements.GridField;
import com.ai.algorithms.utility.GridTools;
import com.ai.algorithms.utility.Heuristics;
import com.ai.algorithms.utility.Processing;

public class GreedyBestFirst {
	private static GridDisplay grid;
	private static GridField goalNode;
	private static PriorityQueue<GridField> frontier;
	
	// For mutli varialbe lists
	public class Tuple<X, Y> {
		public X node;
		public Y prirority;
		
		public Tuple(X item, Y priority) {
			this.node = item;
			this.prirority = priority;
		}
	}
	
	public class PriorityQueue<T> {
	    // I'm using an unsorted array for this example, but ideally this
	    // would be a binary heap. Find a binary heap class:
	    // * https://bitbucket.org/BlueRaja/high-speed-priority-queue-for-c/wiki/Home
	    // * http://visualstudiomagazine.com/articles/2012/11/01/priority-queues-with-c.aspx
	    // * http://xfleury.github.io/graphsearch.html
	    // * http://stackoverflow.com/questions/102398/priority-queue-in-net
	    
	    private List<Tuple<T, Integer>> elements = new ArrayList<Tuple<T, Integer>>();
	    
	    public int size() {
	        return elements.size();
	    }
	    
	    public void enqueue(T item, Integer priority) {
	    	elements.add(new Tuple<T, Integer>(item, priority));
	    }

	    public T dequeue() {
	        int bestIndex = 0;

	        for (int i = 0; i < elements.size(); i++) {
	            if (elements.get(i).prirority < elements.get(bestIndex).prirority) {
	                bestIndex = i;
	            }
	        }

	        T bestItem = elements.get(bestIndex).node;
	        elements.remove(bestIndex);
	        return bestItem;
	    }
	}
	
	public GreedyBestFirst(GridDisplay startGrid, GridField startNode, GridField goal) {
		grid = startGrid;
		goalNode = goal;
		frontier = new PriorityQueue<GridField>();
		
		recurGreedyBestFirst(startNode);
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
				GridTools.highlightGoalNode(currentNode);
//				GridTools.highlightVisitedNode(currentNode);
				found = true;
				
//				currentNode.setDistance(currentNode.getDistance() + 1);
//				currentNode.setCameFrom(currentNode);

				System.out.println("GOAL NODE FOUND: " + currentNode);
				Processing.createGoalRoadFromList(goalNode, cameFrom);
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
