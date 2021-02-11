package BananaShooter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Display extends JPanel implements ActionListener {

	private Timer timer;
    private Pesawat spaceship;
    private List<Pisang> pisangs;
    private boolean ingame;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;

    
    private final int[][] pos = {
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239},
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30},
        {820, 128}, {590, 80}, {600, 130},
        {820, 128}, {490, 170}, {570, 150},
        {590, 80}, {560, 45}, {940, 59},
        {820, 128}, {490, 170}, {700, 30},
        {590, 80}, {560, 45}, {900, 259},
        {820, 128}, {900, 259}, {700, 30},
        {2380, 29}, {450, 59}, {380, 89},
        {780, 109}, {580, 139}, {510, 139},
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {590, 80}, {560, 45}, {900, 259},
        {820, 128}, {490, 170}, {700, 30},
        {590, 80}, {560, 45}, {900, 259},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30},
        {820, 128}, {590, 80}, {600, 130}
    };

    public Display() {

        initBoard();
        
    }
    
    private void initBoard() {
    	
    			
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(new Color(33, 182, 168));
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        spaceship = new Pesawat(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAliens() {
        
        pisangs = new ArrayList<>();

        for (int[] p : pos) {
            pisangs.add(new Pisang(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else if(pisangs.size() == 0){
        	
        	drawYouWin(g);
        	
        }else {
        	
            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }

        List<Peluru> ms = spaceship.getPelurus();

        for (Peluru peluru : ms) {
            if (peluru.isVisible()) {
                g.drawImage(peluru.getImage(), peluru.getX(), 
                        peluru.getY(), this);
            }
        }

        for (Pisang pisang : pisangs) {
            if (pisang.isVisible()) {
                g.drawImage(pisang.getImage(), pisang.getX(), pisang.getY(), this);
            }
        }

        g.setColor(Color.white);
        g.drawString("Press F1 to Activate Music", 230, 15);
        g.drawString("------ "+ pisangs.size() + " Banana(s) Remaining! ------ ", 5, 15);
        
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        
        
    }
    
    private void drawYouWin(Graphics g) {

        String msg = "Congratulations! You Won The Game!";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        
        updateShip();
        updateMissiles();
        updateAliens();

        checkCollisions();

        repaint();
    }

    private void inGame() {

        if (!ingame) {
        	timer.stop();
        }
    }

    private void updateShip() {

        if (spaceship.isVisible()) {
            
            spaceship.move();
        }
    }

    private void updateMissiles() {

        List<Peluru> ms = spaceship.getPelurus();

        for (int i = 0; i < ms.size(); i++) {

            Peluru m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        if (pisangs.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < pisangs.size(); i++) {

            Pisang a = pisangs.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                pisangs.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = spaceship.getBounds();

        for (Pisang alien : pisangs) {
            
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
                spaceship.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        List<Peluru> ms = spaceship.getPelurus();

        for (Peluru m : ms) {

            Rectangle r1 = m.getBounds();

            for (Pisang pisang : pisangs) {

                Rectangle r2 = pisang.getBounds();

                if (r1.intersects(r2)) {
                    
                    m.setVisible(false);
                    pisang.setVisible(false);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }
    }
	
}
