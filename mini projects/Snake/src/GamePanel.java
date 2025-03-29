import java.awt.*;
import java.util.Random;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Action {
    GamePanel(){

    }
    public void startgame(){

    }
    public void paintComponent(Graphics g){

    }
    public void draw(Graphics g){

    } 
    public void move(){

    }
    public void checkApple(){

    }
    public void checkCollision(){

    }
    public void gameover(Graphics g){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public Object getValue(String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValue'");
    }

    @Override
    public void putValue(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putValue'");
    }
    public class MyKeyAdaptar extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){

        }
    }
}
