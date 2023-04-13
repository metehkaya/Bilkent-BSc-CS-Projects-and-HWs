package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class LevelFinishedMenu extends SurvivorMenu {

    private JButton nextLevelButton;
    private JButton levelMenuButton;
    private JButton mainMenuButton;

    private ButtonListener myListener;

    private int idLevel;
    private boolean isPassed;

    private final int MAX_LEVEL = 12;

    private final int WIDTH = 900;
    private final int HEIGHT = 600;

    public LevelFinishedMenu(ManageMenu manageMenu, int idLevel, boolean isPassed) {

        super(manageMenu);

        this.idLevel = idLevel;
        this.isPassed = isPassed;
        myListener = new ButtonListener();

        if (isPassed && idLevel < MAX_LEVEL ) {
            nextLevelButton = new JButton("Next Level");
        } else {
            nextLevelButton = new JButton("Try Again");
        }
        nextLevelButton.setBounds(WIDTH / 2 - 100, 100, 200, 50);
        nextLevelButton.addActionListener(myListener);
        add(nextLevelButton);

        levelMenuButton = new JButton("Level Menu");
        levelMenuButton.setBounds(WIDTH / 2 - 100, 250, 200, 50);
        levelMenuButton.addActionListener(myListener);
        add(levelMenuButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(WIDTH / 2 - 100, 400, 200, 50);
        mainMenuButton.addActionListener(myListener);
        add(mainMenuButton);

        // System.out.println("levels: " + idLevel + " " + isPassed);
        
        /*
        if (isPassed) {
            System.out.println("GECTIN GUZELIM");
        } else {
            System.out.println("KALDIN MI CANIM");
        }
        */

    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // System.out.println("BUTTON ZAA");
            if (event.getSource() == nextLevelButton) {
                if (!isPassed) {
                    try {
                         System.out.println( "!isPassed" );
                        manageMenu.showLevelMap2(idLevel);
                    } catch (IOException ex) {
                        Logger.getLogger(LevelFinishedMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (idLevel < MAX_LEVEL) {
                        try {
                            // System.out.println( "isPassed <" );
                            manageMenu.showLevelMap2(idLevel + 1);
                        } catch (IOException ex) {
                            Logger.getLogger(LevelFinishedMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // System.out.println( "isPassed =" );
                            manageMenu.showLevelMap(idLevel);
                        } catch (IOException ex) {
                            Logger.getLogger(LevelFinishedMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else if (event.getSource() == levelMenuButton) {
                manageMenu.showLevelMenu();
            } else if (event.getSource() == mainMenuButton) {
                manageMenu.showMainMenu();
            }
        }

    }

}
