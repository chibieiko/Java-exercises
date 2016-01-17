package com.sankari;

/**
 * Created by Sankari on 10.11.2015.
 */
public class Fun implements Runnable {

    private PaintArea paintArea;

    public Fun(PaintArea paintArea) {
        this.paintArea = paintArea;
    }
    // Calls the method moveCircles for forever making the circles move inside the
    // paint.Area until the program is quit.
    @Override
    public void run() {
        while(true) {
            paintArea.moveCircles();
            try {
                // Determines the refresh rate.
                Thread.sleep(20);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}