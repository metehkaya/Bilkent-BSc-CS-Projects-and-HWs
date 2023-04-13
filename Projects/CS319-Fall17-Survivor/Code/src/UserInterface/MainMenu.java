package UserInterface;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainMenu extends SurvivorMenu {
    
    private JButton creditsButton;
    private JButton helpButton;
    private JButton levelButton;
    private BufferedImage levelB;
    private BufferedImage creditsB;
    private BufferedImage scoresB;
    private BufferedImage helpB;
    private JButton scoreboardButton;
    private ButtonListener myListener;
    private BufferedImage backgroundImage;
    private MainMenuListener lis;
    public MainMenu( ManageMenu manageMenu ) {
        
        super( manageMenu );
        
        myListener = new ButtonListener();
        
        
        try {
            levelB = ImageIO.read( new File( "images\\playB.png" ) );
        }
        catch( IOException e ) {}
        JLabel levelLa= new JLabel("");
        levelLa.setBounds( 350,100 , 200 ,  100);
        levelLa.setIcon( new ImageIcon(levelB) );
        add( levelLa );
        try {
            creditsB = ImageIO.read( new File( "images\\creditsB.png" ) );
        }
        catch( IOException e ) {}
        JLabel creditL= new JLabel("");
        creditL.setBounds( 350,200 , 200 ,  100);
        creditL.setIcon( new ImageIcon(creditsB) );
        add( creditL );
        try {
            scoresB = ImageIO.read( new File( "images\\scoresB.png" ) );
        }
        catch( IOException e ) {}
        JLabel scoresL= new JLabel("");
        scoresL.setBounds( 350, 300 , 200 ,  100);
        scoresL.setIcon( new ImageIcon(scoresB) );
        add( scoresL );
        try {
            helpB = ImageIO.read( new File( "images\\helpB.png" ) );
        }
        catch( IOException e ) {}
        JLabel helpL= new JLabel("");
        helpL.setBounds( 350,400 , 200 ,  100);
        helpL.setIcon( new ImageIcon(helpB) );
        add( helpL);
        try {
            backgroundImage = ImageIO.read( new File( "images\\main.jpg" ) );
        }
        catch( IOException e ) {}
        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setBounds( 0 , 0 , 900 ,  600);
        backgroundLabel.setIcon( new ImageIcon(backgroundImage) );
        add( backgroundLabel );
        lis = new  MainMenuListener();
        
        addMouseListener(lis);
        
    }
    
    public void paint(Graphics g) {
        super.paint(g);
    }
    private class MainMenuListener implements MouseListener {
        
        public void mouseClicked( MouseEvent event ) {
            
            Point point = event.getPoint();
            
            int x = point.x;
            int y = point.y;
            
            //System.out.printf( "HSListener: %d %d\n" , x , y );
             if ( (x)  <= 550 && (x) >= 350 && y >= 100 && y < 180 ) {
                 manageMenu.showLevelMenu();
             }
             else if ( (x)  <= 550 && (x) >= 350 && y >= 200 && y < 280 ) {
                 manageMenu.showCreditsMenu();
             }
             else if ( (x)  <= 550 && (x) >= 350 && y >= 300 && y < 380 ) {
                try {
                    manageMenu.showScoreboardMenu();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
             else if ( (x)  <= 550 && (x) >= 350 && y >= 400 && y < 480 ) {
                 manageMenu.showHelpMenu();
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
        
        public void actionPerformed( ActionEvent event ) {
            if( event.getSource() == creditsButton ) {
                manageMenu.showCreditsMenu();
            }
            else if( event.getSource() == helpButton ) {
                manageMenu.showHelpMenu();
            }
            else if( event.getSource() == levelButton ) {
                manageMenu.showLevelMenu();
            }
            else if( event.getSource() == scoreboardButton ) {
                try {
                    manageMenu.showScoreboardMenu();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}