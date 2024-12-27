import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashSet;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;






//*****************************************************************************************      
//**                                                                                     **    
//**                                                                                     **    
//**                                                                                     **
//**                         ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿                          **
//**                         ⣿⣿⣿⣿⣿⣿⣿⠟⢁⣴⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⣦⡈⠻⣿⣿⣿⣿⣿⣿⣿                          **
//**                         ⣿⣿⣿⣿⣿⠟⢁⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⡈⠻⣿⣿⣿⣿⣿                          **
//**                         ⣿⣿⣿⠟⢁⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⡈⠻⣿⣿⣿                          **
//**                         ⣿⡟⢁⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⡈⢻⣿                          **
//**                         ⣿⡇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⢸⣿                          **
//**                         ⣿⡇⢸⡇⣠⡾⠿⠶⠆⠶⢶⣶⡶⠶⢀⣴⡾⠿⣶⣄⠀⣶⡶⠶⣶⣄⢸⡇⢸⣿                          **
//**                         ⣿⡇⢸⡇⠻⢷⣶⣦⡄⠀⠀⣿⡇⠀⢸⣿⠀⠀⢸⣿⠀⣿⣧⣤⣾⡟⢸⡇⢸⣿                          **
//**                         ⣿⡇⢸⡇⢤⣤⣠⣽⡏⠀⠀⣿⡇⠀⠘⢿⣦⣠⣾⠟⠀⣿⡇⠀⠀⠀⢸⡇⢸⣿                          **
//**                         ⣿⡇⢸⡇⠀⠈⠉⠁⠀⠀⠀⠉⠀⠀⠀⠀⠈⠉⠀⠀⠀⠈⠁⠀⠀⠀⢸⡇⢸⣿                          **
//**                         ⣿⣧⡈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢁⣼⣿                          **
//**                         ⣿⣿⣿⣦⡈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢁⣴⣿⣿⣿                          **
//**                         ⣿⣿⣿⣿⣿⣦⡈⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢁⣴⣿⣿⣿⣿⣿                          **
//**                         ⣿⣿⣿⣿⣿⣿⣿⣦⡈⠻⠶⠶⠶⠶⠶⠶⠶⠶⠶⠶⠟⢁⣴⣿⣿⣿⣿⣿⣿⣿                          **
//**                         ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿                          **      
//**                                                                                     **
//**                        DO NOT MODIFY ANY CODE IN THIS FILE                          **
//**                                                                                     **
//** ADDITIONALLY, YOU DO NOT NEED TO TRACE, UNDERSTAND, OR CALL ANY CODE IN THIS FILE!  **
//**     (but you're welcome and encouraged to take a look if you're interested!)        **
//**                                                                                     **
//**                                                                                     **        
//*****************************************************************************************














//Handles the graphical code (and some minor game logic aspects) for the JPong game
//Additional Ball class included at the bottom of the file (to reduce number of classes
//students need to deal with).
public class JPong extends JComponent implements KeyListener{ 
   
   
   
   
   
   //Constants related to game timing
   //target frames rendered per second
   private static final int TARGET_FPS = 60;  
   //number of nano seconds in 1 second
   private static final long NANOS_IN_SECOND = 1000000000; 
   //Ideal time (in nano secs) to wait between rendering frames per the target FPS
   private static final long TARGET_FRAMETIME = NANOS_IN_SECOND / TARGET_FPS;      
   //The default game speed, represented as a percentage (100 = 100% speed)
   private static final int DEFAULT_GAME_SPEED = 100;
   //Maximum that the game speed can be increased to
   //(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
   private static final int MAX_GAME_SPEED = 300; 
   //Interval that the speed changes when pressing speed up/down keys
   private static final int SPEED_CHANGE_INTERVAL = 10;   
   
   //Various keybindings
   public static final int KEY_SPEED_DOWN = KeyEvent.VK_MINUS;//make game run slower
   public static final int KEY_SPEED_UP = KeyEvent.VK_EQUALS;//make game run faster 
   public static final int KEY_MOVE_UP = HumanPaddle.KEY_MOVE_UP;
   public static final int KEY_MOVE_DOWN = HumanPaddle.KEY_MOVE_DOWN;
   
   
   
