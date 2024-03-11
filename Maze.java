package patilProject03;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import edu.du.dudraw.DUDraw;

enum CellValue {WALL, OPENED, EXPLORED};

public class Maze {
	
	private CellValue[][] maze;
	private int width; 
	private int height;
	DLLQueue<Cell> cellQueue = new DLLQueue<Cell>();
	SLLStack<Cell> cellStack = new SLLStack<Cell>();
	private static final Color WALL = new Color(0);
	private static final Color OPENED = new Color(255, 255, 255);
	private static final Color EXPLORED = new Color(107, 142, 35);
	
	public Maze(int w, int h) {
		
		//takes width and height values as parameters and initializes the maze
		width = w;
		height = h;
		
		DUDraw.setCanvasSize(500, 500);
		DUDraw.setXscale(0, width);
		DUDraw.setYscale(0, height);
		
		this.maze = new CellValue[width][height];
		
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				
				maze[row][col] = CellValue.WALL;
			}
		}
	}
	
	public void draw() {
		
		DUDraw.clear();
		DUDraw.enableDoubleBuffering();
		
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				
				if (maze[row][col] == CellValue.WALL) {
					
					DUDraw.setPenColor(WALL);
					DUDraw.filledRectangle(col + .5, row + .5, .49, .49);
					
				} else if (maze[row][col] == CellValue.OPENED) {
					
					DUDraw.setPenColor(OPENED);
					DUDraw.filledRectangle(col + .5, row + .5, .49, .49);
					
				} else if (maze[row][col] == CellValue.EXPLORED) {
					
					DUDraw.setPenColor(EXPLORED);
					DUDraw.filledRectangle(col + .5, row + .5, .49, .49);
				}
			}
		}
		
		DUDraw.show();
		DUDraw.pause(1);
	}
	
	public void generateMaze() {
		
//		currentCell = (1,1) //this will be the starting point of building the maze
//				set the currentCell to Open
//				push currentCell on the stack
//				while stack is not empty
//				   pop the stack into currentCell
//				   initialize arrayList neighbors
//				   do the following for all of the neighboring Cell of currentCell
//				     the neighbors are to the north, south, east, west. 
//				     Neighbors are two away from this cell. See if they are in the array and and have value Wall (see picture below): 
//				         set cell to open and add to neighbors. 
//				         set the cell between (labeled wall to remove in picture) to open but don't add to neighbors 
//				   end 
//				   shuffle the arrayList neighbors
//				   push all the content of neighbors on the stack
//				end
//		

		maze[1][1] = CellValue.OPENED;
		Cell currentCell = new Cell(1,1);
		cellStack.push(currentCell);
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();


		while (!cellStack.isEmpty()) {

			currentCell = cellStack.pop();
			
			//to check all four sides
			//neighbors are two away from this cell
			if (currentCell.row + 2 < height && maze[currentCell.row + 2][currentCell.col].equals(CellValue.WALL)) {
				
				//set cell to open and add to neighbors
				maze[currentCell.row + 2][currentCell.col] = CellValue.OPENED;
				neighbors.add(new Cell (currentCell.row + 2 , currentCell.col));
				//set the cell between to open
				maze[currentCell.row + 1][currentCell.col] = CellValue.OPENED;

			}

			if (currentCell.row - 2 > 0 && maze[currentCell.row - 2][currentCell.col].equals(CellValue.WALL)) {

				//set cell to open and add to neighbors
				maze[currentCell.row - 2][currentCell.col] = CellValue.OPENED;
				neighbors.add(new Cell (currentCell.row - 2 , currentCell.col));
				//set the cell between to open
				maze[currentCell.row - 1][currentCell.col] = CellValue.OPENED;
			}

			if (currentCell.row < height && currentCell.col + 2 < width && maze[currentCell.row][currentCell.col + 2].equals(CellValue.WALL)) {

				//set cell to open and add to neighbors
				maze[currentCell.row][currentCell.col + 2] = CellValue.OPENED;
				neighbors.add(new Cell (currentCell.row , currentCell.col + 2));
				//set the cell between to open
				maze[currentCell.row][currentCell.col + 1] = CellValue.OPENED;
			}

			if (currentCell.col - 2 > 0 && maze[currentCell.row][currentCell.col - 2].equals(CellValue.WALL) && currentCell.col - 2 < width) {

				//set cell to open and add to neighbors
				maze[currentCell.row][currentCell.col - 2] = CellValue.OPENED;
				neighbors.add(new Cell (currentCell.row , currentCell.col - 2));
				//set the cell between to open
				maze[currentCell.row][currentCell.col - 1] = CellValue.OPENED;
			}
			
			//shuffle the arrayList neighbors
			Collections.shuffle(neighbors);
			
			//push all the content of neighbors on the stack
			for(Cell e : neighbors) {
				
				cellStack.push(e);
			}
			
			draw();

			if(cellStack.size() > (Math.pow((height * width), 2)) / 3) {
				
				break;
			}
		}
	}
	
	public void solveMaze() {
		
//		start = (1,1)
//				goal = (height-2, width-2)
//				currentCell = start //this will be the starting point of solving the maze
//				set the currentCell to Explored
//				enqueue currentCell on the queue
//				while queue is not empty
//				     dequeue the queue into currentCell
//				     if currentCell is the goal then return (we found the target)
//				     for each neighboring Cell n (those are cells that are one step away) that is not Explored: 
//				           set neighbor n to Explored and enqueue it on the queue. 
//				end
		
		maze[1][1] = CellValue.EXPLORED;
		Cell currentCell = new Cell(1,1);
		cellQueue.enqueue(currentCell);
		
		ArrayList<Cell> neighbors = new ArrayList<Cell>();


		while (!cellQueue.isEmpty()) {

			currentCell = cellQueue.first();
			
			//to check all four sides if they're open
			if (currentCell.row + 2 < height && maze[currentCell.row + 2][currentCell.col].equals(CellValue.OPENED) 
					&& maze[currentCell.row + 1][currentCell.col].equals(CellValue.OPENED)) {

				maze[currentCell.row + 2][currentCell.col] = CellValue.EXPLORED;
				neighbors.add(new Cell (currentCell.row + 2 , currentCell.col));
				maze[currentCell.row + 1][currentCell.col] = CellValue.EXPLORED;

			}

			if (currentCell.row - 2 > 0 && maze[currentCell.row - 2][currentCell.col].equals(CellValue.OPENED) 
					&& maze[currentCell.row - 1][currentCell.col].equals(CellValue.OPENED)) {

				maze[currentCell.row - 2][currentCell.col] = CellValue.EXPLORED;
				neighbors.add(new Cell (currentCell.row - 2 , currentCell.col));
				maze[currentCell.row - 1][currentCell.col] = CellValue.EXPLORED;
			}

			if (currentCell.row < height && currentCell.col + 2 < width && maze[currentCell.row][currentCell.col + 2].equals(CellValue.OPENED) 
					&& maze[currentCell.row][currentCell.col + 1].equals(CellValue.OPENED)) {

				maze[currentCell.row][currentCell.col + 2] = CellValue.EXPLORED;
				neighbors.add(new Cell (currentCell.row , currentCell.col + 2));
				maze[currentCell.row][currentCell.col + 1] = CellValue.EXPLORED;
			}

			if (currentCell.col - 2 > 0 && maze[currentCell.row][currentCell.col - 2].equals(CellValue.OPENED) && currentCell.col - 2 < width 
					&& maze[currentCell.row][currentCell.col - 1].equals(CellValue.OPENED)) {

				maze[currentCell.row][currentCell.col - 2] = CellValue.EXPLORED;
				neighbors.add(new Cell (currentCell.row , currentCell.col - 2));
				maze[currentCell.row][currentCell.col - 1] = CellValue.EXPLORED;
			}
			
			Collections.shuffle(neighbors);
			
			for(Cell e : neighbors) {
				
				cellQueue.enqueue(e);
			}
			
			draw();
			
			//goal = (height-2, width-2)
			if (maze[height - 2][width - 2].equals(CellValue.EXPLORED)) {
				
				System.out.println("Target found!");
				break;
			}
		}
	}
	
	//owns the value for the row and column of a cell in the maze
	private class Cell {
		
		private int row;
		private int col;
		
		public Cell(int r, int c) {
			
			row = r;
			col = c;
		}
		
		public String toString() {
			
			return row + ", " + col;
		}
	}
}
