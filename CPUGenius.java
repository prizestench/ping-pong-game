import java.awt.Color;

public class CPUGenius extends CPUChallenging {

    //CPU Challenging Paddle attributes
   public static final double CPU_GENIUS_PADDLE_LENGTH = 25.0;
   public static final double CPU_GENIUS_PADDLE_SPEED = 3.0;
   public static final Color CPU_GENIUS_PADDLE_COLOR = Color.pink; 
    
    //CONSTRUCTORS
    public CPUGenius(){
        super(CPU_GENIUS_PADDLE_LENGTH, CPU_GENIUS_PADDLE_SPEED, CPU_GENIUS_PADDLE_COLOR);
    }

    public CPUGenius(double length, double speed, Color color){
        super(length, speed, color);
    }

    //Determines how the paddle moves when the ball is moving left
    protected int reactBallMovingLeft(double bX, double bY, double bXVel, double bYVel){
        //moves the same way as CPUChallenging
        return super.reactBallMovingLeft(bX, bY, bXVel, bYVel);
    }

    protected int reactBallMovingRight(double bX, double bY, double bXVel, double bYVel){
        
        double bounceX = TrigHelpers.calcNextWallBounceX(bX, bY, bXVel, bYVel, getWindowHeight());
        //if the calculated bounceX is greater the width of the window(i.e x of paddle), then the ball will not bounce
        if (bounceX > this.getX())
            return super.reactBallMovingRight(bX, bY, bXVel, bYVel);

        //looping until a value for bounceX > PaddleX (i.e the ball reaches the volleying area) is found
        while (bounceX <= this.getX()){
            bX = bounceX; 
            if(bYVel < 0){
                bY = 0; bYVel *= -1;
            }
            else {   
                bY = this.getWindowHeight(); bYVel *= -1;
            }
            bounceX = TrigHelpers.calcNextWallBounceX(bX, bY, bXVel, bYVel, getWindowHeight());
        } 
        return super.reactBallMovingRight(bX, bY, bXVel, bYVel);



    }

    
}
