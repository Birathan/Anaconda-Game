import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnakeBody here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeDot extends Actor
{
    int x;
    int y;
    int speed = 6;
    int currentX;
    int currentY;
    String turn;
    int turnCoordinateX;
    int turnCoordinateY;
    public SnakeDot(int xCoordinate, int yCoordinate){
        x=xCoordinate;
        y=yCoordinate;
    }

    public void act() 
    {
        move();
    }   

    public void move(){
        if ("up".equals(turn)){
            up();
            turnCoordinateX();
            turnCoordinateY();
        }
        else if ("down".equals(turn)){
            down();
            turnCoordinateX();
            turnCoordinateY();
        }
        else if ("left".equals(turn)){
            leftTurn();
            turnCoordinateX();
            turnCoordinateY();
        }
        else if ("right".equals(turn)){
            rightTurn();
            turnCoordinateX();
            turnCoordinateY();
        }
        this.setLocation(x,y);
    }

    public void up(){
        y -= speed;
    }
    
    public boolean dotCollision(){
        if(this.getOneObjectAtOffset(0, 0, SnakeDot.class) != null){
            return true;
        }  
        else{return false;}
    }

    public void down(){
        y += speed;
    }

    public void rightTurn(){
        x += speed;
    }

    public void leftTurn(){
        x -= speed;
    }
    
    public void stop(){

    }
    
    public int currentX(){
        return this.getX();
    }

    public int currentY(){
        return this.getY();
    }

    public void turnCoordinateX(){
        turnCoordinateX = this.getX();
    }

    public void turnCoordinateY(){
        turnCoordinateY = this.getY();
    }
}