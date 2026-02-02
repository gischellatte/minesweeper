import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class GameCreator {

  public GameCreator() {

  }
Scanner scanning = new Scanner(System.in);
    
public String [][] makeGrids(){
    int rows=0;
    int cols=0;
    
    System.out.println("Enter an integer for the square grid size (min 5, max 15)");
    while(true){
      try {
        rows = scanning.nextInt();
        //if user wants to quit, press a 0 for the row
        if(rows == 0){
            System.out.println("Goodbye. Thank you for playing.");
            System.exit(0);
        }

        if (rows < 5){
          rows= 10;
          cols= rows;
          System.out.println("Min row and col size: 5x5. Reseting it to 10 x 10");
        }
        if (rows > 15){
          System.out.println("Max row and col size: 15x15. Reseting it to 10 x 10");
        }
        cols=rows;
        break;
      }
      catch (InputMismatchException e){
        System.out.println("Error, the grid must be an integer. Please restart the game.");
        scanning.nextLine();
        System.exit(0);
      }

    }
   
    //making the grid based on player's input
    String[][] mineSweepGrids = new String [rows][cols];
    for(int a=0; a<mineSweepGrids.length; a++){
        for (int b=0; b<mineSweepGrids[a].length; b++){
            mineSweepGrids[a][b]="?"; 
       }
    }
    return mineSweepGrids;
    
  }
   public void displayGrids(String [][] grid){  
    for(int a=0; a<grid.length; a++){//no of rows
        for (int b=0; b<grid[a].length; b++){//no of columns
            System.out.print(grid[a][b]); 
        }
        System.out.println(); //continue to the next line after printing each row
      }
    }

    public void showNeighbourCells(String[][] grid, String[][] bombArray, int row, int col){
         //continue only works in for, while and do-while loops. Exit if the cells next to it have been revealed
        if(!grid[row][col].equals("?")) {
            return;
        }
        // if(!grid[row][col].equals("X")) {
        //     return;
        // }
           
            
       MinesweeperBomb mb1= new MinesweeperBomb();
       int surroundingBombs= mb1.countSurroundingBombs(bombArray, row, col);

       if (surroundingBombs == 0){
        grid[row][col]= "0";
            for (int y=-1; y<=1; y++) {
               for (int z=-1; z<=1; z++) {
                int beginRow = row + y;
                int beginCol = col + z;
            //if it is a 0 (safe cell), reveal the surrounding cells through a recursive function
            if(beginRow>=0 && beginRow<grid.length && beginCol>=0 && beginCol<grid[0].length){
              //if(bombArray[beginRow][beginCol].equals("?")){
                    showNeighbourCells(grid, bombArray, beginRow, beginCol);
              // }
            }
          }
         }
       }
       else {
        //Show the string value of non 0 cells.
        grid [row][col] = String.valueOf(surroundingBombs);
       }
        
               
    }

    public void promptPlayer(String [][] grid, String[][] bombArray){

       boolean continueGame = true;
       while(continueGame){
        int row = 0;
        int col = 0;
        boolean correctRow=false;

        //row input prompt 
        while (!correctRow) {
        System.out.println("Please enter a row index between 1-"+grid[0].length); 
          try {
            row = scanning.nextInt() - 1;
            if(row >=0 && row <grid.length){
              correctRow=true;
            }
            else {
              System.out.println("Wrong input! Row must a whole number.");
            }
          }
          catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanning.nextLine(); // Remove the invalid token to avoid infinite loop
            }
        }

        //col input prompt 
        boolean correctCol = false;
        while (!correctCol) {
          System.out.println("Please enter a col index between 1-"+grid[0].length);
          try {
           col = scanning.nextInt() - 1;
           if(col >=0 && col < grid[0].length){
            correctCol=true;
           } 
           else {
              System.out.println("Wrong input! Column must a whole number.");
            }
          
          }
        catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanning.nextLine(); // Remove the invalid token to avoid infinite loop
            }

        }



        //if user enters a valid input for row and column, it will proceed to this part
        if(row >=0 && row <grid.length && col >=0 && col <grid[0].length){

            //displayGrids(grid);
            if(bombArray[row][col].equals("X")){
                
                System.out.println("You just hit the bomb! Game over. Please see your final results:");
                continueGame = false;
            }
            else {
              //int neighboringBombs = new MinesweeperBomb().countSurroundingBombs(bombArray, row, col);

              MinesweeperBomb mb2= new MinesweeperBomb();
              int neighboringBombs= mb2.countSurroundingBombs(bombArray, row, col);

          
              if (neighboringBombs==0){
                showNeighbourCells(grid, bombArray, row, col);
              }

              if (checkChampion(grid, bombArray)){
                //displayGrids(grid);
                continueGame=false;
              }

              else{
                grid[row][col]=String.valueOf(neighboringBombs);
              }

            }

            //shows the updated grid after each attempt
            displayGrids(grid);
        }
            
        
        //will be triggered if user enters a row or col number bigger or smaller than the grid size
        else {
           System.out.println("Both row and column must be a + integer between 1-" + grid[0].length);
           displayGrids(grid);
        }

      }
        scanning.close(); 
    }

    public boolean checkChampion(String [][] grid, String[][] bombArray){
 
     for(int n=0; n<grid.length; n++){//no of rows
        for (int m=0; m<grid[0].length; m++){//no of columns
           if(!bombArray[n][m].equals("X") && grid[n][m].equals("?"))
          {
            return false; //if the numbered cells are still covered by ?, the user has not won the game. 
            //the bomb array also includes the safe grids, not just the bomb grids
            
          }
        }
      }
    System.out.println("Congrats! You have opened all of the numbered boxes.");
    return true;
   
   } 
}
