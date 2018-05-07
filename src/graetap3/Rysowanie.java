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
    
    private Konfiguracja konfiguracja;
    //Rysowanie mapy gry
    Rysowanie( JFrame masterFrame, Konfiguracja konfiguracja)
    {  
        this.konfiguracja = konfiguracja;
        setFocusable(true);
        addKeyListener(this);
        
        Rectangle fBounds = masterFrame.getBounds();         
        Insets fInsets = masterFrame.getInsets();
        //setSize(frame.getSize());
        
        setSize(fBounds.width-fInsets.left-fInsets.right, fBounds.height-fInsets.top-fInsets.bottom);
        //setSize(800, 600);
        setVisible(false);
        map = new Mapa("mapa5", this);
        map.setSize(getSize().width, getSize().height);
        
        player = new Gracz(map, konfiguracja);
        

    }
    
    public void start()
    {
        setVisible(true);
        timer = new Timer((int)konfiguracja.AnimationPeriod, this);
        timer.setInitialDelay(500);
        timer.start();
    }
    
    public void stop()
    {
        timer.stop();
    }
    
    private void update(){
        player.update();
    }
    
    public Konfiguracja getConfig()
    {
        return konfiguracja;
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
        JFrame frame = (JFrame)e.getComponent();
        Rectangle fBounds = frame.getBounds();         
        Insets fInsets = frame.getInsets();
        //setSize(frame.getSize());
        
        setSize(fBounds.width-fInsets.left-fInsets.right, fBounds.height-frame.getInsets().top-frame.getInsets().bottom);
        map.setSize(getSize().width, getSize().height);
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
