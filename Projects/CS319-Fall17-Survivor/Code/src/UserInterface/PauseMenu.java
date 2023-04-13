package UserInterface;

import java.awt.Dimension;

public class PauseMenu extends SurvivorMenu {

    public PauseMenu(ManageMenu manageMenu, int idLevel) {

        super(manageMenu);

        setPreferredSize(new Dimension(500, 400));
        setOpaque(false);
        
    }

}
