package patilProject03;

public class Driver {

	public static void main(String[] args) {
		
		Maze maze = new Maze(21, 21);
		maze.generateMaze();
		maze.solveMaze();
	}
}
