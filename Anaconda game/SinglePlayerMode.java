import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class SnakeWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SinglePlayerMode extends World
{
    UserInfo myInfo = UserInfo.getMyInfo();
    SnakeDot [] dots = new SnakeDot[100];
    Label level = new Label("Level:", Color.BLACK, null);
    Label score = new Label("Score:", Color.BLACK, null);
    Label highscore = new Label("Highscore:", Color.BLACK, null);
    Image over = new Image("game_over.jpg");
    Button replay = new Button("ReplayButton.png");
    Button retur = new Button("ReturnMenu.png");
    Dot dot = new Dot();
    GreenfootSound ding = new GreenfootSound("Ding Sound Effect.wav");
    GreenfootSound death = new GreenfootSound("Death.mp3");
    int x;
    int y;
    int lastDot = 95;
    int frontDot = 99;
    int xLast;
    int yLast;
    int speed = 50;
    //ATTENTION ATTENTION ATTENTION READ HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //MAKE SURE TO UNDERSTAND THE DIFFERENCE BETWEEN A DOT, AND A SNAKE DOT
    //A DOT IS THE RED DOT THAT GIVES YOU POINTS
    //A SNAKE DOT IS A DOT THAT MAKES THE SNAKE(BLUE DOT)
    //OTHER THEN THAT, THERE SHOULD BE NOTHING CONFUSING ABOUT MY COMMENTING
    //HAVE FUN PLAYING MY GAME!!!!!!!!
    public SinglePlayerMode()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        //snake body
        SnakeBody();
        //score and highscore
        addObject(score,60,40);
        addObject(highscore,85,80);
        score.number = 0;
        highscore.number =  myInfo.getScore();
        //dot
        addObject(dot, 400, 700);
        //level indicator
        addObject(level,57, 120);
        //set starting speed for game
        Greenfoot.setSpeed(speed);
        ding.setVolume(30);
        death.setVolume(30);
    }

    public void SnakeBody(){
        //prepare the body of the snake 
        //the last snakedot starting at (100,400) and every other snakeDot is 30 units in front of the previous snakeDot
        int x = 100;
        int y = 400;
        for (int counter = lastDot; counter < dots.length; counter++){            
            dots[counter] = new SnakeDot(x,y);
            addObject (dots[counter], x, y);
            dots[counter].turn = "right";
            x += 30;
        }
    }

    public void Levels (){
        //every 5 points, you are in the next level, and the speed increases
        if(score.number % 5== 0){
            level.number ++;
            //speed++;
            //Greenfoot.setSpeed(speed);
        }
    }

    public void act(){
        //use the arrow keys to control the front SnakeDot
        if (Greenfoot.isKeyDown("up")){
            if(dots[frontDot].turn != "down"){
                dots[frontDot].turn = "up";
            }
        }
        if (Greenfoot.isKeyDown("down")){
            if(dots[frontDot].turn != "up"){
                dots[frontDot].turn = "down";
            }
        }
        if (Greenfoot.isKeyDown("left")){
            if(dots[frontDot].turn != "right"){
                dots[frontDot].turn = "left";
            }
        }
        if(Greenfoot.isKeyDown("right")) {
            if(dots[frontDot].turn != "left"){
                dots[frontDot].turn = "right";
            }
        }
        //you die when you touch the limits of the world
        if((dots[frontDot].getX()== 870)||(dots[frontDot].getX()==30)){
            death();
        }
        if((dots[frontDot].getY()== 870)||(dots[frontDot].getY()==30)){
            death();
        }
        //methods
        moveBody();
        checkDotCollision();
        checkCollision();
    }

    public void checkDotCollision(){
        //if the front dot has collided with any other dot, you die
        if(dots[frontDot].dotCollision() == true){
            death();
        }
    }

    public void moveBody(){
        //every snakeDot(excluding the first one), will follow the snakeDot in front of it
        //once a snakeDot reaches the same turn coordinate as the snakeDot in front of it, it will make the turn
        for(int counter = frontDot; counter > lastDot; counter--){
            if(dots[counter].turn == "up"){
                if(dots[counter].turnCoordinateX == dots[counter-1].currentX()){
                    dots[counter-1].turn = "up";
                }
            }
            if(dots[counter].turn == "down"){
                if(dots[counter].turnCoordinateX == dots[counter-1].currentX()){
                    dots[counter-1].turn = "down";
                }
            }
            if(dots[counter].turn == "left"){
                if(dots[counter].turnCoordinateY == dots[counter-1].currentY()){
                    dots[counter-1].turn = "left";
                }
            }
            if(dots[counter].turn == "right"){
                if(dots[counter].turnCoordinateY == dots[counter-1].currentY()){
                    dots[counter-1].turn = "right";
                }
            }
        }
    }

    public void checkCollision(){
        //when you collect a dot, your score will go up and a snakeDot will be added at the end of the snake
        //and another dot will spawn randomly on the screen(to be collected again)
        if (dot.collision() == true){
            removeObject(dot);
            score.number ++;
            Levels();
            //play sound every time you get a dot
            ding.stop();
            ding.play();
            //add the snakeDot to the end of the snake depending on the current turn of the snakeDot in front of it
            if(dots[lastDot].turn == "up"){                
                xLast = dots[lastDot].currentX();
                yLast = dots[lastDot].currentY()+30;
            }
            if(dots[lastDot].turn == "down"){                
                xLast = dots[lastDot].currentX();
                yLast = dots[lastDot].currentY()-30;
            }
            if(dots[lastDot].turn == "left"){                
                xLast = dots[lastDot].currentX()+30;
                yLast = dots[lastDot].currentY();
            }
            if(dots[lastDot].turn == "right"){                
                xLast = dots[lastDot].currentX()-30;
                yLast = dots[lastDot].currentY();
            }
            //This added snakeDot will now be the "lastDot"
            lastDot--;
            dots[lastDot] = new SnakeDot(xLast,yLast);
            addObject (dots[lastDot], xLast, yLast);
            //as soon as this snakeDot is added to the world, it will imitate the turn of the snakeDot in front of it
            dots[lastDot].turn = dots[lastDot+1].turn;
            //spawns another dot randomly on the screen
            x = Greenfoot.getRandomNumber(750) + 30;
            y = Greenfoot.getRandomNumber(750) + 30;
            addObject(dot, x, y);
            //if the score is higher than the highscore, the highscore will change, otherwise it will stay the same
            if (score.number > highscore.number){
                highscore.number = score.number;
            }
        }
    }

    public void death (){
        //play a sound when you die
        death.play();
        //create a game over menu with buttons
        addObject(over,450,450); 
        addObject(replay,450,500);
        addObject (retur, 450, 600);
        //what to do when either the replay or the return to menu button is clicked
        if(Greenfoot.mouseClicked(replay) == true){
            //stop the sound if it is still playing
            death.stop();
            //remove the buttons and the background
            removeObject(over); 
            removeObject(replay);
            removeObject(retur);
            //make a new singleplayer mode to replay
            Greenfoot.setWorld(new SinglePlayerMode());
        }
        if(Greenfoot.mouseClicked(retur) == true){
            //stop the sound if it is still playing
            death.stop();
            //remove the buttons and the background
            removeObject(over); 
            removeObject(replay);
            removeObject(retur);
            //make a new menu, to return to menu
            Greenfoot.setWorld(new Menu());
        }
        //store the new score, if it is larger then highscore, it will be the new highscore.
        int newScore = score.number;
        if (UserInfo.isStorageAvailable()) {            
            if (newScore > myInfo.getScore()) {
                myInfo.setScore(newScore);
                highscore.number = newScore; 
                myInfo.store();  // write back to server
            }
        }
    }
}