import java.awt.Color;

//A JPong Paddle
public abstract class Paddle{
   
   //Minimums
   private static final double MIN_PADDLE_LENGTH = 5;
   private static final double MIN_PADDLE_SPEED = 1;  
   
   //Helpful values for the various CPU paddles' reactToKey(...) and calcMove(...) methods
   protected static final int MOVE_THRESHOLD = 10;  
   protected static final int MOVE_UP = -1;
   protected static final int MOVE_DOWN = 1;
   protected static final int MOVE_NEUTRAL = 0;
   
   
   //Color to draw the Paddle  
   protected Color color;
   
   //The number points this paddle has scored
   protected int score;  
   
   //Length (i.e. height) of the Paddle in pixels
   private double length;
   
   //The number of pixels this paddle moves as a result of  
   //reactToKey(...) or calcMove(...) returning UP or DOWN.
   private double speed;   
   
   //The location of the centermost point of this Paddle
   private double x, y;
   
   //tracks if this Paddle has been initialized
   private boolean initialized = false;   
   
   //the height and width of the window this Paddle is contained inside
   private double windowWidth, windowHeight;
   
   
   
   //****************   CONSTRUCTOR  *************
   public Paddle(double length, double speed, Color color){    
      this.score = 0;
      this.color = color;
      this.length = Math.max(length, MIN_PADDLE_LENGTH);
      this.speed = Math.max(speed, MIN_PADDLE_SPEED);
   }  
   
   
   
   
   //called once at the very start of the game
   //relays the dimensions of the containing window, and also sets the Paddle's initial position
   public void initPaddle(double windowWidth, double windowHeight, double startingX){
      
      if (!this.initialized){//can only be called once, at the start of the game...
         this.windowWidth = windowWidth;
         this.windowHeight = windowHeight;
         this.x = startingX;
         this.y = windowHeight/2.0;     
         this.initialized = true;//initPaddle(...) can't be called again
      }
   }
   
   //Moves the paddle up, down, or not at all, per the int arg passed.
   //Specifically, -1, 0, and 1 correspond to moving up, neutral, and down respectively
   public void movePaddle(int direction){
      if (direction > 1 || direction < -1)
         throw new IllegalArgumentException("Paddle.movePaddle(...) requires a direction arg between -1 and 1 inclusive!");
      this.y += this.speed * direction; 
      this.y = Math.max(this.length/2.0, Math.min(this.windowHeight-this.length/2.0, this.y));
   }
   
   
   
   
   //****************   ABSTRACT METHODS  *************
   
   //Called automatically whenver the ball collides with this paddle
   public abstract void ballVolleyed();
   
   
   //Called automatically whenever EITHER paddle scores a point.
   //argument boolean indicates if it was the human who scored (true) or
   //the CPU (false).
   public abstract void pointScored(boolean didThisPaddleScore);
   
   
   
   
   
   
   //****************   ACCESSOR METHODS  *************
   
   
   public double getWindowHeight(){
      return this.windowHeight;
   }
   
   public double getWindowWidth(){
      return this.windowWidth;
   }
   
   public double getX(){
      return this.x;
   }  
   
   //Gets the y coordinate of the centermost point of the Paddle
   public double getY(){
      return this.y;
   } 
   
   //Gets the y coordinate of the topmost point of the Paddle  
   public double getTopY(){
      return this.y - this.length / 2;
   }  
   
   //Gets the y coordinate of the bottommost point of the Paddle
   public double getBottomY(){
      return this.y + this.length / 2;
   }       
   
   public int getScore(){
      return this.score;
   }  
   
   public Color getPaddleColor(){   
      return this.color;
   }
   
   public double getPaddleLength(){
      return this.length;    
   }  
   
   
   //****************   MUTATOR METHODS  *************
     
   protected void setSpeed(double newSpeed){
      this.speed = Math.max(newSpeed, MIN_PADDLE_SPEED);
   }

   protected void setLength(double newLength){
      this.length = Math.max(newLength, MIN_PADDLE_LENGTH);
   }   
          
   
}