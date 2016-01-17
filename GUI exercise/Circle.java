package com.sankari;

import java.awt.*;
import javax.swing.*;

/**
 * Created by Sankari on 10.11.2015.
 */
public class Circle {
    // Qualities of the circle.
    private int speedX;
    private int speedY;
    private int circleX;
    private int circleY;
    private int size;
    private Color color;
    private double scale;
    // Gives the circle everything it needs, when a new Circle is created.
    public Circle(int locationX, int locationY) {
        calc();
        // Set initial scale to one, it doesn't affect the size with that value.
        setScale(1.0);
        // The location of the mouse.
        setCircleX(locationX);
        setCircleY(locationY);
    }
    // Calculates random values for speed, size and color.
    public void calc() {
        int rand = (int) (Math.random() * 4 + 1);
        int x = (int) (Math.random() * 8 + 1);
        int y = (int) (Math.random() * 8 + 1);
        if (rand == 1) {
            setSpeedX(x);
            setSpeedY(y);
        } else if (rand == 2) {
            setSpeedX(-x);
            setSpeedY(-y);
        } else if (rand == 3) {
            setSpeedX(x);
            setSpeedY(-y);
        } else if (rand == 4) {
            setSpeedX(-x);
            setSpeedY(y);
        }

        setSize((int) (Math.random() * 65 + 6));
        int colorR = (int) (Math.random() * 256);
        int colorG = (int) (Math.random() * 256);
        int colorB = (int) (Math.random() * 256);
        color = new Color(colorR, colorG, colorB);
    }
    // Returns the scaled size, which is scale + size.
    public int getScaledSize() {
        return (int) (getScale() * getSize());
    }

    public double getScale() {
        return scale;
    }
    // Sets the scale.
    public void setScale(double scale) {
        // Prevent the scale going too small and making the circles disappear.
        if (scale < 0.5) {
            this.scale = 0.5;
        // Check and assign a max scale to control how big the circles become.
        } else if (scale > 5) {
            this.scale = 5;
        } else {
            this.scale = scale;
        }
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCircleX() {
        return circleX;
    }

    public void setCircleX(int x) {
        circleX = x;
    }

    public int getCircleY() {
        return circleY;
    }

    public void setCircleY(int y) {
        circleY = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }
}
