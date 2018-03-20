import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dot extends Actor
{
    public void act() 
    {

    }    
    
    public boolean collision(){
        if(this.getOneObjectAtOffset(0, 0, SnakeDot.class) != null){
            return true;
        }  
        else{return false;}
    }
}