package Projekt03.View;

import java.awt.*;

public class View {

    public View() {
    }

    public void paint(Graphics g, Image image) {
        g.drawImage(image, 0, 0, null);
    }

    public void paintMario(Graphics g, int x, int y, int width, int height, Image image) {
        g.drawImage(image, x, y, width, height, null);

    }

    public void paintBarrel(Graphics g, int x, int y, int width, int height, Image image) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void paintString(Graphics g, int x, int y, String tekst, Color color) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont(90f));
        g.drawString(tekst, x, y);
    }

    public void printLives(Graphics g, int x, int y, int size, int lenght, Image image) {
        g.drawImage(image, x, y, lenght, size, null);
    }
}
