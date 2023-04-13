package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameLogic.*;
import java.io.IOException;

public class ManageMenu {
    
    private JFrame myFrame;
            
    private CreditsMenu myCreditsMenu;
    private HelpMenu myHelpMenu;
    private LevelMenu myLevelMenu;
    private MainMenu myMainMenu;
    private ScoreboardMenu myScoreboardMenu;
    private LevelFinishedMenu myLevelFinishedMenu;
    
    private DataManager data;
    
    public ManageMenu( JFrame frame ) throws IOException {
        
        myFrame = frame;
        
        data = new DataManager();
        
        myMainMenu = new MainMenu( this );
        myCreditsMenu = new CreditsMenu( this );
        myHelpMenu = new HelpMenu( this );
        myLevelMenu = new LevelMenu( this , data );
        // myLevelFinishedMenu
        
        showMainMenu();
        
    }
    
    public void showMenu( JPanel menu ) {
        myFrame.getContentPane().removeAll();
        myFrame.add(menu);
        myFrame.getContentPane().revalidate();
        myFrame.getContentPane().repaint();
        myFrame.setResizable(true);
    }
    
    public void showCreditsMenu() {
        showMenu( myCreditsMenu );
    }
    
    public void showHelpMenu() {
        showMenu( myHelpMenu );
    }
    
    public void showLevelMenu() {
        myLevelMenu = new LevelMenu( this , data );
        showMenu( myLevelMenu );
    }
    
    public void showMainMenu() {
        myMainMenu = new MainMenu( this );
        showMenu( myMainMenu );
    }
    
    public void showScoreboardMenu() throws IOException {
        myScoreboardMenu = new ScoreboardMenu( this , data );
        showMenu( myScoreboardMenu );
    }
    
    public void showLevelMap( int idLevel ) throws IOException {
        System.out.println( "ManageMenu: " + idLevel );
        
        MapManager map = new MapManager( this , idLevel , data,30);
        showMenu( map );
    }
    
    public void showLevelFinished( int idLevel , boolean isPassed ) {
        myLevelFinishedMenu = new LevelFinishedMenu( this , idLevel , isPassed );
        showMenu( myLevelFinishedMenu );
    }
    
    public void showLevelMap2( int idLevel ) throws IOException {
        MapManager map = new MapManager(this,idLevel, data, 30 );
        //System.out.println(idLevel);
        
        if (((idLevel-1) / 3 ) == 0) {
           map.loadTiles("/GameLogic/air1.gif");
           if (((idLevel-1) % 3 ) == 0)
            map.loadMap("/GameLogic/earth.map");
           else if (((idLevel-1) % 3 ) == 1)
            map.loadMap("/GameLogic/earth1.map");
           else if (((idLevel-1) % 3 ) == 2)
            map.loadMap("/GameLogic/earth2.map");
        }
        else if (((idLevel-1) / 3 ) == 1) {
           map.loadTiles("/GameLogic/space.gif");
           if (((idLevel-1) % 3 ) == 0)
            map.loadMap("/GameLogic/space.map");
           else if (((idLevel-1) % 3 ) == 1)
            map.loadMap("/GameLogic/space1.map");
           else if (((idLevel-1) % 3 ) == 2)
            map.loadMap("/GameLogic/space2.map");
        }
        else if (((idLevel-1) / 3 ) == 2) {
           map.loadTiles("/GameLogic/water.gif");
           if (((idLevel-1) % 3 ) == 0)
            map.loadMap("/GameLogic/water.map");
           else if (((idLevel-1) % 3 ) == 1)
            map.loadMap("/GameLogic/water1.map");
           else if (((idLevel-1) % 3 ) == 2)
            map.loadMap("/GameLogic/water2.map");
        }
        else if (((idLevel-1) / 3 ) == 3) {
           map.loadTiles("/GameLogic/air1.gif");
           if (((idLevel-1) % 3 ) == 0)
            map.loadMap("/GameLogic/air.map");
           else if (((idLevel-1) % 3 ) == 1)
            map.loadMap("/GameLogic/air1.map");
           else if (((idLevel-1) % 3 ) == 2)
            map.loadMap("/GameLogic/air2.map");
        }
        
        map.setPosition(0, 0);
        showMenu(map);

    }



    
}
