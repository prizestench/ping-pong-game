import java.awt.Color;

//Basic CPU Paddle
//Logic is as follows:
// -if the ball is moving towards the Human player, move the paddle
//  to the center of the screen
// -if the ball is moving towards the CPU player, move the CPU paddle
//  up or down to match the ball's Y (within 10 pixels).
public class CPUBasic extends CPUPaddle{
   
   
   //CPU Basic Paddle attributes
   public static final double CPU_BASIC_PADDLE_LENGTH = 75.0;
   public static final double CPU_BASIC_PADDLE_SPEED = 3.0;
   public static final Color CPU_BASIC_PADDLE_COLOR = Color.yellow; 
         
   
   //****************   CONSTRUCTORS  *************
   public CPUBasic(){
      this(CPU_BASIC_PADDLE_LENGTH, CPU_BASIC_PADDLE_SPEED, CPU_BASIC_PADDLE_COLOR);
   }
   
   public CPUBasic(double length, double speed, Color color){ 
      super(length, speed, color);
   }  
   
   
   
   
   
   //Called automatically whenever EITHER the player or CPU scores a point.
   //argument boolean indicates if it was the CPU who scored (true) or
   //the human (false).
   public void pointScored(boolean didCPUScore){
      if (didCPUScore)
         this.score++;
   } 
   
   
   //Called automatically whenver the ball collides with this paddle
   public void ballVolleyed(){
      //This paddle doesn't react to a ball being volleyed
   }  
   
   
   
   
   
   //******************************************************************************
   //Determine how the paddle should move when the ball is moving left or right with 
   //the argument coordinates/velocities.
   //Returns -1, 0, or 1 if paddle should move up, neutral, or down, respectively.
   //
   //arguments include (in order):
   //bX, bY: the ball's current x and y coordinates
   //bXVel, bYVel: the ball's current x and y velocities  
   protected int reactBallMovingLeft(double bX, double bY, double bXVel, double bYVel){
      
      //implement me!
      double paddleY = this.getY();

      if (Math.abs(paddleY - getWindowHeight() / 2) < MOVE_THRESHOLD)
         return MOVE_NEUTRAL;
      else if (paddleY < getWindowHeight()/2)
         return MOVE_DOWN;
      else
         return MOVE_UP;

   }
   
   
   protected int reactBallMovingRight(double bX, double bY, double bXVel, double bYVel){
      
      double paddleY = this.getY();
      
      if (Math.abs(bY - paddleY) < MOVE_THRESHOLD)
         return MOVE_NEUTRAL;
      else if (paddleY < bY)
         return MOVE_DOWN;
      else
         return MOVE_UP;    
   }
   //******************************************************************************
   
   
   

   
}