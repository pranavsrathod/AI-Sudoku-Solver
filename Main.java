// Pranav Rathod
// CS 411 - Artificial Intelligence - Spring 2022
// Sudoku Solver.
// This Code was implemented and run on replit.com online IDE,
// Used OpenJDK to run the code.
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class Main {
  // sudoku board is of Size 9 x 9
  static int puzzel[][] = new int[9][9];

  // File reading method that loads the numbers from the file in the array an unfilled space in the file is denoted || with NO space in between.
  private static void readFromFile(String fileName) throws FileNotFoundException{
    try {
    File file = new File(fileName);
    Scanner scan = new Scanner(file);

    int a = 0;
    int b = 0;

    // assuming that the file has 9 lines
    for (int i = 0; i < 9; i++){
      String line = scan.nextLine();
      //parsing each line to put numbers inside the 2D array
      b = 0;
      for (int j = 1; j < line.length(); j++){
        char ch = line.charAt(j);
        if (ch == '|' && line.charAt(j-1) == '|'){
          // || depicts that there is an unfilled space
          // denote with 0
          puzzel[a][b] = 0;
          b++;
        } else if (ch != '|' && line.charAt(j-1) == '|'){
          puzzel[a][b] = Character.getNumericValue(ch);
          b++;
        } else {
          // | is preceded by a number, do not increment column index
        }
      }
      a++;
    }
    scan.close();
    } catch (Exception e){
      e.printStackTrace();
      System.out.println("ERROR WHILE READING FROM FILE");
    }
  }

// Function to write the solution the solution onto a file
  private static void writeToFile(String filename){
    String solutionFile = filename + "Solution.txt";
    try {
      // create file object
      File newFile = new File(solutionFile);
      // if (newFile.createNewFile()){
        // create a new file that the solution would be written to
        newFile.createNewFile();

        FileWriter writer = new FileWriter(solutionFile);
        for(int i = 0; i < 9; i++){
          String line = "|";
          for (int j = 0; j < 9; j++){
            line = line + puzzel[i][j] + "|";
          }
          // Only add a new line if it is not the last row of the solved puzzel.
          if(i != 8){
            line = line + "\n";
          }
          System.out.print(line);
          writer.write(line);
        }
        writer.close();
        System.out.println("\n\nThe Solution is available in the File: " + solutionFile);
      // } else {
      //   System.out.println("A Solution file already exists");
      // }
    } catch (Exception e){
      System.out.println("ERROR WHILE WRITING TO FILE");
      // e.printStackTrace();
    }

  }

  // function that returns true is the given number does NOT exist in the specified row, performs linear search.
  private static boolean existsInRow(int num, int row){
    for (int i = 0; i < 9; i++){
      if (puzzel[row][i] == num){
        return false;
      }
    }
    return true;
  }

  // function that returns true is the given number does NOT exist in the specified row, performs linear search.
  private static boolean existsInColumn(int num, int col){ 
    for (int i = 0; i < 9; i++){
      if (puzzel[i][col] == num){
        return false;
      }
    }
    return true;
  }

  // function returns the start coordinate of the subgrid that the number being inserted/compared exist in.
  private static int getSubGrid(int coordinate){
    if (coordinate < 3){
      return 0;
    } else if (coordinate >= 3 && coordinate < 6){
      return 3;
    } else {
      return 6;
    }
  }
  // function returns true if the given element does NOT exist in the corresponding subgrid
  private static boolean existsInSubGrid(int row, int col, int num){
    int rowEnd = getSubGrid(row) + 3;
    // int rowEnd = rowStart + 3;
    int colEnd = getSubGrid(col) + 3;
    // int colEnd = colStart + 3;
    
    for (int i = rowEnd - 3; i < rowEnd; i++){
      for (int j = colEnd - 3; j < colEnd; j++){
        if(puzzel[i][j] == num){
          return false;
        }
      }
    }

    return true;
  }

  // function that traverses through the board and performs backtrack search using recursion in case there is an empty/unsolved space.
  private static boolean recursionSolve(){
    boolean solved = true; // flag variable;
    int atRow = 10;
    int atCol = 10;

    // search for an element represented by the
    for (int i = 0; i < 9; i++){
      for (int j = 0; j < 9; j++) {
        if (puzzel[i][j] == 0){
          // System.out.println("Empty at [" + i + "]["+ j + "]");
          atRow = i;
          atCol = j;
          solved = false;
          break;
        }
      }
      if (!solved){
        // break if flag variable is false, which means there exists an empty space;
        break;
      }
    }

    try {
    // in case no empty space is found, It means the sudoku puzzel is solved and we can return true
      if (!solved) {
        for (int i = 1; i < 10; i++){
          if (existsInRow(i, atRow) && existsInColumn(i, atCol) && existsInSubGrid(atRow, atCol, i) ){
            puzzel[atRow][atCol] = i;
            // implement backtracking & recursion 
            if (!recursionSolve()){
              puzzel[atRow][atCol] = 0;
            } else {
              return true;
            }
          }
        }
      } else {
        return true;
      }
    } catch (Exception e) {
      System.out.println("Error Occured While checking for valid position");
    }
  
    //default case in case sodoku cannot be solved
    return false;
  }
  
  
  public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner (System.in);

		System.out.println("Enter a File name");
    String filename = input.nextLine();
		filename = filename.trim();
    readFromFile(filename);

    // readFromFile("puzzel3.txt");

    System.out.println("\nPRINTING SUDOKU TABLE FROM INPUT\n");
    for (int i = 0; i < 9; i++){
      System.out.print("|");
      for (int j = 0; j < 9; j++){
        if (puzzel[i][j] == 0){
          System.out.print(" |");
        } else {
          System.out.print(puzzel[i][j] + "|");
        }
        // System.out.print(puzzel[i][j] + " ");
      }
      System.out.println();
    }
    if (recursionSolve()){
      System.out.println("\nPRINTING SOLVED TABLE\n");
      writeToFile(filename.substring(0,filename.indexOf(".")));
    } else {
      System.out.println("\nSUDOKU PUZZEL COULD NOT BE SOLVED");
    }
    
    // writeToFile("puzzel3");
    // System.out.println(scan.nextLine());
    input.close();
    // scan.close();
	}

}