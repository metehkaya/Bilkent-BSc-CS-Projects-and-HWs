package GameLogic;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Components.GameObject;
import Components.DynamicGameObject;
import Components.StaticGameObject;
import UserInterface.ManageMenu;
import UserInterface.PauseMenu;
import UserInterface.SurvivorMenu;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static java.lang.Double.max;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;
import javax.swing.*;
//import GameMapManagement.NewMain.*;
//package GameMapManagement;

/**
 *
 * @author Emre Yiğit Kuzhan
 */
public class MapManager extends SurvivorMenu {
    boolean check = false;
    private final int WIDTH = 1800;
    private final int HEIGHT = 600;

    private StaticGameObject staticObject;
    private ArrayList<StaticGameObject> staticObjectList;
    private  DynamicGameObject dynamicObject;
    private ArrayList<DynamicGameObject> dynamicObjectList;
    private Image playerImage;
    private DynamicGameObject playerObject;
    private Image destImage;
    private int destX, destY, destW, destH;
    private double playerX, playerY, playerW, playerH;
    private int playerDirX = 0, playerDirY = 0;
    private ImageIcon icon;
    private JLabel label;
    
     private boolean teleExists = false;
    private Image teleStartImage;
    private int teleStartX, teleStartY, teleStartW, teleStartH;
    private Image teleEndImage;
    private int teleEndX, teleEndY, teleEndW, teleEndH;
        private int currentLevelTime = 0;
    private int totalLevelTime = 2000;
    private int totalSourceLimit = 100000;
    private final int DIR_LIMIT = 5;

    private final double PLANET_EFFECT = 20.0;
    private ArrayList<StaticGameObject> planets = new ArrayList<>();

    private final int RADIATION_PERIOD = 5;
    private ArrayList<StaticGameObject> radiations = new ArrayList<>();

    private ArrayList<StaticGameObject> staticAliens = new ArrayList<>();

    private ArrayList<DynamicGameObject> dynamicAliens = new ArrayList<>();

    private ArrayList<StaticGameObject> blocks = new ArrayList<>();
    
    
    private int idLevel;
    private final int MAX_LEVEL = 12;

    private int isReached = 0;
    private int isTeleport = 0;
    private boolean isPaused = false;
    private boolean isDangerousCollision = false;

    private boolean[] keys = new boolean[256];

    private DataManager data;

    private ManageMenu manageMenu;

    private PauseMenu pauseMenu;

    
    
    //double playerX, playerY, pW, pH;

    private Timer playerTimer;
    private Timer chronoTimer;

    private int currentTime;

    
    //Position
    private double x;
    private double y;
    
    //start
    private boolean start;

    //Boundaries
    private int xmin;
    private int xmax;
    private int ymin;
    private int ymax;

    //Mapping
    int[][] map;
    int tileSize;
    int numRows;
    int numCols;
    int width;
    int height;
    double dub;
    int extend;

    //Tile
    BufferedImage tileset;
    int numTilesAcross;
    Tile[][] tiles;
    int rowOffSet;
    int colOffSet;
    int numRowsToDraw;
    int numColsToDraw;
    long elapsedTime;
    long startTime;
    
    JLabel sources;