   //Dimensions of windows (including borders, actual window space will vary per OS)  
   public static final int WINDOW_WIDTH = 800;
   public static final int WINDOW_HEIGHT = 500; 
   public static final int WINDOW_PADDING = 10;
   
   //Width of paddles
   public static final int PADDLE_WIDTH = 12;  
   //Padding (distance between paddles and edge of window
   public static final int PADDING = PADDLE_WIDTH * 3;   
   
   //Size of squares used when displaying debug trajectories
   public static final int DEBUG_HEIGHT = 10;   
   
   //Colors for ball and debug square
   public static final Color BG_COLOR = Color.WHITE;
   public static final Color ASSIST_COLOR = Color.RED;
   
   //Window where the entirety of the game is rendered
   private JFrame window;
   
   //Paddles for human (p1) and CPU (p2)
   private HumanPaddle p1;
   private CPUPaddle p2;
   
   //Tracks the System.nanoTime() of the last completed render, used in the game timing
   private long lastPaintTime;   
   
   //The single ball Object displayed in the game window
   private Ball b;
   
   //a set tracking all of the keys currently being pressed
   private HashSet<Integer> keysPressed;
   
   //tracks if the ball is in the neutral position (center of screen with no velocity)
   //waiting to be "launched" via the space bar key
   private boolean waitingForLaunch = true;   
   
   //Current game speed, as percentage (ex: 150 = 150% speed, ie 50% faster than normal)
   private int gameSpeed = DEFAULT_GAME_SPEED;   
   
   //tracks if the window is currently painting, forcing the game logic to wait
   private static boolean isPainting; 
   
   //tracks if the window is currently retrieving key presses, forcing the game logic to wait
   private static boolean isProcessingKeys;   
   
   
   
   
   //Accepts the two paddle types to be used in the game
   //Game is always 1 x Human (left paddle, p1) vs 1 x CPU (right paddle, p2)
   public JPong(HumanPaddle p1, CPUPaddle p2){
      super();
      initLogic(p1, p2);       
      initWindow();     
   }
   
   
   
   //initialize/assign logic (i.e. instance variables)
   public void initLogic(HumanPaddle p1, CPUPaddle p2){
      isPainting = false;
      isProcessingKeys = false;
      lastPaintTime = System.nanoTime();    
      keysPressed = new HashSet<Integer>();
      this.p1 = p1;
      this.p2 = p2;
   }
   
   
   //Initializes the window and containing JFrame
   //contains (most of) the gross Java GUI code
   public void initWindow() {
      
      window = new JFrame("");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setSize(JPong.WINDOW_WIDTH + WINDOW_PADDING, JPong.WINDOW_HEIGHT + WINDOW_PADDING);
      window.add(this);
      
      window.setBackground(BG_COLOR);
      
      
      //Fix Windows listener bug(?)
      this.setFocusable(true);
      //window.requestFocus();
      this.requestFocusInWindow();
      
      
      this.setOpaque(true);
      window.setVisible(true);
      window.setResizable(false);
      
      updateWindowTitle(); //set initial title text
      
      //Lets the window know that the methods to react to keyboard/mouse actions
      //are implemented in this class
      addKeyListener(this);  
      
      //initilalize the location/dimensions for the paddles and ball 
      //(they can be drawn since we now have a window to render them in!)
      this.p1.initPaddle(this.getWindowWidth(), this.getWindowHeight(), PADDING);
      this.p2.initPaddle(this.getWindowWidth(), this.getWindowHeight(), this.getWidth() - PADDING - PADDLE_WIDTH);      
      this.b = new Ball(this.getWidth() / 2, this.getHeight() / 2);  
   }  
   
   
   
   //Starts the game
   public void playGame(){          
      
      //go forever (game doesn't have an end condition)
      //one iteration of this loop is one frame of the game render
      while(true){
         //handle paddle/ball movement and collisions
         movePaddles();         
         moveBall();    
         //check if someone scored
         checkForScore();
         //update title text
         updateWindowTitle();
         //lastly, redraw the game window and everything in it
         refresh();
      }      
   }
   
