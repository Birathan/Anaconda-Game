import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{
    Button singleButton = new Button("SingleButton.png");
    Button multiButton = new Button("multiButton.png");
    Image instMulti = new Image("multiplayerInstructions.png"); 
    Image instSingle = new Image("singlePlayerInstructions.png"); 
    GreenfootSound theme = new GreenfootSound("Theme.mp3");
    Image titleScreen = new Image("TitleScreen.png");
    // all the images and sounds
    int button;
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 900, 1); 
        menuScreen();
        theme.setVolume(40);
        theme.play();
    }

    public void menuScreen(){
        // add the single and multiplayer buttons, as well as the title scree
        addObject(titleScreen, 450, 450);
        addObject(singleButton,680,600);
        addObject(multiButton,280,600);
    }

    public void act(){
        //check which button is clicked
        //according to which button the user clicks
        //button = 1 when single player is clicked
        //button = 2 when multiplayer is clicked
        if(Greenfoot.mouseClicked(singleButton) == true){
            theme.stop();
            addObject(instSingle, 450, 450);
            button = 1;
        }
        if(Greenfoot.mouseClicked(multiButton) == true){
            theme.stop();
            addObject(instMulti, 450, 450);
            button = 2;
        }
        if((Greenfoot.isKeyDown("up"))&&(button==2)){
            startMultiGame();
        }
        if((Greenfoot.isKeyDown("up"))&&(button==1)){
            startSingleGame();
        }
    }

    public void startMultiGame(){
        //remove instructions and start multiplayer game
        removeObject(instMulti);
        Greenfoot.setWorld(new MultiplayerMode());
    }

    public void startSingleGame(){
        //remove instructions and start singleplayer mode
        removeObject(instSingle);
        Greenfoot.setWorld(new SinglePlayerMode());
    }
}
