package UserInterface;

import java.awt.Color;
import javax.swing.JPanel;

public class SurvivorMenu extends JPanel {

    ManageMenu manageMenu;

    public SurvivorMenu(ManageMenu menu) {
        manageMenu = menu;
        setLayout(null);
        setBackground(Color.WHITE);
    }

}
