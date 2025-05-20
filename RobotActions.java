
/**
 * Write a description of class Robot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RobotActions
{
    private String[][] path;
    private int x;
    private int y;
    private int finalPositionX;
    private int finalPositionY;
    private String direction;
    
    public static void main(String[] args)
    {
        RobotActions grid = new RobotActions(4, 4);
        grid.printPath();
        
        grid.runSimulation();
    }
    
    public RobotActions(int rows, int cols)
    {
        path = new String[rows][cols];
        
        for (int row = 0; row < path.length; row++)
        {
                for (int col = 0; col < path[0].length; col++)
                {
                    int randNum = (int)(Math.random() * 10);
                    if (row == 0 && col == 0)
                    {
                        path[row][col] = "R";
                    }
                    else if (randNum > 7)
                    {
                        path[row][col] = "#";
                    }
                    else 
                    {
                        path[row][col] = "-";
                    }
                }
        }
        
        x = 0;
        y = 0;
        finalPositionX = rows - 1;
        finalPositionY = cols - 1;
        direction = "E";
    }
    
    public String[][] getPath()
    {
        return path;
    }
    
    public void printPath()
    {
        for (int row = 0; row < path.length; row++)
        {
            for (int col = 0; col < path[0].length; col++)
            {
                System.out.print(path[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    public void getPosition()
    {
        for (int row = 0; row < path.length; row++)
        {
            for (int col = 0; col < path[0].length; col++)
            {
                if (path[row][col].equals("R"))
                {
                    x = row;
                    y = col;
                }
            }
        }
    }
    
    public void turnRight()
    {
        getPosition();
        
        if (direction.equals("E"))
        {
            direction = "S";
            System.out.println("Turned right");
        }
        else if (direction.equals("S"))
        {
            direction = "W";
            System.out.println("Turned right");
        }
        else if (direction.equals("W"))
        {
            direction = "N";
            System.out.println("Turned right");
        }
        else if (direction.equals("N"))
        {
            direction = "E";
            System.out.println("Turned right");
        }
    }
    
    public void moveForward()
    {
        getPosition();
        
        int newX = x;
        int newY = y;
        
        if (direction.equals("E"))
        {
            newY++;
        }
        else if (direction.equals("W"))
        {
            newY--;      
        }
        else if (direction.equals("N"))
        {
            newX--;
        }
        else if (direction.equals("S"))
        {
            newX++;
        }
        
        if ((path.length > newX) && (path[0].length > newY) && (newX >= 0) && (newY >= 0) && (path[newX][newY].equals("-")))
        {
            path[x][y] = "-";
            x = newX;
            y = newY;
            path[x][y] = "R";
            
            System.out.println("Moved forward");
            //printPath();
        }
    }
    
    public void turnLeft()
    {
        getPosition();
        
        if (direction.equals("E"))
        {
            direction = "N";
            System.out.println("Turned left");
        }
        if (direction.equals("S"))
        {
            direction = "E";
            System.out.println("Turned left");
        }
        if (direction.equals("W"))
        {
            direction = "S";
            System.out.println("Turned left");
        }
        if (direction.equals("N"))
        {
            direction = "W";
            System.out.println("Turned left");
        }
    }
    
    public void moveBackwards()
    {
        getPosition();
        
        int newX = x;
        int newY = y;
        
        if (direction.equals("E"))
        {
            newY--;
        }
        else if (direction.equals("W"))
        {
            newY++;      
        }
        else if (direction.equals("N"))
        {
            newX--;
        }
        else if (direction.equals("S"))
        {
            newX++;
        }
        
        if ((path[newX][newY].equals("-")) && (path.length >= newX) && (path[0].length >= newY) && (newX >= 0) && (newY >= 0))
        {
            path[x][y] = "-";
            x = newX;
            y = newY;
            path[x][y] = "R";
            
            System.out.println("\nMoved backwards");
            System.out.println();
            printPath();
        }
    }
    
    public boolean canMoveForward()
    {
        getPosition();
        
        int newX = x;
        int newY = y;
        
        if (direction.equals("E"))
        {
            newY++;
        }
        else if (direction.equals("W"))
        {
            newY--;      
        }
        else if (direction.equals("N"))
        {
            newX--;
        }
        else if (direction.equals("S"))
        {
            newX++;
        }
        
        if ((path.length > newX) && (path[0].length > newY) && (newX >= 0) && (newY >= 0) && (path[newX][newY].equals("-")))
        {
            return true;
        }
        
        return false;
    }
    
    
    public void runSimulation()
    {
        printPath();
        getPosition();
        
        int steps = 0; 
        int maxSteps = (path.length) * (path[0].length) * 4;
        boolean finalPosition = path[finalPositionX][finalPositionY].equals("#");
        boolean atPosition = false;
        
        while ((path.length > x) && (path[0].length > y) && (x >= 0) && (y >= 0) && (steps <= maxSteps) && !atPosition && !finalPosition)
        {
            turnRight();
            
            if (canMoveForward())
            {
                moveForward();
                getPosition();
            }
            else 
            {
                turnLeft();
                getPosition();
                if (canMoveForward())
                {
                    moveForward();
                    getPosition();
                }
                else
                {
                    turnLeft();
                    if (canMoveForward())
                    {
                        moveForward();
                        getPosition();
                    }
                    else 
                    {
                        turnLeft();
                        getPosition();
                    }
                }
            }
            
            printPath();
            System.out.println("");
            getPosition();
            steps++;
            atPosition = (x == path.length - 1 && y == path[0].length - 1);
        }
        
        getPosition();

        if ((x == finalPositionX) && (y == finalPositionY))
        {
            System.out.println("Simulation done. Maze solved.");
            printPath();
        }
        else
        {
            System.out.println("Simulation done. Couldn't solve.");
            printPath();
        }
    }
    
}
