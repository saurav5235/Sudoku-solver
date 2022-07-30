import java.util.*;
import java.io.*;

public class SudokuSolver
{
    private int row[][] = new int[9][10];
    private int col[][] = new int[9][10];
    private int box[][] = new int[9][10];
    private int flag = 0;
    private char board[][] = new char[9][9];
    private HashSet<Character> rows[] = new HashSet[9];
    private HashSet<Character> cols[] = new HashSet[9];
    private HashSet<Character> boxes[] = new HashSet[9];

    public void fill()
    {
        Scanner sc = new Scanner(System.in);

        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                board[i][j] = sc.next().charAt(0);
            }
        }
    }

    public void printSolution()
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                System.out.print(board[i][j]+"  ");
            }
            System.out.println();
        }
    }

    public boolean isValidSudoku() 
    {   
        for (int r = 0; r < 9; r++) 
        {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }
        
        for (int r = 0; r < 9; r++) 
        {
            for (int c = 0; c < 9; c++) 
            {
                char val = board[r][c];
                
                if (val == '.') 
                    continue;
                
                if (rows[r].contains(val)) 
                    return false;

                rows[r].add(val);

                if (cols[c].contains(val)) 
                    return false;
                
                cols[c].add(val);

                int idx = (r / 3) * 3 + c / 3;
                
                if (boxes[idx].contains(val)) 
                    return false;

                boxes[idx].add(val);
            }
        }
        return true;
    }

    public void solveSudoku() 
    {        
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(board[i][j]!='.')
                {
                    row[i][board[i][j]-'0'] = 1;
                    col[j][board[i][j]-'0'] = 1;
                    box[3*(i/3) + (j/3)][board[i][j]-'0'] = 1;
                }
            }
        }
        
        fun(0,0);
    }

    public void fun(int i,int j)
    {
        if(i>=9 || j>=9)
        {
            flag = 1;
            return;
        }
        
        if(board[i][j]!='.')
        {
            if(j+1==9)
                fun(i+1,0);
            else
                fun(i,j+1);
        }
        else
        {
            for(int k=1;k<=9;k++)
            {
                if(row[i][k]!=1 && col[j][k]!=1 && box[3*(i/3) + (j/3)][k]!=1)
                {
                    row[i][k] = 1;
                    col[j][k] = 1;
                    box[3*(i/3) + (j/3)][k] = 1;
                    board[i][j] = (char)('0'+k);
                    if(j+1==9)
                        fun(i+1,0);
                    else
                        fun(i,j+1);
                    if(flag==0)
                    {
                        row[i][k] = 0;
                        col[j][k] = 0;
                        box[3*(i/3) + (j/3)][k] = 0;
                        board[i][j] = '.';
                    }
                    else
                        return;
                }  
            }
        }
    }
}
