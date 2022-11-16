//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**The class Sprite is an abstract class and is the superclass of the classes
 * Explosion, Gem, Helicopter, Ring and Rocket.*/

public abstract class Sprite {
    protected Bitmap[] image; /**image is an array of Bitmaps, used in Sprite's subclasses to hold Bitmaps for the subclasses' animations.*/
    protected int xCoordinate, yCoordinate, frameCounter, width, height; /**Multiple integers used in Sprite's subclasses.*/

    public int getxCoordinate() {
        return xCoordinate;
    } /**The method getxCoordinate is used to retrieve the int xCoordinate.*/
    public int getyCoordinate(){
        return yCoordinate;
    } /**The method getyCoordinate is used to retrieve the int yCoordinate.*/
    public Bitmap getImage(){
        return image[frameCounter];
    } /**The method is used to get a Bitmap in the array image, with index equal to the variable frameCounter.*/
    public int getWidth(){
        return width;
    } /**The method getWidth is used to retrieve the int width.*/
    public int getHeight(){
        return height;
    } /**The method getHeight is used to retrieve the int height.*/
    public Rect getRectangle(){
        return new Rect(xCoordinate, yCoordinate, xCoordinate+width, yCoordinate+height);
    } /**The method getRectangle is used to create and return a Rectangle with the same coordinates and size as the object.
     This method is necessary to check for collisions between instances of the subclasses of the class Sprite.*/
}

