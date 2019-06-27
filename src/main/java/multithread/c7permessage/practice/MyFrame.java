package multithread.c7permessage.practice;

import multithread.c6readwrite.MyReadWriteLock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by adimn on 2019/6/27.
 */
public class MyFrame extends JFrame  implements ActionListener{
    private Service service;
    public MyFrame(){
        super("MyFrame");
        service = new Service();
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new Label("ang thread per"));
        JButton jButton = new JButton("execute");
        getContentPane().add(jButton);
        jButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    service.service();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
