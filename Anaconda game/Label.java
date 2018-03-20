import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    int number = 0;
    String text1;
    java.awt.Color color1; 
    java.awt.Color bg1;
    public Label(String text, java.awt.Color color, java.awt.Color bg){
        text1 = text;
        color1 = color;
        bg1 = bg;
    }

    public void act() 
    {   setImage(new GreenfootImage(text1 + " " + number, 24, Color.BLACK, null));
    }    
}
