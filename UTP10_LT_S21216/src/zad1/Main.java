package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main implements ActionListener {

    int buttonCount = 1;
    int k = 0;
    JTextArea ta = new JTextArea(27,0);
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    JFrame window = new JFrame("Thread pool");
    List<JButton> buttonList = new ArrayList<>();

    public void showGui() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(500,500));

        top.setLayout(new GridLayout(0, 1));
        bottom.setLayout(new GridLayout(1, 0));

        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("Stop");
        stopButton.addActionListener(this);
        JButton createButton = new JButton("Create new");
        createButton.setActionCommand("CreateButton");
        createButton.addActionListener(this);
        buttonList.add(stopButton);
        buttonList.add(createButton);

        JScrollPane scroll = new JScrollPane(ta);
        top.add(stopButton);
        top.add(createButton);

        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        window.add(mainPanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            Method m = this.getClass().getDeclaredMethod("task" + cmd);
            m.invoke(this);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }


    class MainTask implements Callable<Integer> {
        private int threadNum, limit;

        public MainTask(int threadNum){
            this.threadNum = threadNum;
            limit = 1000;
        }

        @Override
        public Integer call() throws Exception {
            Random random = new Random();
            int sum = 0;
            while (sum < limit){
                synchronized (this) {
                    while (isSuspended) {
                        this.wait();
                    }
                }
                if(Thread.currentThread().isInterrupted()) return null;

                int randomNum = random.nextInt(100) + 1;
                sum+=randomNum;
                ta.append("Thread " + threadNum + "(limit = " + limit + "): "
                        + randomNum + ", sum = " + sum + '\n');
                Thread.sleep(1000);
            }
            ta.append("Thread: " + threadNum + " Done!" + '\n');
            return sum;
        }
    }

    Future<Integer> task;
    ExecutorService exec = Executors.newCachedThreadPool();

    public void taskStart() {
        try {
            task = exec.submit(new MainTask(++k));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void taskCancel() {task.cancel(true);}

    public void taskStop() {
        exec.shutdownNow();
        ta.append("All stopped\n");
        for (JButton b : buttonList)
            b.setEnabled(false);
    }


    boolean isSuspended = false;
    public void taskSuspend() {
        isSuspended = true;
    }

    public void taskContinue() {
        isSuspended = false;
        synchronized (this) {
            notify();
        }
    }


    public void taskCreateButton(){
        MyButton button = new MyButton(buttonCount, "T" + buttonCount);
        button.addActionListener(this);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setRunning(!button.isRunning);
                if (button.isRunning) {
                    button.setActionCommand("Suspend");
                    button.setText("Suspend T" + button.buttonID );
                }
                else{
                    button.setActionCommand("Continue");
                    button.setText("Continue T" + button.buttonID);
                }
            }
        });
        buttonCount++;
        buttonList.add(button);
        bottom.add(button);
        window.validate();
        window.repaint();
    }



    public static void main(String[] args) {
        Main main = new Main();
        main.showGui();
    }


}
