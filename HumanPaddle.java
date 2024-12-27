import java.awt.Color;
import java.awt.event.*;

//A human controlled JPong paddle
public class HumanPaddle extends Paddle{
   
   //Human Paddle attributes
   public static final double HUMAN_PADDLE_LENGTH = 75.0;
   public static final double HUMAN_PADDLE_SPEED = 6.0;
   public static final Color HUMAN_PADDLE_COLOR = Color.green;  
   
   //Key binds for if the Paddle should move up or down
   public static final int KEY_MOVE_UP = KeyEvent.VK_UP;
   public static final int KEY_MOVE_DOWN = KeyEvent.VK_DOWN;   
   
   //the count of times this Paddle has successfully deflected the ball
   //resets when a point is scored by either Paddle.
   protected int volleys;
   
   
   //****************   CONSTRUCTOR  *************
   public HumanPaddle(){
      super(HUMAN_PADDLE_LENGTH, HUMAN_PADDLE_SPEED, HUMAN_PADDLE_COLOR);
      this.volleys = 0;
   }  
   public HumanPaddle(double length, double speed, Color color){
      super(length, speed, color);
      this.volleys = 0;
   }
   
   
   //called on each game update for every key that is pressed on the keyboard
   //Returns one of three int values, telling the game that, per the key being 
   //pressed, if the Paddle should move up, down, or not move at all (neutral)
   //
   //Specifically, -1, 0, and 1 correspond to moving up, neutral, and down respectively
   public int reactToKey(int keyPressed){
      if (keyPressed == KEY_MOVE_UP)
         return Paddle.MOVE_UP;
      else if (keyPressed == KEY_MOVE_DOWN)
         return Paddle.MOVE_DOWN;
      else
         return 0;
   }
      
   
   //Called automatically whenver the ball collides with this paddle
   public void ballVolleyed(){
      this.volleys += 1;
   }  
   
   
   //Called automatically whenever EITHER the player or CPU scores a goal.
   //argument boolean indicates if it was the human who scored (true) or
   //the CPU (false).
   public void pointScored(boolean didHumanScore){
      if (didHumanScore){
         this.score++;
      }
      this.volleys = 0;
   }
   
   
   public int getVolleys(){
      return volleys;
   }        
   
}