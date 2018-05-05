package graetap3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/** klasa , kt?ra pozwala na rysowanie element?w gry */
public class Rysowanie extends JPanel implements ActionListener, KeyListener, ComponentListener{
    private Mapa map;
    private Gracz player;
    
    private double peroid; // czestot. od�wiezania ekranu gry
    private Timer timer; // timer za�atwiajacy od�wie�anie
    private double scale;
    
    private Konfiguracja konfiguracja;
    //Rysowanie mapy gry
    Rysowanie(Konfiguracja konfiguracja)
    {  
        this.konfiguracja = konfiguracja;
        setFocusable(true);
        addKeyListener(this);
        scale = 1;
        setSize(konfiguracja.width, konfiguracja.height);
        setVisible(false);
        map = new Mapa("mapa",(int)scale, this);
        
        player = new Gracz(map);
        peroid = 1000/50; // 1000ms przez 50Hz


    }
    
    public void start()
    {
        setVisible(true);
        timer = new Timer((int)peroid, this);
        timer.setInitialDelay(0);
        timer.start();
    }
    
    public void stop()
    {
        timer.stop();
    }
    
    private void update(){
        player.update();
    }

    /** funkcja, kt?a rysuje gracza i mape */
    public void paint ( Graphics g ) {
       Insets b = getInsets();
       g.translate (b.left, b.top);
       map.draw(g);
       player.draw(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        
        this.repaint();
    }
    
    public void setScale(double newScale)
    {
        scale = newScale;
        map.setTileSize(newScale);
    }

    @Override
    public void keyTyped(KeyEvent e) {        
        char key = e.getKeyChar();
        
        //System.out.println(key);
        
        switch(key)
        {
            case 'w':
                //System.out.println("w g�r�");
                player.move(0, -0.5);
                break;
            case 's':
                //System.out.println("w d�");
                player.move(0, 0.5);
                break;
            case 'a':
                //System.out.println("w lewo");
                player.move(-0.5, 0);
                break;
            case 'd':
                //System.out.println("w prawo");
                player.move(0.5, 0);
                break;
            default:
                //System.out.println("inny klawisz");
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println(e);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println(e.getComponent().getSize());
        map.setTileSize(e.getComponent().getBounds().width/100);
        repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
