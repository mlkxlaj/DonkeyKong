package Projekt03.Controller;

import Projekt03.Model.Barrel;
import Projekt03.Model.Mario;
import Projekt03.View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class Controller implements KeyListener, Runnable {

    View view;
    Graphics graphics;
    Mario mario = new Mario(92,340,3);
    List<Barrel> beczki = new ArrayList<>();
    boolean thread = true;
    int[][] jumpTab = {{92, 340}, {135, 337}, {393, 319}, {436, 316}, {436, 174}, {393, 171}, {350, 168}, {307, 165}, {135, 153},{350,322},{307,325}};

    public Controller(Graphics graphics) {
        this.view = new View();
        this.graphics = graphics;
        new Thread(this).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> mario.setLastPressed('w');
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> mario.setLastPressed('s');
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> mario.setLastPressed('a');
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> mario.setLastPressed('d');
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        Image image = new ImageIcon("src/Projekt03/Controller/mario.png").getImage();
        int zycia = 3;
        int ileklatek = 0;
        int licznik = 0;
        long speed = 1000;
        boolean test = false;
        while (thread) {
            view.paint(graphics, new ImageIcon("src/Projekt03/Controller/view.png").getImage());

/**
 *  Beczki ruch
 */
            for (int i = 0; i< beczki.size() ; i++) {
                if (beczki.get(i).getX() == 559 && beczki.get(i).getY() == 197) {
                    beczki.get(i).setY(120);
                    beczki.get(i).setMoving();
                } else if (beczki.get(i).getX() == 559 && beczki.get(i).getY() == 317) {
                    beczki.get(i).setY(3);
                    beczki.get(i).setX(beczki.get(i).getMoving());
                } else if (beczki.get(i).getX() == 86 && beczki.get(i).getY() == 350) {
                    beczki.get(i).setY(5000);
                    beczki.get(i).setX(6000);
                } else {
                    beczki.get(i).setY(3);
                    beczki.get(i).setX(beczki.get(i).getMoving());
                }
                view.paintBarrel(graphics, beczki.get(i).getX(), beczki.get(i).getY(),35,25, new ImageIcon("src/Projekt03/Controller/barrel.png").getImage());
            }

/**
 * Mario ruch
 */
            if (mario.isJump()) {
                mario.setY(+80);
                image = new ImageIcon("src/Projekt03/Controller/mario.png").getImage();
                view.paintMario(graphics, mario.getX(), mario.getY(),25,35, image);
                mario.setJump(false);
            }

            switch (mario.getLastPressed()) {
                case 'w':
                    boolean jump = false;
                    for (int[] ints : jumpTab) {
                        if (mario.getX() == ints[0] && mario.getY() == ints[1]) {
                            jump = true;
                            break;
                        }
                    }
                    if (mario.getX() == 522 && mario.getY() == 310) {
                        mario.setY(-130);
                        mario.setMove(-3);
                    } else if (mario.getX() == 92 && mario.getY() == 150) {
                        mario.setX(-5000);
                        mario.setY(-5000);
                        test = true;
                        thread = false;
                    } else if (jump) {
                        mario.setY(-80);
                        mario.setJump(true);
                        image = new ImageIcon("src/Projekt03/Controller/mariojump.png").getImage();
                    }
                    break;
                case 's':
                    if (mario.getX() == 522 && mario.getY() == 180) {
                        mario.setY(130);
                        mario.setMove(3);
                    }
                    break;
                case 'a':
                    if (mario.getX() != 92 && mario.getY() != 340) {
                        mario.setX(-43);
                        mario.setY(mario.getMove());
                        image = new ImageIcon("src/Projekt03/Controller/marioReverse.png").getImage();
                    }
                    break;
                case 'd':
                    if (mario.getX() != 522 && mario.getY() != 180) {
                        mario.setX(43);
                        mario.setY(-mario.getMove());
                        image = new ImageIcon("src/Projekt03/Controller/mario.png").getImage();
                    }
                    break;
            }

            view.paintMario(graphics, mario.getX(), mario.getY(),25,35, image);
            mario.resetLastPressed('L');

            if(ileklatek == 0) {
                for (Barrel barrel : beczki) {
                    if ((mario.getX() - 6) == barrel.getX()
                            && mario.getY() + 10 == barrel.getY()
                            || (mario.getX() - 6) == barrel.getX()
                            && (mario.getY() + 14) == barrel.getY()) {
                        mario.marioReset(92,340,false,3);
                        zycia -= 1;
                        ileklatek = 5;
                    }
                }
            }
            switch (zycia) {
                case 0 -> {
                    view.paintString(graphics, 200, 225, "DEFEAT", Color.WHITE);
                    thread = false;
                }
                case 1 -> view.printLives(graphics, 600, 200, 25, 70, new ImageIcon("src/Projekt03/Controller/onehearts.png").getImage());
                case 2 -> view.printLives(graphics, 600, 200, 25, 70, new ImageIcon("src/Projekt03/Controller/twohearts.png").getImage());
                case 3 -> view.printLives(graphics, 600, 200, 25, 70, new ImageIcon("src/Projekt03/Controller/threeHearts.png").getImage());
            }

            if (test) {
                view.paintString(graphics, 200, 225, "Victory", Color.YELLOW);
            }
/**
 * Predkosc gry
 * */
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if ((int) (Math.random() * 3 + 1) == 1 && (licznik % 2) == 0 || beczki.size() <= 3 && licznik%4 == 0) {
                beczki.add(new Barrel(-43,155,43));
            }
            if(speed>= 500 && (licznik %4) == 0){
                speed -= 1000 * 0.05;
            }
            if(ileklatek != 0){
                ileklatek--;
            }
            licznik++;
        }
    }
}