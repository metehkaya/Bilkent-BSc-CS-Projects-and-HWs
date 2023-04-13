package Components;

public class StaticGameObject extends GameObject {
    
    public StaticGameObject() {
        super();
    }
    
    public StaticGameObject(int id, int x, int y, int w, int h, String source) {
        super( 0 , id, x, y, w, h, source );
    }
    
}