    public MapManager(ManageMenu manageMenu, int idLevel, DataManager data,int tileSize)throws IOException {
        super(manageMenu);
        sources = new JLabel();
        sources.setText("Remaining sources "+ totalLevelTime+ "/"+totalSourceLimit);
        sources.setBounds(600, 500, 250, 20);
        this.add(sources);
        ArrayList<Integer> limits = data.getLimits("data/Levels/" + idLevel + "/limits.txt");
        totalLevelTime = limits.get(0);
        totalSourceLimit = limits.get(1);

        this.data = data;
        this.idLevel = idLevel;

        this.manageMenu = manageMenu;
        this.pauseMenu = new PauseMenu(manageMenu, this.idLevel);
        
        this.idLevel = idLevel;
        this.tileSize = tileSize;
        start = true;
        startTime = System.currentTimeMillis();
        elapsedTime = 0L;
        extend = 0;
        staticObjectList = data.getStaticObjects("data/Levels/" + idLevel + "/static_objects.txt");

        for (int i = 0; i < staticObjectList.size(); i++) {

            staticObject = staticObjectList.get(i);

            if (staticObject.getId() == 0) {
                destX = staticObject.getX();
                destY = staticObject.getY();
                destW = staticObject.getW();
                destH = staticObject.getH();
                icon = new ImageIcon(staticObject.getSource());
                destImage = icon.getImage();
            } else if (staticObject.getId() == 1) {
                planets.add(staticObject);
            } else if (staticObject.getId() == 2) {
                teleExists = true;
                teleStartX = staticObject.getX();
                teleStartY = staticObject.getY();
                teleStartW = staticObject.getW();
                teleStartH = staticObject.getH();
                icon = new ImageIcon(staticObject.getSource());
                teleStartImage = icon.getImage();
            } else if (staticObject.getId() == 3) {
                teleEndX = staticObject.getX();
                teleEndY = staticObject.getY();
                teleEndW = staticObject.getW();
                teleEndH = staticObject.getH();
                icon = new ImageIcon(staticObject.getSource());
                teleEndImage = icon.getImage();
            } else if (staticObject.getId() == 4) {
                blocks.add(staticObject);
            } else if (staticObject.getId() == 5) {
                radiations.add(staticObject);
            } else if (staticObject.getId() == 6) {
                staticAliens.add(staticObject);
            }

        }
        
        dynamicObjectList = data.getDynamicObjects("data/Levels/" + idLevel + "/dynamic_objects.txt");

        for (int i = 0; i < dynamicObjectList.size(); i++) {

            dynamicObject = dynamicObjectList.get(i);

            if (dynamicObject.getId() == 0) {
                playerObject = dynamicObject;
                playerX = dynamicObject.getX();
                playerY = dynamicObject.getY();
                playerW = dynamicObject.getW();
                playerH = dynamicObject.getH();
                icon = new ImageIcon(dynamicObject.getSource() + "_right.png");
                playerImage = icon.getImage();
                
            } else if (dynamicObject.getId() == 1) {
                dynamicAliens.add(dynamicObject);
            }
            
        }

        currentTime = 0;

        EventQueue.invokeLater(() -> {
            grabFocus();
            requestFocus();
        });

        playerTimer = new Timer();
        playerTimer.schedule(new PlayerTimer(), 0, 50);

        chronoTimer = new Timer();
        chronoTimer.schedule(new ChronoTimer(), 0, 1000);
        
        
        numRowsToDraw = 640 / tileSize + 2;
        numColsToDraw = 1920 / tileSize + 2;
        dub = 3;

    }

