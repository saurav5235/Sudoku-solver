import java.util.*;
import java.io.*;

public class Solution
{
    public static void main(String[] args) 
    {
        SudokuSolver obj = new SudokuSolver();
        System.out.println("Enter incomplete sudoku");
        obj.fill();
        if(obj.isValidSudoku())
        {
            obj.solveSudoku();
            System.out.println("Solution of the given solution is");
            obj.printSolution();
        }
        else
            System.out.println("Entered sudoku is not valid");
    }
}