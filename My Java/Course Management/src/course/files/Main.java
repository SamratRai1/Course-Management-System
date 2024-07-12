package course.files;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Main implements ActionListener{

    private JButton teh, std, admin,exit;
    private JFrame frame;

    void startGUI() {

        frame = new JFrame("HCMS");
        frame.setBackground(Color.gray);

        std = new JButton();
        std.setBounds(40,120,180,50);
        std.addActionListener(this);
        std.setText("Students");
        std.setFocusable(false);

        frame.add(std);

        teh= new JButton();
        teh.setBounds(250,120,180,50);
        teh.setText("Teachers");
        teh.addActionListener(this);
        teh.setFocusable(false);

        frame.add(teh);

        admin = new JButton();
        admin.setBounds(150,30,180,50);
        admin.setText("Administrator");
        admin.addActionListener(this);
        admin.setFocusable(false);

        frame.add(admin);
        exit = new JButton();
        exit.setBounds(150,210,180,50);
        exit.setText("Exit");
        exit.addActionListener(this);
        exit.setFocusable(false);

        frame.add(exit);
        frame.setSize(500,320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == std) {

            frame.dispose();
            LogSignin log = new LogSignin("Student");
            log.userInputs();


        }

        if(e.getSource() == teh) {

            frame.dispose();
            LogSignin log = new LogSignin("Teacher");
            log.userInputs();

        }

        if(e.getSource() == admin) {

            frame.dispose();
            LogSignin log = new LogSignin("Admin");
            log.userInputs();

        }

        if(e.getSource() == exit){
            frame.dispose();
        }

    }


    public static void main(String[] args) {

        Main main = new Main();
        main.startGUI();

    }

}

