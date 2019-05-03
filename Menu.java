import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{
    private JButton play;
    private JButton exit;
    private JPanel panel;
    private Timer timer;

    public Menu(Timer timer){

        this.timer = timer;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        play = new JButton("PLAY!");
        play.addActionListener(new PlayButtonListener());
        play.setFocusable(false);

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
            timer.start();
        }
    }
}