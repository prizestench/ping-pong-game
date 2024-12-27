//A collection of static helper functions that perform
//some trig calculations for you.  Will be very helpful
//in implementing your CPU logic!
//***Do not modify these functions***
//(but feel free to add additional functions of your own!)
public class TrigHelpers {

   // Given a ball at the specified coordinates/velocity, this function returns
   // the y coordinate that the ball will be at when it reaches the CPUPaddle
   // DOES NOT ACCOUNT FOR WALL BOUNCES. If the ball would bounce before it reaches
   // the
   // paddle's x, this function will return a y < 0 or > window height
   //
   // arguments include (in order):
   // bX, bY: the ball's current x and y coordinates
   // bXVel, bYVel: the ball's current x and y velocities
   // paddleX: the x coordinate of the CPU paddle
   //
   // *** You do not need to modify (or totally understand the math) of this
   // function ***
   public static double calcTargetY(double bX, double bY, double bXVel, double bYVel, double paddleX) {
      double expectY = ((paddleX - bX) / bXVel) * bYVel;
      expectY = bY + expectY;
      return expectY;
   }

   // Returns the X coordinate of the next wall bounce, given the ball's current
   // location and trajectory
   // this does not account for the window dimensions, thus if the ball would not
   // hit a wall before reaching
   // the CPU paddle, it would return an X > the paddle's x coordinate
   // ***ASSUMES THE BALL IS MOVING RIGHT, RETURNS -1 OTHERWISE***
   //
   // arguments include (in order):
   // bX, bY: the ball's current x and y coordinates
   // bXVel, bYVel: the ball's current x and y velocities
   // windowHeight: the height, in pixels, of the game window
   //
   // *** You do not need to modify (or totally understand the math) of this
   // function ***
   public static double calcNextWallBounceX(double bX, double bY, double bXVel, double bYVel, double windowHeight) {

      double yDiff = 0;
      if (bYVel < 0)
         yDiff = bY;
      else
         yDiff = windowHeight - bY;
      double bounceX = yDiff / Math.abs(bYVel);
      return (bounceX * bXVel) + bX;
   }

}