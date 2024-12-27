public class CreativeHuman extends HumanPaddle {
    
    public static final double MIN_PADDLE_LENGTH = 10;
    public static final double DECREASE_IN_LENGTH = 5;

    public CreativeHuman(){
        super(HUMAN_PADDLE_LENGTH, HUMAN_PADDLE_SPEED, HUMAN_PADDLE_COLOR);
    }

    //override ballVolleyed to decrease paddle length every time it collides with a ball
    public void ballVolleyed(){
        this.volleys += 1;
        if (getPaddleLength() > MIN_PADDLE_LENGTH + DECREASE_IN_LENGTH)
            setLength(getPaddleLength() - DECREASE_IN_LENGTH);
    }

    // override pointscored function to reset paddle length after a point is scored

    public void pointScored(boolean didHumanScore){
        if (didHumanScore){
            this.score++;       
        }
        this.volleys = 0;
        setLength(HUMAN_PADDLE_LENGTH);
    }
    
    
}
