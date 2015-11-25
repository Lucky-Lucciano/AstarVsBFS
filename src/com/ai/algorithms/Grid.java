package com.ai.algorithms;

import com.ai.algorithms.elements.GridField;
import com.ai.algorithms.heuristic.AStar;
import com.ai.algorithms.heuristic.GreedyBestFirst;
import com.ai.algorithms.search.BreadthFirst;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Grid extends Application {
	
	private GridDisplay gridDisplay;
	
	public class GridDisplay {
		private static final double ELEMENT_SIZE = 50;
		private static final double GAP = ELEMENT_SIZE/30;
		
		private TilePane tilePane = new TilePane();
		private Group display = new Group(tilePane);
		private int nRows;
		private int nCols;
		private GridField board[][];
		
		public GridDisplay(int nRows, int nCols) {
			tilePane.setStyle("-fx-background-color: rgba(255, 215, 0, 0.1);");
			tilePane.setHgap(GAP);
			tilePane.setVgap(GAP);
			
			this.nRows = nRows;
			this.nCols = nCols;
			board = new GridField[nRows + 1][nCols + 1];
			
			setColumns(nCols);
			setRows(nRows);
		}
		
		public void reGenerateFields(int newRows, int newColumns) {
			this.nRows = newRows;
			this.nCols = newColumns;
			this.board = new GridField[newRows + 1][newColumns + 1];
			
			setColumns(newColumns);
			setRows(newRows);
		}
		
		public void setColumns(int newColumns) {
            nCols = newColumns;
            tilePane.setPrefColumns(nCols);
            //createElements();
        }

        public void setRows(int newRows) {
            nRows = newRows;
            tilePane.setPrefRows(nRows);
            createElements();
        }
        
        public int getRows() {
            return nRows;
        }
        
        public int getColumns() {
            return nCols;
        }
        
        public GridField getElement(int row, int col) {
        	return board[row][col];
        }
        
        private void createElements() {
            tilePane.getChildren().clear();
            GridField temp;
            StackPane stp;
            Text text;
            
            for (int i = 1; i <= nRows; i++) {
                for (int j = 1; j <= nCols; j++) {
                	temp = createElement(i, j);
                	
                	text = new Text(i + "x" + j);
                	text.setFont(Font.font ("Verdana", 16));
                	text.setFill(Color.RED);
                	
                	stp = new StackPane();
                	stp.getChildren().addAll(temp.getRectangle(), text);
                	
                    tilePane.getChildren().add(stp);
                    board[i][j] = temp;
                }
            }
        }
        
        private GridField createElement(int xPos, int yPos) {
            GridField field = new GridField(xPos, yPos, ELEMENT_SIZE);
            field.setEventHandler();

            return field;
        }
        
        public Group getDisplay() {
            return display;
        }	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		//Grid with rectangels
		gridDisplay = new GridDisplay(10, 10);
		
		//Config Greed Best First
		int GBFStartRow = 2;
		int GBFStartCol = 2;
		int GBFGoalRow = 10;
		int GBFGoalCol = 6;
		
		//Config Greed Best First
		int ASStartRow = 2;
		int ASStartCol = 2;
		int ASGoalRow = 10;
		int ASGoalCol = 6;
		
		// Polja u grafici za unos redova/kolona
		TextField rowField = new TextField("10");
		TextField columnField = new TextField("10");
		Button btnGenerate = new Button("Generate fields");
		btnGenerate.setOnAction(e -> {
			System.out.println("Hi World 123");
			this.gridDisplay.reGenerateFields(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()));
		});
		Button btnBreadthFirst = new Button("Start Breadth first");
		btnBreadthFirst.setOnAction(e -> {
			System.out.println("Starting Breadth first....");
			BreadthFirst.recurBreadthFirst(gridDisplay, gridDisplay.getElement(1, 2), gridDisplay.getElement(5, 5));
		});
		
		Button btnGreedyBestFirst = new Button("Start Greedy search");
		btnGreedyBestFirst.setOnAction(e -> {
			System.out.println("Starting Greedy best first....");
			GreedyBestFirst gbf = new GreedyBestFirst(gridDisplay, gridDisplay.getElement(GBFStartRow, GBFStartCol), gridDisplay.getElement(GBFGoalRow, GBFGoalCol));
		});
		
		Button btnAStar = new Button("Start A* search");
		btnAStar.setOnAction(e -> {
			System.out.println("Starting A* search....");
			AStar.run(gridDisplay, gridDisplay.getElement(ASStartRow, ASStartCol), gridDisplay.getElement(ASGoalRow, ASGoalCol));
//			GreedyBestFirst gbf = new GreedyBestFirst(gridDisplay, gridDisplay.getElement(GBFStartRow, GBFStartCol), gridDisplay.getElement(GBFGoalRow, GBFGoalCol));
		});
		
		//Funkcija koja se izvrsava kada tekstualno polje izgubi fokus
		//createTextFieldActions(rowField, columnField);
		
		HBox fields = new HBox(10);
		fields.getChildren().add(rowField);
		fields.getChildren().add(new Label("x"));
		fields.getChildren().add(columnField);
		fields.getChildren().add(btnGenerate);
		fields.getChildren().add(btnBreadthFirst);
		fields.getChildren().add(btnGreedyBestFirst);
		fields.getChildren().add(btnAStar);
		
		BorderPane mainPanel = new BorderPane();
		mainPanel.setCenter(gridDisplay.getDisplay());
		mainPanel.setTop(fields);
		
		Scene scene = new Scene(mainPanel, 1000, 800);
		primaryStage.setTitle("Search algorithms");
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void createTextFieldActions(final TextField rowField, final TextField columnField) {
		// ov - observable value, t - old value, t1 - new value
		rowField.focusedProperty().addListener((ov, t, t1) -> {
			if(!t1) {
				if(!rowField.getText().equals("")) {
					try {
						int nbRow = Integer.parseInt(rowField.getText());
						gridDisplay.setRows(nbRow);
					} catch (NumberFormatException nfe) {
						System.out.println("Molimo vas unesite ispravan broj za redove");
					}
				}
			}
		});
		
		columnField.focusedProperty().addListener((ov, t, t1) -> {
			if(!t1) {
				if(!columnField.getText().equals("")) {
					try {
						int nbColumn = Integer.parseInt(columnField.getText());
						gridDisplay.setRows(nbColumn);
					} catch (NumberFormatException nfe) {
						System.out.println("Molimo vas unesite ispravan broj za kolone");
					}
				}
			}
		});
	}

}