   //redraw the game window after everything's been updated for hte current frame
   public void refresh(){
      repaint(); 
      adjustFrameTiming();//ensures the game renders at a fixed 60 times per second.
   }
   
   
   //Handles moving both the Player and CPU paddles, called on every update of the game
   private void movePaddles(){
      isProcessingKeys = true;
      try{
         HashSet<Integer> tempPressed = new HashSet<Integer>(keysPressed);
         //feed each currently pressed key to the Human's keyPressed(...)
         
         for (int key : tempPressed){
            p1.movePaddle(p1.reactToKey(key));
            
            //if the key is a movement key, leave it in the keysPressed set until it is released
            //otherwise, remove it (so a single key press doesn't register many times)
            if (key != KEY_MOVE_UP && key != KEY_MOVE_DOWN)
               keysPressed.remove(key);
         }
         
      }
      catch(RuntimeException re){            
         //just in case we get a one-in-a-million race condition between retrieving/fetching keys
      }  
      isProcessingKeys = false;     
      //only one call needed for the CPU paddle (figure out its next move per the ball's state)
      p2.movePaddle(p2.calcMove(b.getX(), b.getY(), b.getXVel(), b.getYVel()));
   }
   
   
   
   //updates the state of the ball on each refresh of the game
   public void moveBall(){
      //track the old position, this will be used for collision detection
      double oldX = b.getX();
      double oldY = b.getY();
      //move the ball and check for wall collisions
      b.move();       
      checkForWallBounce();
      //if the ball is elegible to be volleyed (per cooldown), check for Paddle collisions
      if (b.isEligibleForVolley())
         checkPaddleCollisions(oldX, oldY, b.getX(), b.getY());      
   }   
   
   
   //Checks to see if the ball has collided with either p1 or p2 paddle and 
   //updates the ball's state accordingly
   public void checkPaddleCollisions(double bX1, double bY1, double bX2, double bY2){
      //generate hitboxes for the ball and paddles
      Rectangle2D p1HitBox = makePaddleHitbox(p1.getX(), p1.getY(), p1.getPaddleLength());  
      Rectangle2D p2HitBox = makePaddleHitbox(p2.getX(), p2.getY(), p2.getPaddleLength());
      
      double ballDThresh = b.getBallDiameter() / 2.0;
      if (bX1 < bX2)
         bX2 += (2 * ballDThresh);
      else
         bX1 += (2 * ballDThresh);  
      
      //Generate two hitboxes, tracking the ball's vector (from its old position on the previously
      //rendered frame, to its current position.
      //One hit box is drawn from the topmost points of the old/new ball locations, the other from the bottommost.
      Line2D bUpperHitBox = new Line2D.Double(bX1, bY1-ballDThresh, bX2, bY2-ballDThresh);
      Line2D bLowerHitBox = new Line2D.Double(bX1, bY1+ballDThresh, bX2, bY2+ballDThresh);
      
      //check if either ball hit box collides w/ each Paddle and react accordingly.
      if (bUpperHitBox.intersects(p1HitBox) || bLowerHitBox.intersects(p1HitBox)){
         p1.ballVolleyed();
         b.handlePaddleCollision(p1.getY(), p1.getPaddleLength());
      }
      else if (bUpperHitBox.intersects(p2HitBox) || bLowerHitBox.intersects(p2HitBox)){//check for collision w/ CPU
         p2.ballVolleyed();
         b.handlePaddleCollision(p2.getY(), p2.getPaddleLength());
      }    
   }
   
   
   //Generates a hitbox for either paddle  
   private Rectangle2D makePaddleHitbox(double x, double y, double height){
      return new Rectangle2D.Double(x, y - (height/2), PADDLE_WIDTH, height);    
   }
   
   
   
