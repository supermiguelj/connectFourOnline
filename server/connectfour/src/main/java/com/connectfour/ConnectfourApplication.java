package com.connectfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.connectfour")
public class ConnectfourApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectfourApplication.class, args);

		int[][] board = new int[6][7];
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
				board[i][j] = -1;
		}

		int turn = 1;
		int player = 1;
		boolean winCon = false;

		while (winCon == false && turn < 43)
		{
			boolean validPlay = false;
			int playedColumn;

			// display grid + get input(put in playedColumn)

			for (int row = 6; row > -1; row--)
			{
				if(board[row][playedColumn] != -1)
				{
					board[row][playedColumn] = player;
					break;
				}
			}

			winner = determineWinner(player, board);

			if(player == 1)
				player++;
			else
				player--;

			turn++;
		}

		if(winner == true)
			{
				if(player == 1)
				// display for player who went first
				else
				// display for player who went second
			}
			else
				// display for tie

	}

	public static boolean validate(int column, int [][] board)
	{
		// if they can somehow(?) click outside column, will return false

		if (board[5][column] != -1)
			return false;

		return true;
	}

	public static boolean determineWinner(int player, int [][] board)
	{
		//Checks for horizontal win condition
		for(int row = 0; row < board.length; row++)
		{
			for (int col = 0;col < board[0].length - 3;col++)
			{
				if (board[row][col] == player   && 
					board[row][col+1] == player &&
					board[row][col+2] == player &&
					board[row][col+3] == player)
					return true;
			}			
		}

		//Checks for vertical win condition
		for(int row = 0; row < board.length - 3; row++)
		{
			for(int col = 0; col < board[0].length; col++)
			{
				if (board[row][col] == player   && 
					board[row+1][col] == player &&
					board[row+2][col] == player &&
					board[row+3][col] == player)
					return true;
			}
		}

		//Checks for one diagonal win condition
		for(int row = 3; row < board.length; row++)
		{
			for(int col = 0; col < board[0].length - 3; col++)
			{
				if (board[row][col] == player   && 
					board[row-1][col+1] == player &&
					board[row-2][col+2] == player &&
					board[row-3][col+3] == player)
					return true;
			}
		}

		//Checks other diagonal win condition
		for(int row = 0; row < board.length - 3; row++)
		{
			for(int col = 0; col < board[0].length - 3; col++)
			{
				if (board[row][col] == player   && 
					board[row+1][col+1] == player &&
					board[row+2][col+2] == player &&
					board[row+3][col+3] == player)
					return true;
			}
		}

		return false;
	}
}
