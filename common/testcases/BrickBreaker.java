/*
	BrickBreaker class contains the entry point function.
	It creates a JFrame object and a Game object which is then added to the frame.
*/

import com.brickbreaker.game.util.*;
import javax.swing.JFrame;
class BrickBreaker {
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Brick Breaker Game");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setLocationRelativeTo(null);
	}
}