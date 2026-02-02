import java.util.Random;

public class MinesweeperBomb {

    public MinesweeperBomb(){

    }
    
    public void getPlaceBombs(String[][] bombArray, int bombAmounts) {
    for(int a=0; a<bombArray.length; a++){//no of rows
        for (int b=0; b<bombArray[a].length; b++){//no of columns
            bombArray[a][b]=""; //initialising bomb amounts as empty
            System.out.print(bombArray[a][b] + " ");  

        }
        System.out.println();//continue to the next line after printing each row
      }

      int detonatorsPlaced = 0;
      while(detonatorsPlaced<bombAmounts){
        int randomRow= (int) Math.floor(Math.random()*bombArray.length);
        int randomCol = (int) Math.floor(Math.random()*bombArray[0].length);


        if(!bombArray[randomRow][randomCol].equals("X"))
        {
            bombArray[randomRow][randomCol]="X"; //if the block is empty, drop the bomb ><
            detonatorsPlaced++; 
        }
        
      }
    
    
    }

    //displaying bomb array - only for debugging 
    public void discloseBombs(String[][] bombArray){
        int rows=bombArray.length;
        int cols = bombArray[0].length;

        for(int a=0; a<rows; a++){//no of rows
        for (int b=0; b<cols; b++){//no of columns
            if(bombArray[a][b].equals("X")){
                System.out.print("X");
            } 
            else{
                System.out.print("?");
            }
        }
        System.out.println(); //Continue to the next line after printing each row
      }

    }

    public int countSurroundingBombs(String[][] bombArray,int row, int col){
    int count= 0;

    //Check the 8 surrounding cells
      for (int i=-1; i<=1; i++) {
        for (int j=-1; j<=1; j++) {
            int beginRow = row + i;
            int beginCol = col + j;          

            if(beginRow>=0 && beginRow<bombArray.length && beginCol>=0 && beginCol<bombArray[0].length){
               if(bombArray[beginRow][beginCol].equals("X")){
                count++;
               }
            }
            
        }
      }  
      return count;

    }

    
}
