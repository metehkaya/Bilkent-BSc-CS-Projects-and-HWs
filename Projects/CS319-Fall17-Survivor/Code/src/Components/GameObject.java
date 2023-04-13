package Components;

public class GameObject {

    int type;
    int id;
    int x;
    int y;
    int w;
    int h;

    String source;

    public GameObject() {

    }

    public GameObject(int type, int id, int x, int y, int w, int h, String source) {
        this.type = type;
        this.id = id;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.source = source;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public String getSource() {
        return source;
    }
    public void setX(int x) {
        this.x = x;
    }
}
