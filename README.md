Brick Breaker is a 1 player video game. The code for this project is divided into 3 classes that work together to implement this game.
    1) Board class: This class is responsible to create the bricks, and assigning the bricks a code so that the bricks can be drawn on the screen with different colours.
    2) Game class: This class draws the elements required for this game, manages these elements with the help of event handlers, manages the speed of the game with the help of the Timer class, and enacts the rules of this game.
    3) BrickBreaker class: The main function or the entry point function defined inside this class creates the JFrame object and the Game class object and adds the Game class object to the JFrame object.

    The program works as follows: 

           1) The player presses any of the arrow keys to start the game.

           2) Press the left and right arrow keys to control the paddle so that the ball bounces back and hits the bricks.

           3) Bricks disappear after the ball hits the bricks and the score increases.

           4) If the ball misses the paddle or the ball hits all the bricks, the game ends and the score is displayed on the screen.

           5) Press ENTER key to start a new game.

 

Algorithm of Brick Breaker game:

           1) Initialize the game board with some bricks, a paddle and a ball.

           2) Start the game.

           3) if(ball hits the paddle) reverse the vertical direction of the ball;

           4) if(ball hits a brick)

               {

                        (total number of bricks)--;

                        score = score + c;

                        if(ball hits the brick on its left or right side) reverse the horizontal direction of the ball;

                        else if(ball hits the brick on the top or bottom side) reverse the vertical direction of the ball;

                }

           5) Update the position of the paddle and the ball.

           6) if(ball misses the paddle) end the game;

           7) if(total number of bricks == 0) end the game;

