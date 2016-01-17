package com.sankari;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * Created by Sankari on 10.11.2015.
 */
class MyWindow extends JFrame implements MouseListener, MouseWheelListener {
    // Initiate variables here so they can be used in different methods.
    private PaintArea paintArea;
    private Fun thing;
    private Circle round;

    public MyWindow() {
        // Create objects here to give them some content
        paintArea = new PaintArea(this);
        thing = new Fun(paintArea);
        round = new Circle(20, 20);
        // Set a layout to the MyWindow.
        setLayout(new BorderLayout());
        // Add the paintArea and assign a place for it.
        add(paintArea, BorderLayout.CENTER);
        // create and start the Thread. Enables the first ball to move in the window
        // before the window has been clicked.
        Thread saie = new Thread(thing);
        saie.start();
        // Add Mouse listener.
        paintArea.addMouseListener(this);
        // Add mouse wheel listeners.
        paintArea.addMouseWheelListener(this);
        // Determine and set the size
        setSize(400, 400);
        // Set the window to visible
        setVisible(true);
        // Cuts the program, when the window is closed.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Adds circles to the paintArea when either left or right mouse button is clicked.
    @Override
    public void mouseClicked(MouseEvent e) {
        // Ask the position of the mouse and set x and y variables.
        int x = e.getX();
        int y = e.getY();
        System.out.println("x: " + x + " y: " + y);
        // Assign x and y as coordinates for circle.
        round.setCircleX(x);
        round.setCircleY(y);
        // Call the method addCircle to add circles to the paintArea.
        paintArea.addCircle(x, y);
        // Repaint the paintArea.
        paintArea.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    // Increases or decreases the size of the circles depending which way you scroll.
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // Call the method scaleCircles to adjust right size for the circles.
        paintArea.scaleCircles(e.getPreciseWheelRotation());
    }
}
