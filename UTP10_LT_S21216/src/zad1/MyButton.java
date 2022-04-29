package zad1;

import javax.swing.*;

public class MyButton extends JButton {
    boolean isRunning;
    int buttonID;

    public MyButton(int buttonID, String text) {
        super(text);
        this.setActionCommand("Start");
        this.buttonID = buttonID;
        isRunning = false;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


}
