import java.util.Scanner;
//import java.util.Arrays;


public class App {
	public static void main(String[] args) throws Exception {

        //make an instance of game creator to call the methods
        GameCreator game = new GameCreator();

        //create the 2d grid using an instance from Game creator
        String [][] twoDimensGrids = game.makeGrids();

        //print the grids
        game.displayGrids(twoDimensGrids);
        
        //make it the same as minesweeper grids. If it is smaller than the main grid, you will get an error when you access the max row and column (because the bomb check cant go beyond the bomb grid). Grid col size: +twoDimensGrids[0].length;
        String[][] bombBlocks= new String [twoDimensGrids.length][twoDimensGrids[0].length]; 

        
        //number of bombs
        int max =7;

        //make an instance of Mineswepbombs to call the methods        
        MinesweeperBomb detonator1 = new MinesweeperBomb();

        
        detonator1.getPlaceBombs(bombBlocks, max);

        //showing bomb locations - for debugging. 
        //System.out.println("bombs location: ");
        //detonator1.discloseBombs(bombBlocks);

        

        game.promptPlayer(twoDimensGrids, bombBlocks);
        //print it again
        game.displayGrids(twoDimensGrids);
        
    }

}