    public void loadTiles(String s) {
        try {

            tileset = ImageIO.read(MapManager.class.getResourceAsStream(s));
             System.out.println(tileSize);
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];
            BufferedImage subImage;
              System.out.println("2");
            for (int col = 0; col < numTilesAcross; col++) {
                subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Tile(subImage, Tile.NORMAL);
                subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subImage, Tile.BLOCKED);

            }
        } catch (Exception e) {
            System.out.println("Try Again1");
        }
    }

    public void loadMap(String s) {
        try {

            InputStream in = MapManager.class.getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numCols = Integer.parseInt(br.readLine());

            numRows = Integer.parseInt(br.readLine());

            map = new int[numRows][numCols];

            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s+";

            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception e) {
            System.out.println("Try Again2");
        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {
        col = col - (extend/30);

        int rc = map[row][col];
        
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        
        
        return tiles[r][c].getType();

    }

    public void setPosition(double x, double y) {
        this.x = this.x - x;
        
    }

    public void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g;
         sources.setForeground(Color.WHITE);
        sources.setText("Remaining sources "+ totalLevelTime+ "/"+totalSourceLimit);

        
        for (int row = rowOffSet; row < rowOffSet + numRowsToDraw; row++) {
            if (row >= numRows) {
                break;
            }
            for (int col = colOffSet; col < colOffSet + numColsToDraw; col++) {
                if (col >= numCols) {
                    break;
                }
                if (map[row][col] == 0) {
                    continue;
                }
                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;
                g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize + extend, (int) y + row * tileSize, this);
               
            }
        }
        
        

        if (teleExists) {
            g2d.drawImage(teleStartImage, teleStartX+extend, teleStartY, this);
            g2d.drawImage(teleEndImage, teleEndX+extend, teleEndY, this);
            
        }

        for (int i = 0; i < planets.size(); i++) {
            icon = new ImageIcon(planets.get(i).getSource());
            //planets.get(i).setX(planets.get(i).getX()+extend/30);
            Image image = icon.getImage();
            g2d.drawImage(image, planets.get(i).getX()+extend, planets.get(i).getY(), this);
        }
        for (int i = 0; i < staticAliens.size(); i++) {
            icon = new ImageIcon(staticAliens.get(i).getSource());
            Image image = icon.getImage();
            
            g2d.drawImage(image, staticAliens.get(i).getX()+extend, staticAliens.get(i).getY(), this);
        }

        for (int i = 0; i < dynamicAliens.size(); i++) {
            icon = new ImageIcon(dynamicAliens.get(i).getSource());
            Image image = icon.getImage();
            g2d.drawImage(image, dynamicAliens.get(i).getX()+extend, dynamicAliens.get(i).getY(), this);
        }

        for (int i = 0; i < radiations.size(); i++) {
            if (((currentLevelTime - 1) / RADIATION_PERIOD) % 2 == 0) {
                System.out.println("radiation1 " + currentLevelTime);
                icon = new ImageIcon(radiations.get(i).getSource() + "_nodamage.png");
            } else {
                System.out.println("radiation2 " + currentLevelTime);
                icon = new ImageIcon(radiations.get(i).getSource() + "_dangerous.png");
            }
            Image image = icon.getImage();
            g2d.drawImage(image, radiations.get(i).getX()+extend, radiations.get(i).getY(), this);
        }

        if (playerDirX > 0) {
            icon = new ImageIcon(playerObject.getSource() + "_right.png");
            playerImage = icon.getImage();
        } else if (playerDirX < 0) {
            icon = new ImageIcon(playerObject.getSource() + "_left.png");
            playerImage = icon.getImage();
        }
        g2d.drawImage(destImage,destX+extend, destY,this);
        g2d.drawImage(playerImage, (int) playerX, (int) playerY, this);
        if (playerX- extend >= destX  && playerX -extend + playerW <= destX  + destW) {
            if (playerY >= destY && playerY + playerH <= destY + destH) {
                isReached++;
            }
        }

        if (isReached >= 1) {
             try {
                 data.setScore(idLevel, 100 * totalLevelTime + totalSourceLimit);
             } catch (IOException ex) {
                 Logger.getLogger(MapManager.class.getName()).log(Level.SEVERE, null, ex);
             }
            if (idLevel < MAX_LEVEL) {
                this.manageMenu.showLevelFinished(idLevel, true);
            } else {
                this.manageMenu.showLevelMenu();
            }
            playerTimer.cancel();
            playerTimer.purge();
        }

        if (isPaused) {
            // Empty
        }
        isDangerousCollision =checkDangerousCollision(); 
        //isDangerousCollision = checkDangerousCollision();

        if (isDangerousCollision) {
            this.manageMenu.showLevelFinished(idLevel, false);
            playerTimer.cancel();
            playerTimer.purge();
        }

        if (playerX-extend+15 >= teleStartX && playerX -extend + playerW -15<= teleStartX + teleStartW) {
            if (playerY+20 >= teleStartY && playerY -20+ playerH <= teleStartY + teleStartH) {
                isTeleport++;
                System.out.println("sada");
            }
            else {
            }
        }

        if (isTeleport >= 1) {
            extend = extend + teleStartX - teleEndX; 
           // playerX = teleEndX + teleEndW / 2 - playerW / 2;
            playerY = teleEndY + teleEndH / 2 - playerH / 2 ; 
            isTeleport = 0;
        }

        if (totalLevelTime < 0) {
            this.manageMenu.showLevelFinished(idLevel, false);
            playerTimer.cancel();
            playerTimer.purge();
        }

        if (totalSourceLimit < 0) {
            this.manageMenu.showLevelFinished(idLevel, false);
            playerTimer.cancel();
            playerTimer.purge();
        }
    }
        public double getAffectY() {
        double effect = 0;
        for (int i = 0; i < planets.size(); i++) {
            GameObject planet = planets.get(i);
            double centerX = planet.getX() + extend + planet.getW() / 2.0;
            double centerY = planet.getY() + planet.getH() / 2.0;
            double diffX = centerX - (playerX + playerW / 2.0);
            double diffY = centerY - (playerY + playerH / 2.0);
            double diff = diffX * diffX + diffY * diffY;
            if (diff < 1e-6) {
                continue;
            }
            double diffRate = diff / (planet.getW() * planet.getH() / 4.0);
            double diffEffect = PLANET_EFFECT / max(diffRate, 1);
            effect += diffEffect * diffY / Math.sqrt(diff);
        }
        return effect;
    }
            public double getAffectX() {
        double effect = 0;
        for (int i = 0; i < planets.size(); i++) {
            GameObject planet = planets.get(i);
            double centerX = planet.getX()+extend + planet.getW() / 2.0;
            double centerY = planet.getY() + planet.getH() / 2.0;
            double diffX = centerX - (playerX + playerW / 2.0);
            double diffY = centerY - (playerY + playerH / 2.0);
            double diff = diffX * diffX + diffY * diffY;
            
            if (diff < 1e-6) {
                continue;
            }
            double diffRate = diff / (planet.getW() * planet.getH() / 4.0);
            double diffEffect = PLANET_EFFECT / max(diffRate, 1);
            effect += diffEffect * diffX / Math.sqrt(diff);
        }
        return effect;
    }
    private boolean checkPlayerAndRectangleIntersect(double playerX, double playerY, double pW, double pH, int oX, int oY, int oW, int oH) {
        if (playerX >= oX + oW) {
            return false;
        }
        if (playerX + pW <= oX) {
            return false;
        }
        if (playerY >= oY + oH) {
            return false;
        }
        if (playerY + pH <= oY) {
            return false;
        }
        return true;
    }

    private boolean checkDangerousCollisionRadiation() {
        if (((currentLevelTime - 1) / RADIATION_PERIOD) % 2 == 0) {
            return false;
        }
        int rX, rY, rW, rH;
        for (int i = 0; i < radiations.size(); i++) {
            rX = radiations.get(i).getX()+extend;
            rY = radiations.get(i).getY();
            rW = radiations.get(i).getW();
            rH = radiations.get(i).getH();
            if (checkPlayerAndRectangleIntersect(playerX, playerY, playerW, playerH, rX, rY, rW, rH)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDangerousCollisionAliens() {
        int aX, aY, aW, aH;
        for (int i = 0; i < staticAliens.size(); i++) {
            aX = staticAliens.get(i).getX()+extend;
            aY = staticAliens.get(i).getY();
            aW = staticAliens.get(i).getW();
            aH = staticAliens.get(i).getH();
            if (checkPlayerAndRectangleIntersect(playerX, playerY, playerW, playerH, aX, aY, aW, aH)) {
                System.out.println("alien error");
                return true;
            }
        }
        for (int i = 0; i < dynamicAliens.size(); i++) {
            aX = dynamicAliens.get(i).getX()+extend;
            aY = dynamicAliens.get(i).getY();
            aW = dynamicAliens.get(i).getW();
            aH = dynamicAliens.get(i).getH();
            if (checkPlayerAndRectangleIntersect(playerX, playerY, playerW, playerH, aX, aY, aW, aH)) {
                System.out.println("dalien error");
                return true;
            }
        }
        return false;
    }

    private boolean checkDangerousCollision() {
        if (checkDangerousCollisionPlanets()) {
            System.out.println(playerX + " " + playerY);
            //System.out.println(planetX + " " + playerY);
            return true;
        }
        if (checkDangerousCollisionRadiation()) {
            System.out.println("radiation error");
            return true;
        }
        if (checkDangerousCollisionAliens()) {
            return true;
        }
        return false;
    }
    private boolean checkDangerousCollisionPlanets() {
        for (int i = 0; i < planets.size(); i++) {
            
            int centerX = planets.get(i).getX()+extend  + planets.get(i).getW() / 2;
            int centerY = planets.get(i).getY() + planets.get(i).getH() / 2;
            double radius = planets.get(i).getW() / 2;
            if (getDistance(playerX, playerY, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX+ playerW, playerY, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX, playerY + playerH, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX + playerW, playerY + playerH, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX, playerY + playerH / 2, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX + playerW / 2, playerY, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX + playerW, playerY + playerH / 2, centerX, centerY) <= radius * radius) {
                return true;
            }
            if (getDistance(playerX + playerW / 2, playerY + playerH, centerX, centerY) <= radius * radius) {
                return true;
            }
        }
        return false;
    }
    public void actionPerformed(ActionEvent event) {
        repaint();
    }
    public double getDistance(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    @Override
    protected void processKeyEvent(KeyEvent event) {
        // System.out.println("PROCESS KEY EVENT");
        if (event.getID() == KeyEvent.KEY_PRESSED) {
            keys[event.getKeyCode()] = true;
        } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.manageMenu.showLevelMenu();
            playerTimer.cancel();
            playerTimer.purge();
        } else if (event.getID() == KeyEvent.KEY_RELEASED) {
            keys[event.getKeyCode()] = false;
        }
        /*switch (event.getID()) {
            case KeyEvent.KEY_PRESSED:
                keys[event.getKeyCode()] = true;
                break;
            case KeyEvent.KEY_RELEASED:
                keys[event.getKeyCode()] = false;
                break;
            default:
                break;
        }*/
    }
    
    private class PlayerTimer extends TimerTask {
           
        public void isWaterMove(){
            //gravity
            try{
            if(start && (getType((int)(((playerY+95)/30)),(int)(playerX/30)) == 0) && (getType((int)((playerY+95)/30),(int)((playerX+45)/30)) == 0)){
                playerDirY = playerDirY + 1;
            }
           }catch (Exception e) {
             }
            //left
            if (keys[37]) {
                playerDirX = playerDirX - 1;
            }
            //up
            if (keys[38]) {
                playerDirY = playerDirY -2;
            }
            //right
            if (keys[39]) {
                playerDirX = playerDirX+ 1;
            }
            //down
            if (keys[40]) {
                playerDirY = playerDirY + 1;
            }
            
            
            //down control mechanism
            if (playerDirY > 0 && ((getType((int)(((playerY+80)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY+80)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = -playerDirY/3;
                //playerY = playerY - 3;
                
                        
            }
            //up control mechanism
             if (playerDirY < 0 && ((getType((int)(((playerY)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY)/30))),(int)(playerX+45)/30)) == 1)) {
                playerDirY = -playerDirY/3;
                //playerY = playerY+1;
            }
            
             if (playerDirX > 0 && ((getType((int)(((playerY)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX+45)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX+45)/30)) == 1))){
                
                playerDirX =0;
               //0playerX = playerX - 1;
            }
            if (playerDirX < 0 && ((getType((int)(((playerY)/30)),(int)((playerX)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX)/30)) == 1)))  {
                playerDirX = 0;
                //playerX =playerX +1;
            }
            
            
            
            
            
            if ((playerX > 450) && (playerDirX > 0)) {
                if (((playerX-extend +450) / 30) < map[1].length) {
                   extend = extend - 8;
                   playerDirX = 0;
                }
            }
        }
        public void isEarthMove() {
            //gravity
            try{
            if(start && (getType((int)(((playerY+95)/30)),(int)(playerX/30)) == 0) && (getType((int)((playerY+95)/30),(int)((playerX+45)/30)) == 0)){
                playerDirY = playerDirY + 3;
            }
           }catch (Exception e) {
            }
            //left
            if (keys[37]) {
                playerDirX = playerDirX - 1;
            }
            
            elapsedTime = (new Date()).getTime() - startTime;
            //up
            if (keys[38] && elapsedTime > 500) {
                startTime = System.currentTimeMillis();
                playerDirY = playerDirY -20;
            }
            //right
            if (keys[39]) {
                playerDirX = playerDirX+ 1;
            }
            //down
            if (keys[40]) {
                playerDirY = playerDirY + 1;
            }
                  
            //down control mechanism
            if (playerDirY > 0 && ((getType((int)(((playerY+80)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY+80)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY - 3;
                
                        
            }
            //up control mechanism
             if (playerDirY < 0 && ((getType((int)(((playerY)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY+1;
            }
            
             if (playerDirX > 0 && ((getType((int)(((playerY)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX+45)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX+45)/30)) == 1))) {
                
                playerDirX =0;
               //0playerX = playerX - 1;
            }
            if (playerDirX < 0 && ((getType((int)(((playerY)/30)),(int)((playerX)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX)/30)) == 1))) {
                playerDirX = 0;
                //playerX =playerX +1;
            }
            
            if ((playerX > 450) && (playerDirX > 0)) {
                if (((playerX-extend +450) / 30) < map[1].length) {
                   extend = extend - 8;
                   playerDirX = 0;
                }
            }
        }
        
        
        public void isAirMove() {
            if (keys[37]) {
                playerDirX = playerDirX - 1;
            }
            //up
            if (keys[38]) {
                playerDirY = playerDirY -6;
            }
            //right
            if (keys[39]) {
                playerDirX = playerDirX+ 1;
            }
            //down
            if (keys[40]) {
                playerDirY = playerDirY + 1;
            }
            //down control mechanism
            if (playerDirY > 0 && ((getType((int)(((playerY+80)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY+80)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY - 3;
                
                        
            }
            //up control mechanism
             if (playerDirY < 0 && ((getType((int)(((playerY)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY+1;
            }
            
             if (playerDirX > 0 && ((getType((int)(((playerY)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX+45)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX+45)/30)) == 1))) {
                
                playerDirX =0;
               //0playerX = playerX - 1;
            }
            if (playerDirX < 0 && ((getType((int)(((playerY)/30)),(int)((playerX)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX)/30)) == 1))) {
                playerDirX = 0;
                //playerX =playerX +1;
            }
            
            try{
            if(start && (getType((int)(((playerY+95)/30)),(int)(playerX/30)) == 0) && (getType((int)((playerY+95)/30),(int)((playerX+45)/30)) == 0)){
                playerDirY = playerDirY + 2;
            }
           }catch (Exception e) {
            }
            if ((playerX > 450) && (playerDirX > 0)) {
                if (((playerX-extend +450) / 30) < map[1].length) {
                   extend = extend - 8;
                   playerDirX = 0;
                }
            }
        }
        
        public void isSpaceMove() {
        
            if (keys[37]) {
                playerDirX = Math.max(playerDirX - 1, -DIR_LIMIT);
            }
            if (keys[38]) {
                playerDirY = Math.max(playerDirY - 1, -DIR_LIMIT);
            }
            if (keys[39]) {
                playerDirX = Math.min(playerDirX + 1, +DIR_LIMIT);
            }
            if (keys[40]) {
                playerDirY = Math.min(playerDirY + 1, +DIR_LIMIT);
            }

            double effectX = getAffectX();
            double effectY = getAffectY();

            double newX = playerX + playerDirX + effectX;
            double newY = playerY + playerDirY + effectY;

            totalSourceLimit -= Math.abs(playerDirX);
            totalSourceLimit -= Math.abs(playerDirY);

            if (newX < 0) {
                newX = 0;
            } else if (newX > WIDTH - playerW) {
                newX = WIDTH - playerW;
            }

            if (newY < 0) {
                newY = 0;
            } else if (newY > HEIGHT - playerH) {
                newY = HEIGHT - playerH;
            }

            /*for (int i = 0, bX, bY, bW, bH; i < blocks.size(); i++) {
                bX = blocks.get(i).getX();
                bY = blocks.get(i).getY();
                bW = blocks.get(i).getW();
                bH = blocks.get(i).getH();
                if (checkPlayerAndRectangleIntersect(newX, newY, playerW, playerH, bX, bY, bW, bH)) {

                }
            }*/
               
            playerX = playerX + playerDirX+ effectX;
            playerY = playerY+ playerDirY+effectY;

            for (int i = 0; i < dynamicAliens.size(); i++) {
                int x = dynamicAliens.get(i).getX();
                int y = dynamicAliens.get(i).getY();
                int period = dynamicAliens.get(i).getPeriod();
                if (((currentLevelTime - 1) / period) % 2 == 0) {
                    x += dynamicAliens.get(i).getDirX();
                    y += dynamicAliens.get(i).getDirY();
                } else {
                    x -= dynamicAliens.get(i).getDirX();
                    y -= dynamicAliens.get(i).getDirY();
                }
                dynamicAliens.get(i).updateXY(x, y);
            }
            //down control mechanism
            if (playerDirY > 0 && ((getType((int)(((playerY+80)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY+80)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY - 3;
                
                        
            }
            //up control mechanism
             if (playerDirY < 0 && ((getType((int)(((playerY)/30)),(int)(playerX/30)) == 1)||(getType((int)((((playerY)/30))),(int)((playerX+45)/30)) == 1))) {
                playerDirY = 0;
                //playerY = playerY+1;
            }
            
             if (playerDirX > 0 && ((getType((int)(((playerY)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX+45)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX+45)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX+45)/30)) == 1))) {
                
                playerDirX =0;
               //0playerX = playerX - 1;
            }
            if (playerDirX < 0 && ((getType((int)(((playerY)/30)),(int)((playerX)/30)) == 1)||(getType((int)((((playerY + 60)/30))),(int)((playerX)/30)) == 1) ||(getType((int)(((playerY+30)/30)),(int)((playerX)/30)) == 1)||(getType((int)(((playerY+50)/30)),(int)((playerX)/30)) == 1))) {
                playerDirX = 0;
                //playerX =playerX +1;
            }
            
            if ((playerX > 450) && (playerDirX > 0)) {
                if (((playerX-extend +450) / 30) < map[1].length) {
                   extend = extend - 8;
                   playerDirX = 0;
                }
            }
        }
        public void playSound() {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Asus\\Desktop\\GitHub\\CS-319-2G-Survivor\\Survivor\\sounds\\jump.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch(Exception ex) {
        System.out.println("Error with playing sound.");
        ex.printStackTrace();
    }
    }
        @Override
        public void run() {
            if (((idLevel-1) / 3 ) == 0) {
                isEarthMove();
            }
             else if (((idLevel-1) / 3 ) == 1) {
             isSpaceMove();
             }
            else if (((idLevel-1) / 3 ) == 2) {
            isWaterMove();
            }
            else if (((idLevel-1) / 3 ) == 3) {
                isAirMove();
            }
            
            
                if (playerX <= 0 && playerDirX < 0) {
                    playerDirX = 0;
                    playerX = 0;
                }
                else if ((playerX+50) >= 850 && playerDirX>0) {
                    playerDirX = 0;
                    playerX = playerX -5;
                }

                if (playerY <=  0 && playerDirY < 0) {
                    playerDirY = 0;
                    playerY = 0;
                }
                try{
                if ((playerY+playerH+80) >=  HEIGHT ) {
                    manageMenu.showLevelFinished(idLevel, false);
                    playerDirY = 0;
                    playerY = 0;
                    
                }
                }catch (Exception e) {
                    System.out.println("Game is finished");
             }
                
                playerX = playerX + playerDirX;
                playerY = playerY + playerDirY;
            if (playerDirX != 0 || playerDirY != 0)
                totalSourceLimit--;
            repaint();

        }
    }

    private class ChronoTimer extends TimerTask {

        @Override
        public void run() {
            totalLevelTime--;
            currentLevelTime++;
            // System.out.println("current time: " + currentTime);
        }
    }
    
}

