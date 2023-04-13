package Components;

public class DynamicGameObject extends GameObject {
    
    private int dirX;
    private int dirY;
    private int period;
    
    public DynamicGameObject() {
        super();
    }
    
    public DynamicGameObject(int id, int x, int y, int w, int h, String source ,int dirX ,int dirY , int period) {
        super( 1 , id, x, y, w, h, source );
        this.dirX = dirX;
        this.dirY = dirY;
        this.period = period;
    }
    
    public int getDirX() {
        return dirX;
    }
    
    public int getDirY() {
        return dirY;
    }
    
    public int getPeriod() {
        return period;
    }
    
    public void updateXY( int x , int y ) {
        this.x = x;
        this.y = y;
    }
    
}
