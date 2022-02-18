# AI-Sudoku-Solver

- Pranav Rathod
- CS 411 - Artificial Intelligence
- Project 1 : Sudoku Solver


This Project is used to solve a Sudoku Puzzel using Backtracking search algorithm and also performs consistency checking.
The Project is written in Java.

The program reads in data from a file input that is specified by the user, and then calls on the recursive function that performs backtrack search.
The Unsolved Sudoku puzzel is in the following format:

- |8|||9|3||||2|
- |||9|||||4||
- ...

An empty space/box in the Sudoku puzzel is represented || with no physical space.
The Board has the dimension of 9x9 and consists of 9 subgrids of 3x3

Function description:

main() : 
- Get the name of the file to read from.
- Call the readFromFile function
- print the initial sudoku board
- call the recursiveSolve for backtrack search,
- if solution is found call writeToFile()
- print error message in case no solution is found

-- Functions for File interaction

- readFromFile(String fileName): The filename is passed to the function, the function creates a File oject and traverses through each line and assigns the numbers to their respective array positions.

- writeToFile(String fileName): This function creates a new solution file and traverses through the solved sudoku puzzel array and writes it to a file in the specified format.


-- Functions for Consistency checking:

- existsInRow(int num, int row): function that returns true is the given number does NOT exist in the specified row, performs linear search.
 
- existsInColumn(int num, int col): function that returns true is the given number does NOT exist in the specified row, performs linear search.

- existsInSubGrid(int row, int col, int num): function returns true if the given element does NOT exist in the corresponding subgrid


-- Helper function:

- getSubGrid(int coordinate): function returns the start coordinate of the subgrid that the number being inserted/compared exist in.

-- Function to perform backtrack search: 

- recursionSolve(): function that traverses through the board and performs backtrack search using recursion in case there is an empty/unsolved space.
