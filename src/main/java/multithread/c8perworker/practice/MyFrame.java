package multithread.c8perworker.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adimn on 2019/7/2.
 */
public class MyFrame extends JFrame implements ActionListener {
    private final JLabel jLabel = new JLabel("event dispatching thread");
    private final JButton button = new JButton("countUp");

    public MyFrame(){
        super("angFrame");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(jLabel);
        getContentPane().add(button);
        button.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            countUp();
        }
    }

    private void countUp(){
        new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " invoke begin " );
                for (int i = 0; i < 10; i++) {
                    final String myname = i+"";
                    final  Runnable executor = new Runnable() {
                        public void run() {
                            System.out.println(Thread.currentThread().getName()+" begin "+myname);
                            jLabel.setText(myname);
                            System.out.println(Thread.currentThread().getName()+" end "+myname);
                        }
                    };
                    SwingUtilities.invokeLater(executor);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " invoke end " );
            }
        }.start();

    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+" begin");
        new MyFrame();
        System.out.println(Thread.currentThread().getName()+" end");
    }
}
