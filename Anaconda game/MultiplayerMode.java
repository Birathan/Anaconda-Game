import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class MultiplayerMode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MultiplayerMode extends World
{
    SnakeDot [] snakePlayer1 = new SnakeDot[200];
    SnakeDot [] snakePlayer2 = new SnakeDot[200];
    int frontDotPlayer1 = 0;
    int lastDotPlayer1 = snakePlayer1.length-1;
    int frontDotPlayer2 = 0;
    int lastDotPlayer2 = snakePlayer2.length-1;
    Image player1 = new Image("Player1win.png"); 
    Image player2 = new Image("Player2wins.png"); 
    Button replay = new Button("ReplayButton.png");
    Button retur = new Button("ReturnMenu.png");
    GreenfootSound death = new GreenfootSound("Death.mp3");
    boolean stop = false;
    public MultiplayerMode()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 900, 1); 
        //run methods, to prepare game
        player1Body();
        player2Body();
        //set the game speed
        Greenfoot.setSpeed(50);
        death.setVolume(30);
    }

    public void act(){
        //methods that will always be running
        player1();
        player2();
    }

    public void player1Body(){
        //add all the SnakeDots for player1 to the world
        for (int counter = 0; counter < snakePlayer1.length; counter++){
            snakePlayer1[counter] = new SnakeDot(400,450);
            addObject(snakePlayer1[counter], 400, 450);
        }
        //the player 1 snake starts off by going left
        snakePlayer1[frontDotPlayer1].turn = "left";
    }

    public void player2Body(){      
        //add all the SnakeDots for player2 to the world
        for (int counter = 0; counter < snakePlayer2.length; counter++){
            snakePlayer2[counter] = new SnakeDot(500,450);
            addObject(snakePlayer2[counter], 500, 450);
        }
        //the player 2 snake starts off by going right
        snakePlayer2[frontDotPlayer2].turn = "right";
    }

    public void player1(){
        //controls for the front SnakeDot of player1
        //same concept as single player
        if (Greenfoot.isKeyDown("w")){
            if(snakePlayer1[frontDotPlayer1].turn != "down"){
                snakePlayer1[frontDotPlayer1].turn = "up";
            }
        }
        else if (Greenfoot.isKeyDown("s")){
            if(snakePlayer1[frontDotPlayer1].turn != "up"){
                snakePlayer1[frontDotPlayer1].turn = "down";
            }
        }
        else if (Greenfoot.isKeyDown("a")){
            if(snakePlayer1[frontDotPlayer1].turn != "right"){
                snakePlayer1[frontDotPlayer1].turn = "left";
            }
        }
        else if(Greenfoot.isKeyDown("d")) {
            if(snakePlayer1[frontDotPlayer1].turn != "left"){
                snakePlayer1[frontDotPlayer1].turn = "right";
            }
        }
        //if player 1 touches the edge of the screen, he loses
        if((snakePlayer1[frontDotPlayer1].getX()>870)||(snakePlayer1[frontDotPlayer1].getX()<30)){
            addObject(player2,450,450);
            death(); 
        }
        if((snakePlayer1[frontDotPlayer1].getY()> 870)||(snakePlayer1[frontDotPlayer1].getY()<30)){
            addObject(player2,450,450);
            death();
        }
        moveBody1();        
    }

    public void player2(){
        //controls for the front SnakeDot of player2
        //same concept as single player
        if (Greenfoot.isKeyDown("up")){
            if(snakePlayer2[frontDotPlayer2].turn != "down"){
                snakePlayer2[frontDotPlayer2].turn = "up";
            }
        }
        else if (Greenfoot.isKeyDown("down")){
            if(snakePlayer2[frontDotPlayer2].turn != "up"){
                snakePlayer2[frontDotPlayer2].turn = "down";
            }
        }
        else if (Greenfoot.isKeyDown("left")){
            if(snakePlayer2[frontDotPlayer2].turn != "right"){
                snakePlayer2[frontDotPlayer2].turn = "left";
            }
        }
        else if(Greenfoot.isKeyDown("right")) {
            if(snakePlayer2[frontDotPlayer2].turn != "left"){
                snakePlayer2[frontDotPlayer2].turn = "right";
            }
        }
        //if player 2 touches the edge of the screen, he loses
        if((snakePlayer2[frontDotPlayer2].getX()> 870)||(snakePlayer2[frontDotPlayer2].getX()<30)){
            addObject(player1,450,450);
            death(); 
        }
        if((snakePlayer2[frontDotPlayer2].getY()>870)||(snakePlayer2[frontDotPlayer2].getY()<30)){
            addObject(player1,450,450);
            death();
        }
        moveBody2();
    }

    public void moveBody1(){
        //for player1
        //every dot(excluding the first one), will follow the dot in front of it
        //once a dot reaches the same turn coordinate as the dot in front of it, it will make the turn
        for (int counter = frontDotPlayer1; counter < lastDotPlayer1; counter++){
            if(snakePlayer1[counter].turn == "right"){                  
                if((snakePlayer1[counter].currentX())-30 == snakePlayer1[counter+1].currentX()){
                    snakePlayer1[counter+1].turn = "right";    
                    dotCollision();
                }
            }
            if(snakePlayer1[counter].turn == "left"){                  
                if((snakePlayer1[counter].currentX())+30 == snakePlayer1[counter+1].currentX()){
                    snakePlayer1[counter+1].turn = "left";                
                }
            }
            if(snakePlayer1[counter].turn == "down"){                  
                if((snakePlayer1[counter].currentY())-30 == snakePlayer1[counter+1].currentY()){
                    snakePlayer1[counter+1].turn = "down";                
                }
            }
            if(snakePlayer1[counter].turn == "up"){                  
                if((snakePlayer1[counter].currentY())+30 == snakePlayer1[counter+1].currentY()){
                    snakePlayer1[counter+1].turn = "up";                
                }
            }
        }
    }

    public void moveBody2(){        
        //for player2
        //every dot(excluding the first one), will follow the dot in front of it
        //once a dot reaches the same turn coordinate as the dot in front of it, it will make the turn
        for (int counter = frontDotPlayer2; counter < lastDotPlayer2; counter++){
            if(snakePlayer2[counter].turn == "right"){                  
                if((snakePlayer2[counter].currentX())-30 == snakePlayer2[counter+1].currentX()){
                    snakePlayer2[counter+1].turn = "right";                
                }
            }
            if(snakePlayer2[counter].turn == "left"){                  
                if((snakePlayer2[counter].currentX())+30 == snakePlayer2[counter+1].currentX()){
                    snakePlayer2[counter+1].turn = "left";  
                    dotCollision();
                }
            }
            if(snakePlayer2[counter].turn == "down"){                  
                if((snakePlayer2[counter].currentY())-30 == snakePlayer2[counter+1].currentY()){
                    snakePlayer2[counter+1].turn = "down";                
                }
            }
            if(snakePlayer2[counter].turn == "up"){                  
                if((snakePlayer2[counter].currentY())+30 == snakePlayer2[counter+1].currentY()){
                    snakePlayer2[counter+1].turn = "up";                
                }
            }
        }        
    }

    public void dotCollision(){
        //if the front snakeDot of player1 has collided with any other snakeDots, player2 wins
        if(snakePlayer1[frontDotPlayer1].dotCollision() == true){
            addObject(player2,450,450);
            death();        
        }
        //if the front snakeDot of player2 has collided with any other snakeDots, player1 wins
        else if(snakePlayer2[frontDotPlayer2].dotCollision() == true){
            addObject(player1,450,450);
            death(); 
        }
    }

    public void death(){
        player1 = new Image("blank.png"); 
        player2 = new Image("blank.png"); 
        //play sound when someone dies
        death.play();
        //add the buttons to replay or return to menu
        addObject(replay,450,500);
        addObject (retur, 450, 600);
        //if replay button is clicked
        if(Greenfoot.mouseClicked(replay) == true){
            death.stop(); 
            //remove the buttons
            removeObject(replay);
            removeObject(retur);
            //start a new multiplayer mode to replay
            Greenfoot.setWorld(new MultiplayerMode());
        }
        //if return to menu button is clicked
        else if(Greenfoot.mouseClicked(retur) == true){
            death.stop(); 
            //remove buttons
            removeObject(replay);
            removeObject(retur);
            //set a new menu to return to menu
            Greenfoot.setWorld(new Menu());
        }
    }
}