   //Checks to see if ball has collided with top or bottom of window
   private void checkForWallBounce(){ 
      //if a collision has occured, move the ball's y back in the window
      //by the same amount it has overshot the window by and negate its y velocity
      if (b.getY() <= 0.0){
         b.setY(-b.getY());
         b.invertYSpeed();
      }  
      else if (b.getY() >= this.getWindowHeight()){
         b.setY(this.getWindowHeight() - (b.getY() - this.getWindowHeight()));
         b.invertYSpeed();         
      }
   }
   
   
   //Check to see if either player has scored (ball has gone off left or right edge of window)
   private void checkForScore(){
      //if the ball has gone of either left/right edge of window, a score has occurred
      if (b.getX() <= 0 || b.getX() >= this.getWindowWidth()){
         boolean didP1Score = b.getX() >= this.getWindowWidth(); //is P1 the paddle that scored?
         p1.pointScored(didP1Score);
         p2.pointScored(!didP1Score);
         //move ball back to center of screen to be relaunched via spacebar press
         b.resetBall();
         waitingForLaunch = true;
      }        
   }
   
   
   // rerenders the game window
   // ran when repaint() is called (on each tick of the timer)
   public void paintComponent(Graphics g) {
      JPong.isPainting = true;
      super.paintComponent(g);
      Graphics2D g2D = (Graphics2D)g;
      g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
      
      //redraw the background (necessary for Windows or else you get cascading effect)
      drawBackground(g);
            
      //draw paddles and ball
      drawPaddle(p1.getPaddleColor(), p1.getX(), p1.getY(), p1.getPaddleLength(),  g2D);
      drawPaddle(p2.getPaddleColor(), p2.getX(), p2.getY(), p2.getPaddleLength(),  g2D);
      
      drawBall(b, g2D);
      //if debug mode is enabled in launcher, draw the debug hitboxes
      if (Launcher.ASSIST_MODE && b.getXVel() != 0)
         drawAssist(g2D);
      JPong.isPainting = false;
   }  
   
