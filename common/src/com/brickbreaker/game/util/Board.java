/*
	This package is
		1)responsible for creating, and 
		2)managing the bricks in the brick breaker game.
*/

package com.brickbreaker.game.util;
import java.awt.*;
public class Board {
	public int board[][];
	public int brickWidth;
	public int brickHeight;
	public int row, column;
	public Board(int r, int c) {
		row = r;
		column = c;
		board = new int[row][column];

		// Assigns a code to every brick to give it different colour.
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) setBrickValue(getCode(), i, j);
		}

		brickWidth = 540/column;
		brickHeight = 150/row;
	}

	// Function to get random numbers that can be alloted to every brick.
	public int getCode() {
		int random = (int)(Math.random()*7);
		return random;
	}

	// Allots different colour to bricks based on the code they are allotted.
	public Color brickColour(int code) {
		Color bColor = Color.decode("#000000");
		if(code == 1) bColor = Color.decode("#FF0000");
		else if(code == 2) bColor = Color.decode("#FF5C00");
		else if(code == 3) bColor = Color.decode("#FFE500");
		else if(code == 4) bColor = Color.decode("#52FF00");
		else if(code == 5) bColor = Color.decode("#00E0FF");
		else if(code == 6) bColor = Color.decode("#6100FF");
		return bColor;
	}

	// Draws the bricks on the screen.
	public void draw(Graphics2D g2D) {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(board[i][j] > 0) {
					g2D.setColor(brickColour(board[i][j]));
					g2D.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					g2D.setStroke(new BasicStroke(3));
					g2D.setColor(Color.BLACK);
					g2D.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}

	// Sets the value passed to it, to the specified brick.
	public void setBrickValue(int value, int row, int column) {
		board[row][column] = value;
	}

}