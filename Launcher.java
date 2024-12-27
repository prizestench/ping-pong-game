
public class Launcher {

  // Set to true to enable debug mode
  // Debug mode will draw RED SQUARES to show the approximate
  // locations of each time the ball will hit a wall and where
  // it will be when it reaches the paddle.
  public static final boolean ASSIST_MODE = false;

  // Run this to launch the JPong Game!
  public static void main(String[] args) {

    // Change these two values to test your different paddles!
    HumanPaddle p1 = new HumanPaddle();
    

    CPUPaddle p2 = new CPUBasic();
    CPUPaddle p3 = new CPUChallenging();
    CPUPaddle p4 = new CPUGenius();

    CreativeHuman p0 = new CreativeHuman();
    CPUPaddle p5 = new CreativeCPU(p0);
    // *******************************************************

    JPong game = new JPong(p1, p3);

    game.playGame();
    

  }

}