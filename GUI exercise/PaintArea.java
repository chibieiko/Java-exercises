package com.sankari;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sankari on 10.11.2015.
 */

class PaintArea extends JPanel {
    private MyWindow myWindow;
    private ArrayList<Circle> cuties;

    public PaintArea(MyWindow myWindow) {
        //this.myWindow = myWindow;
        // Create an ArrayList to be able to insert as many circles there as the users wants.
        cuties = new ArrayList<>();
        // Add the first Circle to the window, it will be moving when user starts
        // the program.
        addCircle(20, 20);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Assign color, scale, location and size for circles in the ArrayList cuties.
        for (int a = 0; a < cuties.size(); a++){
            Circle c = cuties.get(a);
            g.setColor(c.getColor());
            int size = c.getScaledSize();
            g.fillOval(c.getCircleX(), c.getCircleY(), size, size);
        }
        repaint();
    }

    public void moveCircles() {
        // Go through the ArrayList cuties with for.
        for (int a = 0; a < cuties.size(); a++) {
            // Shorten cuties.get(a) to c, makes it easier to read and write code.
            Circle c = cuties.get(a);
            // Update circle's x and y coordinates by adding speed to location.
            c.setCircleX(c.getCircleX() + c.getSpeedX());
            c.setCircleY(c.getCircleY() + c.getSpeedY());
            // Check that the circles aren't escaping from the paintArea. Correct location and
            // change move destination if needed.
            if (c.getCircleX() < 0) {
                c.setSpeedX(-c.getSpeedX());
                c.setCircleX(0);
            } else if (c.getCircleX() + c.getScaledSize() > getWidth()) {
                c.setSpeedX(-c.getSpeedX());
                c.setCircleX(getWidth() - c.getScaledSize());
            }

            if (c.getCircleY() < 0) {
                c.setSpeedY(-c.getSpeedY());
                c.setCircleY(0);
            } else if (c.getCircleY() + c.getScaledSize() > getHeight()) {
                c.setSpeedY(-c.getSpeedY());
                c.setCircleY(getHeight() - c.getScaledSize());
            }
        }
    }
    // Creates and adds Circles to the ArrayList cuties.
    public void addCircle(int x, int y) {
        cuties.add(new Circle(x, y));
    }
    // Creates a scale for the circles. Is activated by the mouseWheelListener. Makes the
    // circles bigger or smaller according to wheel movement.
    public void scaleCircles(double d) {
        // The double 0.1 determines how fast the circles grow or get smaller, the smaller
        // the number the slower they change size. Basically requires more scrolling.
        d = d * 0.2;
        // Goes through all existing circles.
        for (int a = 0; a < cuties.size(); a++) {
            Circle c = cuties.get(a);
            // Set the new scale by adding d to the existing scale.
            c.setScale(c.getScale() + d);
        }
    }
}
