package UserInterface;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CreditsMenu extends SurvivorMenu {
    
    private BufferedImage backgroundImage;
    private BufferedImage backImage;
    
    public CreditsMenu( ManageMenu manageMenu ) {
        
        super( manageMenu );
        
        try {
            backgroundImage = ImageIO.read( new File( "images\\credits.png" ) );
        }
        catch( IOException e ) {}
        JLabel backgroundLabel = new JLabel("");
        backgroundLabel.setBounds( 0 , 0 , 900 ,  600);
        backgroundLabel.setIcon( new ImageIcon(backgroundImage) );
        add( backgroundLabel );
        CreditsMouseListener mouseListener = new CreditsMouseListener();
        addMouseListener( mouseListener );   

    }
    private class CreditsMouseListener implements MouseListener {
        
        public void mouseClicked( MouseEvent event ) {
            
            Point point = event.getPoint();
            
            int x = point.x;
            int y = point.y;
            
            System.out.printf( "HSListener: %d %d\n" , x , y );
             if ( (x)  <= 200 && y > 500) {
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
    
    
}
