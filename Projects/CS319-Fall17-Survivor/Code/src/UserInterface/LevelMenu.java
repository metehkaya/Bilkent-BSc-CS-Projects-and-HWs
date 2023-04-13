package UserInterface;

import GameLogic.DataManager;
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

public class LevelMenu extends SurvivorMenu {
    
    private BufferedImage backgroundImage;
    private BufferedImage t1,t2,t3,k2,k3, acm3, acm2, acm1, km2, km3;
    private BufferedImage ak1, ak2, ak3, kk2, kk3, ay1, ay2, ay3, ky2, ky3, arrow;
    private LevelMenuListener listener;
    public static final int[] X_CENTERS = {250,280,370,520,610,640,640,610,520,360,280,250};
    public static final int[] Y_CENTERS = {220,130,87,87,130,220,340,420,470,470,420,340};
    private ButtonListener myListener;
    
    private final int LEVELS = 20;
    
    public LevelMenu( ManageMenu manageMenu , DataManager data ) {
        
        super( manageMenu );
        try {
            t1 = ImageIO.read( new File( "images\\t1.png" ) );
        }
        catch( IOException e ) {}
        JLabel t1l = new JLabel("");
        t1l.setBounds( 500 , 67 , 40 ,  40);
        t1l.setIcon( new ImageIcon(t1) );
        add( t1l );
        try {
            t2 = ImageIO.read( new File( "images\\t2.png" ) );
        }
        catch( IOException e ) {}
        JLabel t2l = new JLabel("");
        t2l.setBounds( 590 , 110 , 40 ,  40);
        t2l.setIcon( new ImageIcon(t2) );
        add( t2l );
        
        try {
            t3 = ImageIO.read( new File( "images\\t3.png" ) );
        }
        catch( IOException e ) {}
        JLabel t3l = new JLabel("");
        t3l.setBounds( 620 , 200 , 40 ,  40);
        t3l.setIcon( new ImageIcon(t3) );
        add( t3l );
        
        try {
            acm3 = ImageIO.read( new File( "images\\acm3.png" ) );
        }
        catch( IOException e ) {}
        JLabel acm3l = new JLabel("");
        acm3l.setBounds( 500 , 450 , 40 ,  40);
        acm3l.setIcon( new ImageIcon(acm3) );
        add( acm3l );
        
        try {
            acm2 = ImageIO.read( new File( "images\\acm2.png" ) );
        }
        catch( IOException e ) {}
        JLabel acm2l = new JLabel("");
        acm2l.setBounds( 590 , 400 , 40 ,  40);
        acm2l.setIcon( new ImageIcon(acm2) );
        add( acm2l );
        
        try {
            acm1 = ImageIO.read( new File( "images\\acm1.png" ) );
        }
        catch( IOException e ) {}
        JLabel acm1l = new JLabel("");
        acm1l.setBounds( 620 , 320 , 40 ,  40);
        acm1l.setIcon( new ImageIcon(acm1) );
        add( acm1l );
        
        try {
            ak1 = ImageIO.read( new File( "images\\ak1.png" ) );
        }
        catch( IOException e ) {}
        JLabel ak1l = new JLabel("");
        ak1l.setBounds( 340 , 450 , 40 ,  40);
        ak1l.setIcon( new ImageIcon(ak1) );
        add( ak1l );
        
        try {
            ak2 = ImageIO.read( new File( "images\\ak2.png" ) );
        }
        catch( IOException e ) {}
        JLabel ak2l = new JLabel("");
        ak2l.setBounds( 260 , 400 , 40 ,  40);
        ak2l.setIcon( new ImageIcon(ak2) );
        add( ak2l );
        
        try {
            ak3 = ImageIO.read( new File( "images\\ak3.png" ) );
        }
        catch( IOException e ) {}
        JLabel ak3l = new JLabel("");
        ak3l.setBounds( 230 , 320 , 40 ,  40);
        ak3l.setIcon( new ImageIcon(ak3) );
        add( ak3l );
        
        
        try {
            ay1 = ImageIO.read( new File( "images\\ay1.png" ) );
        }
        catch( IOException e ) {}
        JLabel ay1l = new JLabel("");
        ay1l.setBounds( 230 , 200 , 40 ,  40);
        ay1l.setIcon( new ImageIcon(ay1) );
        add( ay1l );
        
        try {
            ay2 = ImageIO.read( new File( "images\\ay2.png" ) );
        }
        catch( IOException e ) {}
        JLabel ay2l = new JLabel("");
        ay2l.setBounds( 260 , 110 , 40 ,  40);
        ay2l.setIcon( new ImageIcon(ay2) );
        add( ay2l );
        
        try {
            ay3 = ImageIO.read( new File( "images\\ay3.png" ) );
        }
        catch( IOException e ) {}
        JLabel ay3l = new JLabel("");
        ay3l.setBounds( 350 , 67 , 40 ,  40);
        ay3l.setIcon( new ImageIcon(ay3) );
        add( ay3l );
        
        try {
            arrow = ImageIO.read( new File( "images\\arrow2.png" ) );
        }
        catch( IOException e ) {}
        JLabel arrowl = new JLabel("");
        arrowl.setBounds( 145 , 15 , 200,  300);
        arrowl.setIcon( new ImageIcon(arrow) );
        add( arrowl );
        
        ///////////////////////////
        try {
            backgroundImage = ImageIO.read( new File( "images\\levels.png" ) );
        }
        catch( IOException e ) {}
        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setBounds( 0 , 0 , 900 ,  600);
        backgroundLabel.setIcon( new ImageIcon(backgroundImage) );
        add( backgroundLabel );
        
        myListener = new ButtonListener();
        listener = new LevelMenuListener();
        addMouseListener(listener);
        
        
        
    }
    
    int getLevel(int x,int y) {
       int ret = -1;
       for (int i = 0; i < 12; i++) {
           if ((400 >= (x - X_CENTERS[i])*(x - X_CENTERS[i]) + (y - Y_CENTERS[i]) * (y - Y_CENTERS[i])) ) {
               return i;
           }
       }
       
       return ret;
    }
    private class LevelMenuListener implements MouseListener {
        
        public void mouseClicked( MouseEvent event ) {
            
            Point point = event.getPoint();
            
            int x = point.x;
            int y = point.y;
            
            System.out.printf( "HSListener: %d %d\n" , x , y );
             if ( (x)  <= 200 && y > 500) {
                 manageMenu.showMainMenu();
             }
             else {
                 if (getLevel(x,y) != -1) {
                     try {
                    manageMenu.showLevelMap2(getLevel(x,y) + 1 );
                } catch (IOException ex) {
                    Logger.getLogger(LevelMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                 }
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
            
                int idLevel = Integer.parseInt( ( (JButton) event.getSource() ).getText() );
                try {
                    manageMenu.showLevelMap2( idLevel );
                } catch (IOException ex) {
                    Logger.getLogger(LevelMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        
    }

	/*class ButtonListener implements ActionListener {

       	 public void actionPerformed(ActionEvent event) {

            int idLevel = Integer.parseInt(((JButton) event.getSource()).getText());
            try {
                manageMenu.showLevelMap(idLevel);
            } catch (IOException ex) {
                Logger.getLogger(LevelMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }*/
    
}
