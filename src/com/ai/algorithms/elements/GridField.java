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
	private int costSoFar;
	private int cost;
	private GridField cameFrom;
	
	
	public enum FIELD_TYPE {
		INITAL, WALL, GRASS, WATER
	}
	
	public GridField(int row, int col, double elementSize) {
		this.row = row;
		this.col = col;
		this.cost = 1;
		this.type = FIELD_TYPE.INITAL;
		
		Rectangle rectangle = new Rectangle(elementSize, elementSize);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.BISQUE);
		this.rectangle = rectangle;
	}
	
	public GridField(int row, int col) {
		this.row = row;
		this.col = col;
		this.cost = 1;
		this.type = FIELD_TYPE.INITAL;
		
		Rectangle rectangle = new Rectangle(50, 50);
        rectangle.setStroke(Color.ORANGE);
        rectangle.setFill(Color.BISQUE);
		this.rectangle = rectangle;
	}
	
	public void setEventHandler() {
		this.rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! X:" + row + " - " + col + " | " + event.getSource());
                if(type.equals(FIELD_TYPE.INITAL)) {
                	rectangle.setFill(Color.GREY);
                	type = FIELD_TYPE.WALL;
                	cost = 100000;
                } else if(type.equals(FIELD_TYPE.WALL)) {
                	rectangle.setFill(Color.GREEN);
                	type = FIELD_TYPE.GRASS;
                	cost = 5;
                } else if(type.equals(FIELD_TYPE.GRASS)) {
                	rectangle.setFill(Color.DEEPSKYBLUE);
                	type = FIELD_TYPE.WATER;
                	cost = 10;
                } else if(type.equals(FIELD_TYPE.WATER)) {
                	rectangle.setFill(Color.BISQUE);
                	type = FIELD_TYPE.INITAL;
                	cost = 1;
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
	public int getCostSoFar() {
		return costSoFar;
	}

	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
