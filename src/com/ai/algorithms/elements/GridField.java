package com.ai.algorithms.elements;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridField {
	private int row;
	private int col;
	private FIELD_TYPE type;
	private Rectangle rectangle;
	private int distance;
	private GridField cameFrom;
	
	
	public enum FIELD_TYPE {
		INITAL, WALL
	}
	
	public GridField(int row, int col, double elementSize) {
		this.row = row;
		this.col = col;
		this.type = FIELD_TYPE.INITAL;
		
		Rectangle rectangle = new Rectangle(elementSize, elementSize);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.STEELBLUE);
		this.rectangle = rectangle;
	}
	
	public GridField(int row, int col) {
		this.row = row;
		this.col = col;
		this.type = FIELD_TYPE.INITAL;
		
		Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.STEELBLUE);
		this.rectangle = rectangle;
	}
	
	public void setEventHandler() {
		this.rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! X:" + row + " - " + col + " | " + event.getSource());
                if(type.equals(FIELD_TYPE.INITAL)) {
                	rectangle.setFill(Color.BISQUE);
                	type = FIELD_TYPE.WALL;
                } else if(type.equals(FIELD_TYPE.WALL)) {
                	rectangle.setFill(Color.STEELBLUE);
                	type = FIELD_TYPE.INITAL;
                }
            }
        });
	};
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getRow() + "x" + getCol();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public FIELD_TYPE getType() {
		return type;
	}

	public void setType(FIELD_TYPE type) {
		this.type = type;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public GridField getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(GridField cameFrom) {
		this.cameFrom = cameFrom;
	}
}
