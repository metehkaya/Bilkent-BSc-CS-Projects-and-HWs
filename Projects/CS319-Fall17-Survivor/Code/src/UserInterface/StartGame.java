package UserInterface;

import java.io.IOException;
import javax.swing.JFrame;

public class StartGame {

    public static void main(String[] args) throws IOException {

        JFrame myFrame = new JFrame("Survivor");
        myFrame.setSize(900, 600);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ManageMenu manageMenu = new ManageMenu(myFrame);
        myFrame.setVisible(true);
        myFrame.setResizable(false);

    }

}
