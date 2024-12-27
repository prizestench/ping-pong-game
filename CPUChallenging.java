import java.awt.Color;

public class CPUChallenging extends CPUBasic {

    //CPU Challenging Paddle attributes
   public static final double CPU_CHALLENGING_PADDLE_LENGTH = 75.0;
   public static final double CPU_CHALLENGING_PADDLE_SPEED = 3.0;
   public static final Color CPU_CHALLENGING_PADDLE_COLOR = Color.orange; 
    
    //CONSTRUCTORS
    public CPUChallenging(){
        super(CPU_CHALLENGING_PADDLE_LENGTH, CPU_CHALLENGING_PADDLE_SPEED, CPU_CHALLENGING_PADDLE_COLOR);
    }

    public CPUChallenging(double length, double speed, Color color){
        super(length, speed, color);
    }

   
    //Determines how the paddle moves when the ball is moving left
    
    protected int reactBallMovingLeft(double bX, double bY, double bXVel, double bYVel){
        
        return super.reactBallMovingRight(bX, bY, bXVel, bYVel);

    }

    //Determines how the paddle moves when the ball is moving right

    protected int reactBallMovingRight(double bX, double bY, double bXVel, double bYVel){

        double paddleX = this.getX();
        double paddleY = this.getY();
        double expectY = TrigHelpers.calcTargetY(bX, bY, bXVel, bYVel, paddleX);

        if (Math.abs(paddleY - expectY) < MOVE_THRESHOLD)
            return MOVE_NEUTRAL;
        else if (paddleY < expectY)
            return MOVE_DOWN;
        else 
            return MOVE_UP;
        
    }
}
