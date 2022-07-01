package Projekt03.Model;

public class Barrel {

    int x;
    int y;
    int moving;

    public Barrel(int x,int y,int moving) {
        this.x = x;
        this.y = y;
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMoving() {
        return moving;
    }

    public void setMoving() {
        this.moving *= -1;
    }

    public void setX(int x) {
        this.x += x;
    }

    public void setY(int y) {
        this.y += y;
    }
}
