import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{
    private JButton play;
    private JButton exit;
    private JPanel panel;

    public Menu(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        play = new JButton("PLAY!");
        play.addActionListener(new PlayButtonListener());

        exit = new JButton("EXIT");
        exit.addActionListener(new ExitButtonListener());

        panel.add(play, BorderLayout.NORTH);
        panel.add(exit, BorderLayout.SOUTH);

        add(panel);
    }

    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class PlayButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Hello");
            SnakeSnacks.timer.start();
        }
    }
}