package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankFrontEnd extends JPanel implements ActionListener {
    private Timer timer = new Timer(1000,this);
    public BankFrontEnd() {
        setFocusable(true);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        /////////////////////
        graphics2D.setFont(new Font("Courier New", Font.BOLD, 20));
        graphics2D.setColor(Color.black);
        graphics2D.drawString(BankDatabase.BankName,340,460);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