   //Fill the game window with the designated background color
   //Necessary for Windows machines -- otherwise the window will render a black background
   //and you will get a cascading effect on anything that animates in the game window.
   private void drawBackground(Graphics g){
      
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());            
   }
   
   
   //Draws the assist indicators that show future ball wall collisions and projected y trajectory
   //when it reaches respective paddle
   public void drawAssist(Graphics2D g2D){
      g2D.setPaint(ASSIST_COLOR);
      double bX = b.getX();
      double bY = b.getY();
      double bXVel = b.getXVel();
      double bYVel = b.getYVel();
      Paddle targetPaddle = p1;
      if (bXVel > 0)
         targetPaddle = p2;
      //keep calculating bounce locations until we've passed the CPU Paddle
      while(true){
         double bounceX = TrigHelpers.calcNextWallBounceX(bX, bY, bXVel, bYVel, this.getWindowHeight());
         if (bounceX < targetPaddle.getX() && targetPaddle == p1) //this bounce is past the Human Paddle.
            break;
         else if (bounceX > targetPaddle.getX() && targetPaddle == p2) //this bounce is past the CPU Paddle.
            break;
         //determine the y coord of the ball when the bounce occurs per its last yVelocity
         if (bYVel > 0)
            bY = this.getWindowHeight();
         else
            bY = 0;
         
         bYVel *= -1;//bounce inverts y velocity
         bX = bounceX;
         //draw a square at this bounce location
         g2D.fill(new Rectangle2D.Double(bX, bY - (DEBUG_HEIGHT/2), DEBUG_HEIGHT, DEBUG_HEIGHT));                                    
      }    
      //draw one final square at the location the ball will be when it reaches the CPU Paddle.
      double finalY = TrigHelpers.calcTargetY(bX, bY, bXVel, bYVel, targetPaddle.getX());
      g2D.fill(new Rectangle2D.Double(targetPaddle.getX(), finalY - (DEBUG_HEIGHT/2), DEBUG_HEIGHT, DEBUG_HEIGHT));    
   }
   
   
   
   //Updates the text on the top menu bar of the game window
   public void updateWindowTitle(){
      window.setTitle("JPong!   |   Score: " + p1.getScore() + " to " + p2.getScore() + "   |   Player Volleys: " + p1.getVolleys() + "   |   Speed: " + this.gameSpeed + "%");
   }
   
   
   //Draws a single paddle with the specified attributes (either Human or CPU)
   private void drawPaddle(Color c, double x, double y, double height, Graphics2D g2D){  
      g2D.setPaint(c);
      Rectangle2D shape = makePaddleHitbox(x, y, height);
      g2D.fill(shape);
      g2D.setPaint(Color.black);
      g2D.draw(shape);      
   }
   
   
   //Draws the ball to the screen
   private void drawBall(Ball b, Graphics2D g2D){
      g2D.setPaint(b.getBallColor());    
      Ellipse2D shape = new Ellipse2D.Double(b.getX()-b.getBallDiameter()/2.0, b.getY()-b.getBallDiameter()/2.0, b.getBallDiameter(), b.getBallDiameter());
      g2D.fill(shape);
      g2D.setPaint(Color.black);
      g2D.draw(shape);    
   }
   
   
   //Adjusts the game timing to ensure that it doesn't run too fast or slow
   //Makes sure the game maintains its target frames per second (FPS)
   //1 frame = 1 "redraw" of the game window
   public void adjustFrameTiming(){
      //Wait for the game to finish painting
      while(isPainting){
         sleep(100);
      }
      //Figure out how much longer we need to wait to hit our target frametime
      //ex: if we want 60 fps, we want to make sure there's a 1/60 second delay
      //between each frame painted
      long target = (TARGET_FRAMETIME * 100 / gameSpeed);
      long delta = System.nanoTime() - lastPaintTime;
      long delayNeeded = (target - delta);
      sleep(delayNeeded);
      
      //log the current system time for the next frame's timing
      lastPaintTime = System.nanoTime(); 
   }    
   
   
   //Forces the execution of the program to stop and wait
   //For the specified number of nano seconds
   private void sleep(long nanosToSleep){
      if (nanosToSleep <= 0)
         return;
      long start = System.nanoTime();
      while (System.nanoTime() - start < nanosToSleep){
         try {
            Thread.sleep(0, 5000);
         } 
         catch(Exception e) { 
            //shouldn't ever reach here, but try/catch is necessary due to 
            //Java's implementation of Thread.sleep function
         }
      }
   }     
   
   
   //Called automatically whenver a keyboard key is depressed per the key listener
   public void keyPressed(KeyEvent e) {       
      //'Esc' quits the game
      if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
         System.exit(0);  
      //If the ball is waiting to be launched, launch it when space is pushed
      else if (e.getKeyCode()==KeyEvent.VK_SPACE && waitingForLaunch){
         b.launchBall();          
         waitingForLaunch = false;
      }
      //speed up/slow down the game, within allowable thresholds
      else if (e.getKeyCode() == KEY_SPEED_DOWN)
         gameSpeed = Math.max(SPEED_CHANGE_INTERVAL, gameSpeed - SPEED_CHANGE_INTERVAL);
      else if (e.getKeyCode() == KEY_SPEED_UP)
         gameSpeed = Math.min(MAX_GAME_SPEED, gameSpeed + SPEED_CHANGE_INTERVAL);
      
      //If not one of the reserved keys, add to the keysPressed set to be passed
      //on to the HumanPaddle's reactToKey(...) on the next game update.
      else{
         //Wait for the game to finish retrieving keys pressed
         while(isProcessingKeys){
            sleep(100);
         }           
         keysPressed.add(e.getKeyCode());
      }
   }    
   
   
   //Called autmomatically whenever a key is released (ie lifts back up after a depress)
   public void keyReleased(KeyEvent e) { 
      //remove the released key from the set of currently pressed keys
      //(Ensures smooth movement for arrow keys).
      keysPressed.remove(e.getKeyCode());      
   }  
   
   
   //Not used, needed to satisfy KeyListener interface...
   public void keyTyped(KeyEvent event) {  }    
   
   
   //accessors for window dimensions (not really necessary, but why not)
   public double getWindowHeight(){
      return this.getHeight();
   }
   
   public double getWindowWidth(){
      return this.getWidth();
   }      
}


//Abstraction of the ball and its respective attributes in the game window
//Put inside this file to give students one fewer file to worry about
class Ball{
   
   //Fixed Diameter/Color of the ball
   public static final int BALL_DIAMETER = 10;
   public static final Color BALL_COLOR = Color.magenta;
   
   //The minimum number of frames between volleys (prevent false double collisions)
   private static final int VOLLEY_COOLDOWN_FRAMES = 6;    
   
