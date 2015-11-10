package com.ai.algorithms.utility;

import java.util.ArrayList;
import java.util.List;

import net.etfbl.ui.Grid.GridDisplay;
import net.etfbl.ui.elements.GridField;
import net.etfbl.ui.elements.GridField.FIELD_TYPE;

public class Processing {
	
	public static List<GridField> getFrontLine(GridDisplay grid, GridField node) {
		int row = node.getRow();
		int col = node.getCol();
		List<GridField> frontier = new ArrayList<GridField>();
		GridField tempField;
		
		if((row-1) >= 0) {
			tempField = grid.getElement(row - 1, col);
			if(!tempField.getType().equals(FIELD_TYPE.WALL)) {
				frontier.add(tempField);
			}
		}
		
		if((col-1) >= 0) {
			tempField = grid.getElement(row, col-1);
			if(!tempField.getType().equals(FIELD_TYPE.WALL)) {
				frontier.add(tempField);
			}
		}
		
		if((row+1) < grid.getRows()) {
			tempField = grid.getElement(row + 1, col);
			if(!tempField.getType().equals(FIELD_TYPE.WALL)) {
				frontier.add(tempField);
			}
		}
		
		if((col+1) < grid.getColumns()) {
			tempField = grid.getElement(row, col+1);
			if(!tempField.getType().equals(FIELD_TYPE.WALL)) {
				frontier.add(tempField);
			}
		}
		
		return frontier;
	}
}
