/*
	This package 
		1)controls the elements of this game, 
		2)draws the elements required for this game,
		3)manages the speed of this game, and
		4)handles events.
*/

package com.brickbreaker.game.util;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Game extends JPanel implements KeyListener, ActionListener{
	
	// Checks if the game has started or not.
	private boolean play = false;

	// Keeps track of the score.
	private int score = 0;

	// Total number of bricks displayed on the screen.
	private int totalBricks = 60;

	// Controls the speed of the game.	
	private Timer timer;
	private int delay = 10;

	// Stores the horizontal coordinate of the paddle.
	private int playerX = 220;

	// Stores the coordinates of the ball
	private int ballPositionX = 175;
	private int ballPositionY = 400;

	// Stores the direction in which the ball is moving. 
	private int ballXDirection = -1;
	private int ballYDirection = -2;
	private Board board;

	public Game() {
		board = new Board(6, 10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	// To draw the required elements on the screen.
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		board.draw((Graphics2D) g);
		g.setColor(Color.YELLOW);
		g.fillRect(691, 0, 3, 592);

		// To draw score at the top right corner of the screen.
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);

		// To draw the paddle.
		g.setColor(Color.YELLOW);
		g.fillRect(playerX, 550, 100, 8);

		// To draw the ball.
		g.setColor(Color.decode("#898989"));
		g.fillOval(ballPositionX, ballPositionY, 20, 20);

		// When paddle misses the ball.
		if(ballPositionY > 570) {
			play = false;
			ballXDirection = 0;
			ballYDirection = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			g.drawString("Game over! Score: " + score, 190, 300);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart...", 190, 340);
		}

		// When no brick is left on the screen.
		if(totalBricks == 0){
			play = false;
			ballYDirection = -2;
			ballXDirection = -1;
			g.setColor(Color.RED);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			g.drawString("Game over! Score: " + score, 190, 300);
			g.setFont(new Font("SansSerif", Font.BOLD, 30));
			g.drawString("Press Enter to Restart...", 190, 340);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		timer.start();
		if(play) {

			// Check if the ball hit the paddle. If it returns true, then the vertical direction of the ball is reversed.
			if(new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
				ballYDirection = -ballYDirection;
			}

			// Ensures that the the ball bounces off the bricks correctly and that the score is updated correctly. It checks if the ball has hit any bricks.
			for(int i = 0; i < board.row; i++) {
				for(int j = 0; j < board.column; j++){
					if(board.board[i][j] > 0) {
						int brickX = j * board.brickWidth + 80;
						int brickY = i * board.brickHeight + 50;
						int bricksWidth = board.brickWidth;
						int bricksHeight = board.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
						Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20, 20);
						Rectangle brickRect = rect;
						if(ballRect.intersects(brickRect)) {

							// Sets the value of the brick to 0 indicating that the brick has been destroyed.	
							board.setBrickValue(0, i, j);

							// Decrements the total number of bricks.
							totalBricks--;

							// Increases the score by 5.
							score += 5;

							// Reverses the direction of the ball's horizontal movement if the ball hit the brick on the left or right side.
							if(ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x + bricksWidth) ballXDirection = -ballXDirection;

							// Reverses the direction of the ball's vertical movement if the ball hit the brick on the top or bottom side.
							else ballYDirection = -ballYDirection;
							break;
						}
					}
				}
			}

			// Controls the movement of the ball.
			if(score < 50) {
				ballPositionX += ballXDirection;
				ballPositionY += ballYDirection;
			}

			if(score >= 50 && score < 100) {
				ballPositionX = (int)(ballPositionX + 1.5 * ballXDirection);
				ballPositionY = (int)(ballPositionY + 1.5 * ballYDirection);
			}
			else if(score >= 100 && score < 150) {
				ballPositionX = ballPositionX + 2 * ballXDirection;
				ballPositionY = ballPositionY + 2 * ballYDirection;
			}

			else if(score >= 150 && score < 200) {
				ballPositionX = (int)(ballPositionX + 2.5 * ballXDirection);
				ballPositionY = (int)(ballPositionY + 2.5 * ballYDirection);
			}

			else if(score >= 200) {
				ballPositionX = (int)(ballPositionX + 3 * ballXDirection);
				ballPositionY = (int)(ballPositionY + 3 * ballYDirection);
			}

			// Ensures that the ball does not go out of the screen.
			if(ballPositionX < 0 || ballPositionX > 670) ballXDirection = -ballXDirection;
			if(ballPositionY < 0) ballYDirection = -ballYDirection;
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {

			// Ensures that the paddle does not go out of the screen.
			if(playerX >= 600) playerX = 600;
			else moveRight();
		}
		if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {

			// Ensures that the paddle does not go out of the screen.
			if(playerX < 10) playerX = 10;
			else moveLeft();
		}

		// To start the game.
		if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				ballPositionX = 175;
				ballPositionY = 400;
				ballXDirection = -1;
				ballYDirection = -2;
				score = 0;
				playerX = 220;
				totalBricks = 60;
				board = new Board(6, 10);
				repaint();
			}

		}
	}

	// To move the paddle towards the right.
	public void moveRight() {
		play = true;
		playerX += 25;
	}

	// To move the paddle towards the left.
	public void moveLeft() {
		play = true;
		playerX -= 25;
	}

	@Override
	public void keyTyped(KeyEvent ke) {

	}

	@Override
	public void keyReleased(KeyEvent ke) {

	}
}