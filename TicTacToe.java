package com.dkit;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean draw;
        boolean player1Win;
        boolean player2Win;

        int rounds;
        int rowLoc;
        int colLoc;
        int count;
        int player1WinCount = 0;
        int player2WinCount = 0;
        int drawCount = 0;

        char blank = ' ';
        char x = 'X';
        char o = 'O';

        char[][] ticTacToe;

        System.out.println("How many rounds would you like to play?");
        rounds = input.nextInt();

        while (rounds < 1)
        {
            System.out.println("Invalid input, please try again");
            System.out.println("How many rounds would you like to play?");
            rounds = input.nextInt();
        }

        for (int i = 1; i <= rounds; i++)
        {
            draw = false;
            player1Win = false;
            player2Win = false;
            count = 0;

            ticTacToe = new char[][]{
                    {blank, blank, blank},
                    {blank, blank, blank},
                    {blank, blank, blank}
            };

            System.out.println("Round " + i);
            System.out.println("----------------");

            drawBoard(ticTacToe);
            
            //Round will continue unless checkForWin returns 'True' for any player, or if all the squares have been filled without a winner, resulting in a draw
            while (!draw && !player1Win && !player2Win) 
            {
                if (count % 2 == 0)
                {
                    System.out.println("Player 1's turn");
                }
                else
                {
                    System.out.println("Player 2's turn");
                }
                
                System.out.print("Enter co-ordinate of row in the range [0,2]:");
                rowLoc = input.nextInt();

                while (rowLoc >= ticTacToe.length || rowLoc < 0)
                {
                    System.out.println("Invalid input, please try again");
                    System.out.print("Enter co-ordinate of row in the range [0,2]:");
                    rowLoc = input.nextInt();
                }

                System.out.print("Enter co-ordinate of column in the range [0,2]:");
                colLoc = input.nextInt();
                System.out.println();

                while (colLoc >= ticTacToe[0].length || colLoc < 0)
                {
                    System.out.println("Invalid input, please try again");
                    System.out.print("Enter co-ordinate of column in the range[0,2]:");
                    colLoc = input.nextInt();
                    System.out.println();
                }

                while (ticTacToe[rowLoc][colLoc] == x || ticTacToe[rowLoc][colLoc] == o)
                {
                    System.out.println("Given co-ordinate has already been used, please give different co-ordinate");
                    System.out.print("Enter co-ordinate of row [0,2]:");
                    rowLoc = input.nextInt();

                    while (rowLoc >= ticTacToe.length || rowLoc < 0)
                    {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Enter co-ordinate of row [0,2]:");
                        rowLoc = input.nextInt();
                    }

                    System.out.print("Enter co-ordinate of column [0,2]:");
                    colLoc = input.nextInt();
                    System.out.println();

                    while (colLoc >= ticTacToe[0].length || colLoc < 0)
                    {
                        System.out.println("Invalid input, please try again");
                        System.out.print("Enter co-ordinate of column [0,2]:");
                        colLoc = input.nextInt();
                        System.out.println();
                    }
                }

                if (count % 2 == 0)
                {
                    ticTacToe[rowLoc][colLoc] = x;
                    player1Win = checkForWin(ticTacToe, rowLoc, colLoc, x);
                }
                else
                {
                    ticTacToe[rowLoc][colLoc] = o;
                    player2Win = checkForWin(ticTacToe, rowLoc, colLoc, o);
                }

                drawBoard(ticTacToe);

                if (!player1Win && !player2Win && count == (ticTacToe.length * ticTacToe[0].length) - 1)
                {
                    draw = true;
                }
                count++;
            }

            if (player1Win)
            {
                System.out.println("Player 1 wins\n");
                player1WinCount++;
            }
            else if (player2Win)
            {
                System.out.println("Player 2 wins\n");
                player2WinCount++;
            }
            else
            {
                System.out.println("Game is a draw\n");
                drawCount++;
            }
        }

        System.out.println("\nFinal Results");
        System.out.println("-----------------------------------");
        System.out.println("Number of games won by Player 1: " + player1WinCount);
        System.out.println("Number of games won by Player 2: " + player2WinCount);
        System.out.println("Number of games drawn:           " + drawCount);
        System.out.println("-----------------------------------");

        if (player1WinCount > player2WinCount)
        {
            System.out.println("Player 1 won the overall match");
        }
        else if (player2WinCount > player1WinCount)
        {
            System.out.println("Player 2 won the overall match");
        }
        else
        {
            System.out.println("Overall match is a draw");
        }
    }

    public static void drawBoard(char[][] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[0].length; j++)
            {
                if (j == a[0].length - 1)
                {
                    System.out.print("| " + a[i][j] + " | " + i);
                }
                else
                {
                    System.out.print("| " + a[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("  0   1   2\n");
    }
    
     /*
         When a user enters an X or O in a square, this method then checks if any 2 squares 
         contain an X (if the user entered an X) or O (if the user entered an O) to determine if the user has won or not.
         E.g. If a player draws an X on the bottom right corner, this method will check if the 2 squares
         above it, 2 squares to the right or the 2 diagonal squares contain X's
     */

    public static boolean checkForWin(char[][]a, int rowLoc, int colLoc, char symbol)
    {
        boolean win = false;

        if (rowLoc == 0)
        {
            if (a[rowLoc + 1][colLoc] == symbol && a[rowLoc + 2][colLoc] == symbol)
            {
                win = true;
            }
            else if (colLoc == 0)
            {
                if (a[rowLoc + 1][colLoc + 1] == symbol && a[rowLoc + 2][colLoc + 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc + 2] == symbol)
                {
                    win = true;
                }
            }
            else if (colLoc == 1)
            {
                if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc - 1] == symbol)
                {
                    win = true;
                }
            }
            else if (colLoc == 2)
            {
                if (a[rowLoc + 1][colLoc - 1] == symbol && a[rowLoc + 2][colLoc - 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc][colLoc - 1] == symbol && a[rowLoc][colLoc - 2] == symbol)
                {
                    win = true;
                }
            }
        }
        else if (rowLoc == 2)
        {
            if (a[rowLoc - 1][colLoc] == symbol && a[rowLoc - 2][colLoc] == symbol)
            {
                win = true;
            }
            else if (colLoc == 0)
            {
                if (a[rowLoc - 1][colLoc + 1] == symbol && a[rowLoc - 2][colLoc + 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc + 2] == symbol)
                {
                    win = true;
                }
            }
            else if (colLoc == 1)
            {
                if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc - 1] == symbol)
                {
                    win = true;
                }
            }
            else if (colLoc == 2)
            {
                if (a[rowLoc - 1][colLoc - 1] == symbol && a[rowLoc - 2][colLoc - 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc][colLoc - 1] == symbol && a[rowLoc][colLoc - 2] == symbol)
                {
                    win = true;
                }
            }
        }
        else
        {
            if (colLoc == 0)
            {
                if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc + 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc + 1][colLoc] == symbol && a[rowLoc - 1][colLoc] == symbol)
                {
                    win = true;
                }
            }
            else if (colLoc == 2)
            {
                if (a[rowLoc][colLoc - 1] == symbol && a[rowLoc][colLoc - 2] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc + 1][colLoc] == symbol && a[rowLoc - 1][colLoc] == symbol)
                {
                    win = true;
                }
            }
            else
            {
                if (a[rowLoc - 1][colLoc] == symbol && a[rowLoc + 1][colLoc] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc][colLoc + 1] == symbol && a[rowLoc][colLoc -1] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc + 1][colLoc + 1] == symbol && a[rowLoc - 1][colLoc - 1] == symbol)
                {
                    win = true;
                }
                else if (a[rowLoc - 1][colLoc + 1] == symbol && a[rowLoc + 1][colLoc - 1] == symbol)
                {
                    win = true;
                }
            }
        }
        return win;
    }
}
