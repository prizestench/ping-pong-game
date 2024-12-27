import java.awt.Color;

//A CPU controlled JavaPong paddle
public abstract class CPUPaddle extends Paddle{
   
   
   
   //****************   CONSTRUCTOR  *************
   public CPUPaddle(double length, double speed, Color color){ 
      super(length, speed, color);
   }  
   
   
   
   //determines if the Paddle should move up, down, or stay in its current position
   //Returns one of three int values, telling the game that, per the key being 
   //pressed, if the Paddle should move up, down, or not move at all (neutral) 
   //
   //arguments include (in order):
   //bX, bY: the ball's current x and y coordinates
   //bXVel, bYVel: the ball's current x and y velocities
   public int calcMove(double bX, double bY, double bXVel, double bYVel){
      if (bXVel > 0)
         return reactBallMovingRight(bX, bY, bXVel, bYVel);
      else
         return reactBallMovingLeft(bX, bY, bXVel, bYVel);
   }
         
   
   //****************   ABSTRACT METHODS  *************
   
   //helpers used by calcMove(...) to determine the Paddle's move when ball is travelling left/right
   //like calcMove(...), returns one of three int values to dictate move up, down, nor eutral
   protected abstract int reactBallMovingLeft(double bX, double bY, double bXVel, double bYVel);
   protected abstract int reactBallMovingRight(double bX, double bY, double bXVel, double bYVel);  
   
   
   //Called automatically whenver the ball collides with this paddle
   public abstract void ballVolleyed();
   
   
   //Called automatically whenever EITHER paddle scores a point.
   //argument boolean indicates if it was the human who scored (true) or
   //the CPU (false).
   public abstract void pointScored(boolean didCPUScore);
   
   
   
}