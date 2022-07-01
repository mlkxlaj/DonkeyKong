package Projekt03.Model;

public class Mario {

    int x;
    int y;
    char lastPressed;
    boolean jump = false;
    int move;

    public Mario(int x,int y,int move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }

    public void marioReset(int x,int y,boolean jump,int move){
        this.x = x;
        this.y = y;
        this.jump = jump;
        this.move = move;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLastPressed() {
        return lastPressed;
    }

    public boolean isJump() {
        return jump;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setX(int x) {
        this.x += x;
    }

    public void setY(int y) {
        this.y += y;
    }

    public void setLastPressed(char lastPressed) {
        this.lastPressed = lastPressed;
    }

    public void resetLastPressed(char lastPressed) {
        this.lastPressed = lastPressed;
    }

}
