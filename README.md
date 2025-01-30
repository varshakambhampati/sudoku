Sudoku Validator:

This Java program validates a given Sudoku puzzle by checking the validity of the rows, columns, and subgrids. It utilizes multithreading to check all three simultaneously, improving performance and responsiveness for larger Sudoku puzzles.

Features
Multithreading:
The program leverages Java's Thread class to check the validity of rows, columns, and subgrids concurrently.

Dynamic Sudoku Size: 
The program allows users to input different Sudoku puzzle sizes (e.g., 4x4, 9x9).

Validation:
Validates the Sudoku puzzle for correct numbers (between 1 and the size of the puzzle) and ensures no number repeats in the same row, column, or subgrid.

How It Works:

The program prompts the user to input the size of the Sudoku puzzle (e.g., 4 for a 4x4 grid, 9 for a 9x9 grid).
It then asks the user to enter the numbers for the Sudoku grid.
The program uses multiple threads to check:
Each row
Each column
Each subgrid (3x3 for 9x9 puzzles, for example)
After checking all conditions, it prints whether the Sudoku solution is valid or invalid.

Requirements:

Java: The program requires Java 8 or above.

Multithreading Support: The program uses Java threads, so multithreading support must be available.


Code Explanation:

SudokuValidator.java: The main program logic that defines classes for row, column, and subgrid validation. Multithreading is utilized by creating Runnable tasks that run concurrently to validate rows, columns, and subgrids.

RowColumnObject: A base class for managing row and column information.

IsRowValid: Checks if a row contains valid values.

IsColumnValid: Checks if a column contains valid values.

IsSubgridValid: Checks if a subgrid (a 3x3 block) contains valid values.


Contributing:

Feel free to fork this repository and contribute improvements. Issues and pull requests are welcome!

