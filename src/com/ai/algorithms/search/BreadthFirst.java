package com.ai.algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.etfbl.ui.elements.GridField;

public class BreadthFirst {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		recurBreadthFirst(new GridField(2, 2));
	}
	
	public static void recurBreadthFirst(GridField node) {
		List<GridField> que = new ArrayList<GridField>();
		Map<GridField , Integer> distance = new HashMap<GridField, Integer>();
		Map<GridField , GridField> cameFrom = new HashMap<GridField, GridField>();
		boolean found = false;
		
		//TODO higlightstartNode
		que.add(node);
		distance.put(node, 0);
		cameFrom.put(node, null);
		
		while(que.size() > 0 && !found) {
			GridField currentNode = que.remove(0);
			
			System.out.println(currentNode);
		}
	}

}
