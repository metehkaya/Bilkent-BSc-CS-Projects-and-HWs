package UserInterface;

import GameLogic.DataManager;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardMenu extends SurvivorMenu {

    private BufferedImage backgroundImage;
    private JButton backButton;
    private ScoreBoardMouseListener listener;
    private JLabel label;
    private ArrayList<Integer> list;
    private JPanel panel;

    public ScoreboardMenu(ManageMenu menu, DataManager data) throws IOException {
        super(menu);

        
        try {
            backgroundImage = ImageIO.read(new File("images\\spacebackground.png"));
        } catch (IOException e) {
        }
        
        JLabel backGround = new JLabel("");
        backGround.setBounds(0, 0, 905, 635);
        add(backGround);
        backGround.setIcon(new ImageIcon(backgroundImage));

        ScoreBoardMouseListener listener = new ScoreBoardMouseListener();
        addMouseListener(listener);

        list = data.getScores();

    }

    
    public void paint(Graphics page) {

        super.paint(page);
        page.drawImage(backgroundImage, 0, 0, null);

        // Bence font iyi
        page.setColor(Color.WHITE);
        page.setFont(new Font("TimesRoman", Font.BOLD, 25));
        page.drawString("HIGH SCORES", 350, 50);

        // Koordinatlar değişebilir background'a göre
          for (int i = 1; i < 7 ; i++) {
            if( list.get(i) == -1){
             page.drawString(i + "-) " + "Not completed" + "", 120, 50 + 65 * (i));
            }
            else{
            page.drawString(i + "-) " + list.get(i) + "", 120, 50 + 65 * (i));
            }
        }
        
        for (int i = 7; i < list.size(); i++) {
            if( list.get(i) == -1){
             page.drawString(i + "-) " + "Not completed" + "", 500, 50 + 65 * (i-6));
            }
            else{
            page.drawString(i + "-) " + list.get(i) + "", 500, 50 + 65 * (i-6));
            }
        }

    }

    /*
    public void paintComponent(Graphics page) {
        super.paintComponent(page);

        Graphics2D g2d = (Graphics2D) page;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.white);
        java.awt.Font currentFont = g2d.getFont();
        java.awt.Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        page.setFont(newFont);
        page.drawString("EBEN", 100, 100);

    }
*/
    private class ScoreBoardMouseListener implements MouseListener {

        public void mouseClicked(MouseEvent event) {

            Point point = event.getPoint();

            int x = point.x;
            int y = point.y;

            //System.out.printf( "HSListener: %d %d\n" , x , y );
            if ((x) <= 500 && y > 500) {
                manageMenu.showMainMenu();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == backButton) {
                manageMenu.showMainMenu();
            }
        }

    }

}
