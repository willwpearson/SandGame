import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int HELIUM = 4;
  public static final int BOUNCE = 5;
  public static final int VIRUS = 6;
  public static final int FIRE = 7;
  public static final int VINE = 8;
  public static final int TELEPORT = 9;
  public static final int LASER = 10;
  
  //do not add any more fields below
  private int[][] grid;
  private boolean[] hitEdge;
  private int randomDirectionLaser;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[11];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[HELIUM] = "Helium";
    names[BOUNCE] = "Bounce";
    names[VIRUS] = "Virus";
    names[FIRE] = "Fire";
    names[VINE] = "Vine";
    names[TELEPORT] = "Teleport";
    names[LASER] = "Laser";
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    hitEdge = new boolean[numCols];
    randomDirectionLaser = (int)(Math.random() * 4);
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    
//    for(int row = 0; row < numRows; row++)
//    {
//    		for(int col = 0; col < numCols; col++)
//    		{
//    			grid[row][col] = BOUNCE;
//    		}
//    }
//    for(int row = numRows - 1; row >= numRows / 2; row--)
//    {
//    		for(int col = 0; col < numCols; col++)
//    		{
//    			grid[row][col] = HELIUM;
//    		}
//    }
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
	  Color color = null;
   //Hint - use a nested for loop
	  for(int row = 0; row < grid.length; row++)
	  {
		  for(int col = 0; col < grid[0].length; col++)
		  {
			  if(grid[row][col] == EMPTY)
			  {
				  color = Color.BLACK;
			  }
			  else if(grid[row][col] == METAL)
			  {
				  color = Color.GRAY;
			  }
			  else if(grid[row][col] == SAND)
			  {
				  color = new Color(204, 186, 25);
			  }
			  else if(grid[row][col] == WATER)
			  {
				  color = new Color(7, 125, 116);
			  }
			  else if(grid[row][col] == HELIUM)
			  {
				  color = Color.WHITE;
			  }
			  else if(grid[row][col] == BOUNCE)
			  {
				  color = new Color(255, 0, 200);
			  }
			  else if(grid[row][col] == VIRUS)
			  {
				  color = new Color(153, 51, 204);
			  }
			  else if(grid[row][col] == FIRE)
			  {
				  color = new Color(229, 64, 0);
			  }
			  else if(grid[row][col] == VINE)
			  {
				  color = new Color(45, 102, 25);
			  }
			  else if(grid[row][col] == TELEPORT)
			  {
				  color = new Color(255, 255, 0);
			  }
			  else if(grid[row][col] == LASER)
			  {
				  color = new Color(245, 50, 41);
			  }
			  display.setColor(row, col, color);
		  }
	  }
	  
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {  
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
	  int randomRow = (int)(Math.random() * grid.length - 1);
	  int randomCol = (int)(Math.random() * grid[0].length - 1);
	  int randomDirection = (int)(Math.random() * 3);
	  int randomDirectionFire = (int)(Math.random() * 4);
	  int randomSpread = (int)(Math.random() * 100);
	  int randomTelRow = (int)(Math.random() * grid.length);
	  int randomTelCol = (int)(Math.random() * grid[0].length);
	  
	  //Sand
	  if(grid[randomRow][randomCol] == SAND && (grid[randomRow + 1][randomCol] == EMPTY || grid[randomRow + 1][randomCol] == WATER))
	  {
		  if(grid[randomRow + 1][randomCol] == WATER)
		  {
			  grid[randomRow + 1][randomCol] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = WATER;
		  }
		  else
		  {
			  grid[randomRow + 1][randomCol] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = 0;
		  }

	  }
	  //Water
	  if(grid[randomRow][randomCol] == WATER && randomCol - 1 >= 0)
	  {
		  if(randomDirection == 1 && grid[randomRow + 1][randomCol] == EMPTY)
		  {
			  grid[randomRow + 1][randomCol] = WATER;
			  grid[randomRow][randomCol] = 0;
		  }
		  else if(randomDirection == 2 && grid[randomRow][randomCol - 1] == EMPTY)
		  {
			  grid[randomRow][randomCol - 1] = WATER;
			  grid[randomRow][randomCol] = 0;
		  }
		  else if(grid[randomRow][randomCol + 1] == EMPTY)
		  {
			  grid[randomRow][randomCol + 1] = WATER;
			  grid[randomRow][randomCol] = 0;
		  }
	  }
	  //Helium
	  if(grid[randomRow][randomCol] == HELIUM && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {
		  if(grid[randomRow - 1][randomCol] == WATER)
		  {
			  grid[randomRow - 1][randomCol] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = WATER;
		  }
		  else if(randomDirection == 1 && grid[randomRow - 1][randomCol] == EMPTY)
		  {
			  grid[randomRow - 1][randomCol] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = 0;
		  }
		  else if(randomDirection == 2 && grid[randomRow][randomCol - 1] == EMPTY)
		  {
			  grid[randomRow][randomCol - 1] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = 0;
		  }
		  else if(grid[randomRow][randomCol + 1] == EMPTY)
		  {
			  grid[randomRow][randomCol + 1] = grid[randomRow][randomCol];
			  grid[randomRow][randomCol] = 0;
		  }
	  }
	  
	  //Bounce
	  if(grid[randomRow][randomCol] == BOUNCE)
	  {  	
		  if(randomRow + 1 >= grid[0].length - 1 || randomRow + 1 == METAL)
		  {
			  hitEdge[randomCol] = true;
		  }
		  else if(randomRow - 1 < 0 || randomRow - 1 == METAL)
		  {
			  hitEdge[randomCol] = false;
		  }
		  
		  if(hitEdge[randomCol] == true)
		  {
			  grid[randomRow - 1][randomCol] = BOUNCE;
			  grid[randomRow][randomCol] = 0;
		  }
		  else
		  {
			 grid[randomRow + 1][randomCol] = BOUNCE;
			 grid[randomRow][randomCol] = 0;
		  }
	  }
	  
	  //Virus
	  if(grid[randomRow][randomCol] == VIRUS && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {
		  if(grid[randomRow + 1][randomCol] == EMPTY)
		  {
			  grid[randomRow + 1][randomCol] = VIRUS;
			  grid[randomRow][randomCol] = 0;
		  }
		  
			  int temp = grid[randomRow + 1][randomCol];
			  //VIRUS takeover
			  if(grid[randomRow - 1][randomCol] != EMPTY)
			  {
				  grid[randomRow - 1][randomCol] = VIRUS;
			  }
			  if(grid[randomRow + 1][randomCol] != EMPTY)
			  {
				  grid[randomRow + 1][randomCol] = VIRUS;
			  }
			  if(grid[randomRow][randomCol - 1] != EMPTY)
			  {
				  grid[randomRow][randomCol - 1] = VIRUS;
			  }
			  if(grid[randomRow][randomCol + 1] != EMPTY)
			  {
				  grid[randomRow][randomCol + 1] = VIRUS;
			  }
			  //TEMP takeover
			  grid[randomRow][randomCol] = temp;
			  if(grid[randomRow - 1][randomCol] != EMPTY)
			  {
				  grid[randomRow - 1][randomCol] = temp;
			  }
			  if(grid[randomRow + 1][randomCol] != EMPTY)
			  {
				  grid[randomRow + 1][randomCol] = temp;
			  }
			  if(grid[randomRow][randomCol - 1] != EMPTY)
			  {
				  grid[randomRow][randomCol - 1] = temp;
			  }
			  if(grid[randomRow][randomCol + 1] != EMPTY)
			  {
				  grid[randomRow][randomCol + 1] = temp;
			  }
	  }
	  
	  //Fire
	  if(grid[randomRow][randomCol] == FIRE && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {  
		  if(randomDirectionFire == 1)
		  {
			  grid[randomRow][randomCol] = EMPTY;
		  }
		  else if(randomDirectionFire == 2)
		  {
			  grid[randomRow - 1][randomCol - 1] = FIRE;
			  grid[randomRow][randomCol] = EMPTY;
		  }
		  else if(randomDirectionFire == 3)
		  {
			  grid[randomRow - 1][randomCol + 1] = FIRE;
			  grid[randomRow][randomCol] = EMPTY;
		  }
		  
		  //Fire Eats Vine
		  if(grid[randomRow - 1][randomCol] == VINE)
		  {
			  grid[randomRow - 1][randomCol] = FIRE;
		  }
		  if(grid[randomRow + 1][randomCol] == VINE)
		  {
			  grid[randomRow + 1][randomCol] = FIRE;
		  }
		  if(grid[randomRow][randomCol - 1] == VINE)
		  {
			  grid[randomRow][randomCol - 1] = FIRE;
		  }
		  if(grid[randomRow][randomCol + 1] == VINE)
		  {
			  grid[randomRow][randomCol + 1] = FIRE;
		  }
		  
		  //Fire Blows Up Helium
		  if(grid[randomRow - 1][randomCol] == HELIUM && randomRow - 2 >= 0 && randomCol - 2 >= 0 && randomRow + 2 < grid[0].length && randomCol + 2 < grid.length)
		  {
			  //Explosion
			  grid[randomRow - 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol + 2] = FIRE;
			  grid[randomRow + 2][randomCol - 2] = FIRE;
			  grid[randomRow - 2][randomCol + 2] = FIRE;
			  grid[randomRow - 2][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol + 2] = FIRE;
			  //Clear Explosion
			  grid[randomRow - 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol + 1] = EMPTY;
			  grid[randomRow + 1][randomCol - 1] = EMPTY;
			  grid[randomRow - 1][randomCol + 1] = EMPTY;
			  grid[randomRow - 1][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol + 1] = EMPTY;
		  }
		  if(grid[randomRow + 1][randomCol] == HELIUM && randomRow - 2 >= 0 && randomCol - 2 >= 0 && randomRow + 2 < grid[0].length && randomCol + 2 < grid.length)
		  {
			//Explosion
			  grid[randomRow - 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol + 2] = FIRE;
			  grid[randomRow + 2][randomCol - 2] = FIRE;
			  grid[randomRow - 2][randomCol + 2] = FIRE;
			  grid[randomRow - 2][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol + 2] = FIRE;
			  //Clear Explosion
			  grid[randomRow - 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol + 1] = EMPTY;
			  grid[randomRow + 1][randomCol - 1] = EMPTY;
			  grid[randomRow - 1][randomCol + 1] = EMPTY;
			  grid[randomRow - 1][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol + 1] = EMPTY;
		  }
		  if(grid[randomRow][randomCol - 1] == HELIUM && randomRow - 2 >= 0 && randomCol - 2 >= 0 && randomRow + 2 < grid[0].length && randomCol + 2 < grid.length)
		  {
			//Explosion
			  grid[randomRow - 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol + 2] = FIRE;
			  grid[randomRow + 2][randomCol - 2] = FIRE;
			  grid[randomRow - 2][randomCol + 2] = FIRE;
			  grid[randomRow - 2][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol + 2] = FIRE;
			  //Clear Explosion
			  grid[randomRow - 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol + 1] = EMPTY;
			  grid[randomRow + 1][randomCol - 1] = EMPTY;
			  grid[randomRow - 1][randomCol + 1] = EMPTY;
			  grid[randomRow - 1][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol + 1] = EMPTY;
		  }
		  if(grid[randomRow][randomCol + 1] == HELIUM && randomRow - 2 >= 0 && randomCol - 2 >= 0 && randomRow + 2 < grid[0].length && randomCol + 2 < grid.length)
		  {
			//Explosion
			  grid[randomRow - 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol] = FIRE;
			  grid[randomRow + 2][randomCol + 2] = FIRE;
			  grid[randomRow + 2][randomCol - 2] = FIRE;
			  grid[randomRow - 2][randomCol + 2] = FIRE;
			  grid[randomRow - 2][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol - 2] = FIRE;
			  grid[randomRow][randomCol + 2] = FIRE;
			  //Clear Explosion
			  grid[randomRow - 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol] = EMPTY;
			  grid[randomRow + 1][randomCol + 1] = EMPTY;
			  grid[randomRow + 1][randomCol - 1] = EMPTY;
			  grid[randomRow - 1][randomCol + 1] = EMPTY;
			  grid[randomRow - 1][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol - 1] = EMPTY;
			  grid[randomRow][randomCol + 1] = EMPTY;
		  }
	  }
	  
	  //Vine
	  if(grid[randomRow][randomCol] == VINE && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {
		  if(grid[randomRow + 1][randomCol] == EMPTY)
		  {  
			  if(randomSpread == 1)
			  {
				  grid[randomRow + 1][randomCol] = VINE;
			  }
		  }
		  else if(grid[randomRow + 1][randomCol] == WATER)
		  {
			  if(randomSpread >= 90)
			  {
				  grid[randomRow + 1][randomCol] = VINE;
				  grid[randomRow][randomCol + 1] = VINE;
				  grid[randomRow][randomCol - 1] = VINE;
			  }
		  }
	  }
	  
	  //Teleport
	  if(grid[randomRow][randomCol] == TELEPORT && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {
		  grid[randomTelRow][randomTelCol] = TELEPORT;
		  grid[randomRow][randomCol] = EMPTY;
	  }
	  
	  //Laser
	  if(grid[randomRow][randomCol] == LASER && randomCol - 1 >= 0 && randomRow - 1 >= 0)
	  {
		  if(randomDirectionLaser == 1 && randomRow - 4 >= 0)
		  {
			  grid[randomRow + 1][randomCol] = LASER;
			  grid[randomRow - 4][randomCol] = EMPTY;
			  if(randomRow + 1 == grid.length - 1)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow - 1][randomCol] = EMPTY;
				  grid[randomRow - 2][randomCol] = EMPTY;
				  grid[randomRow - 3][randomCol] = EMPTY;
			  }
		  }
		  else if(randomDirectionLaser == 2)
		  {
			  grid[randomRow - 1][randomCol] = LASER;
			  grid[randomRow + 4][randomCol] = EMPTY;
			  if(randomRow - 1 == 0)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow + 1][randomCol] = EMPTY;
				  grid[randomRow + 2][randomCol] = EMPTY;
				  grid[randomRow + 3][randomCol] = EMPTY;
			  }
		  }
		  else if(randomDirectionLaser == 3 && randomCol - 4 >= 0)
		  {
			  grid[randomRow][randomCol + 1] = LASER;
			  grid[randomRow][randomCol - 4] = EMPTY;
			  if(randomCol + 1 == grid[0].length - 1)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow][randomCol - 1] = EMPTY;
				  grid[randomRow][randomCol - 2] = EMPTY;
				  grid[randomRow][randomCol - 3] = EMPTY;
			  }
		  }
		  else
		  {
			  grid[randomRow][randomCol - 1] = LASER;
			  grid[randomRow][randomCol + 4] = EMPTY;
			  if(randomCol - 1 == 0)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow][randomCol + 1] = EMPTY;
				  grid[randomRow][randomCol + 2] = EMPTY;
				  grid[randomRow][randomCol + 3] = EMPTY;
			  }
		  }
	  }
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