   //Constant total net velocity (used in paddle-ball deflection math)
   private static final double BALL_VELOCITY = 8.0;
   //Maximum deflection angle when a ball hits a paddle (used in paddle-ball deflection math)
   private static final double MAX_BOUNCE_ANGLE = Math.toRadians(65);
   
   
   
   
   //coordinate and velocity fields for ball
   private double x, y, xSpeed, ySpeed, startingX, startingY;
   //number of frames remaining until the ball can be volleyed again
   private int volleyCooldown;
   
   
   
   public Ball(float startingX, float startingY){
      this.x = startingX;
      this.y = startingY;
      this.startingX = startingX;
      this.startingY = startingY;       
      this.xSpeed = 0.0;
      this.ySpeed = 0.0;
      this.volleyCooldown = 0;
   }
   
   
   //Resets the ball to center of screen
   //Called when game is first launched or following either player scoring   
   public void resetBall(){
      resetBall(this.startingX, this.startingY);
   }   
   
   public void resetBall(double resetX, double resetY){
      this.x = resetX;    
      this.y = resetY; 
      this.xSpeed = 0.0;
      this.ySpeed = 0.0;
   }
   
   
   //Launches the ball from its starting position with an initial, randomized velocity/direction
   public void launchBall(){
      //determine random angle, then calculate x/y velocities accordingly per net velocity
      double launchAngle = (Math.random() * (MAX_BOUNCE_ANGLE * 2)) - MAX_BOUNCE_ANGLE;
      this.ySpeed = Math.sin(launchAngle) * BALL_VELOCITY;
      this.xSpeed = Math.cos(launchAngle) * BALL_VELOCITY;
      //determine if ball randomly starting moving left or right
      if (Math.random() >= 0.5)
         this.xSpeed *= -1;
   }
   
   
   //Called each time the game updates... moves the ball per its current velocities
   public void move(){
      this.x += xSpeed;
      this.y += ySpeed;
      if (this.volleyCooldown > 0)
         this.volleyCooldown--; 
   }  
   
   
   
   //Called whenever ball collides with either human or CPU paddle 
   public void handlePaddleCollision(double paddleCenterY, double paddleHeight){
      
      double yDiff = this.getY() - paddleCenterY;
      //Gets a normalized vector between -1.0 and 1.0 depending on where vertically on the paddle
      //the collision occured (dead center would be 0.0, topmost edge would be 1.0, bottommost -1.0)
      double normalizedYDiff = Math.max(-1.0, Math.min(1.0, yDiff / (paddleHeight / 2.0))); 
      double bounceAngle = normalizedYDiff * MAX_BOUNCE_ANGLE;
      //trig is happening (oh dear...)
      this.ySpeed = Math.sin(bounceAngle) * BALL_VELOCITY;
      this.xSpeed = Math.cos(bounceAngle) * BALL_VELOCITY * -Math.signum(this.xSpeed);
      //reset the volley cooldown, so ball can't be volleyed for a certain number of frames
      //prevents the ball from being volleyed multiple times in scenarios with weird angles/velocities
      this.volleyCooldown = VOLLEY_COOLDOWN_FRAMES;
   }
   
   
   //checks if balls is eligible to be volleyed (or is on cooldown)
   public boolean isEligibleForVolley(){
      return this.volleyCooldown <= 0;
   }
   
   
   
   //****************   ACCESSOR METHODS  *************    
   
   public double getX(){
      return this.x;
   }
   
   public double getY(){
      return this.y + (this.getBallDiameter() / 2);
   }
   
   public double getXVel(){
      return this.xSpeed;
   }
   
   public double getYVel(){
      return this.ySpeed;
   }  
   
   public double getBallDiameter(){
      return BALL_DIAMETER;
   }
   
   public Color getBallColor(){   
      return BALL_COLOR;
   }
   
   //************************************************  
   
   
   
   
   //****************   MUTATOR METHODS  *************  
   
   public void setY(double newY){
      this.y = newY;
   }
   
   public void setX(double newX){
      this.x = newX;
   }    
   
   public void invertYSpeed(){
      this.ySpeed *= -1;
   }
   
   //************************************************               
}