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
        map = new Mapa("mapa1", this);
        map.setSize(getSize().width, getSize().height);        
        player = new Gracz(konfiguracja);
        
        player.setMap(map);
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
        map.update();
        
        double playerX = player.getX();
        double playerY = player.getY();
        int playerWidth = ((int)(player.getWidth()+playerX+0.999999))-((int)playerX);
        int playerHeight = ((int)(player.getHeight()+playerY+0.999999))-((int)playerY);
        
        int[][] area = map.getSubMap((int)(player.getX()), (int)(player.getY()), playerWidth, playerHeight);
        
        int blueCounter = 0;
        int greenCounter = 0;
        Boolean colided = false;
        
        for( int kx=0; kx<playerWidth; kx++ )
        {
            for( int ky=0; ky<playerHeight; ky++ )
            {                
                if( area[ky][kx]==0 ) // black
                    continue;
                else if( area[ky][kx]==1 ) // blue
                {
                    blueCounter++;
                    if(blueCounter>1)
                    {
                        collision(area[ky][kx]);
                        colided = true;
                        break;
                    }
                }
                else if (area[ky][kx]==2) // green
                {
                    greenCounter++;    
                    if(greenCounter>1)
                    {
                        collision(area[ky][kx]);
                        colided = true;
                        break;
                    }         
                }
                
                double radius = player.getHeight()/2;
                double centerX = playerX-(int)playerX + player.getWidth()/2;
                double centerY = playerY-(int)playerY + player.getHeight()/2;
                
                double[] pointX = new double[]{kx+0.0, kx+0.5, kx+0.5, kx+1.0, kx+0.0, kx+1.0, kx+1.0, kx+0.0}; 
                double[] pointY = new double[]{ky+0.5, ky+0.0, ky+1.0, ky+0.5, ky+0.0, ky+0.0, ky+1.0, ky+1.0}; 
                
                for( int n=0; n<pointX.length; n++)
                    if( checkPoint(centerX, centerY, pointX[n], pointY[n], radius) )
                    {
                        collision(area[ky][kx]);
                        colided = true;
                        break;
                    }
                if(colided==true)
                    break;
            } 
            if( colided==true )
                break;
        }
        
        repaint();
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
       
        double scaleHeight = map.getPrefHeight()/map.getMapHeight();
        double scaleWidth = map.getPrefWidth()/map.getMapWidth();
         
        
    
    }
    
    public Boolean checkPoint( double aX, double aY, double bX, double bY, double radius)
    {
        return ( Math.sqrt( Math.pow(aX-bX, 2) + Math.pow(aY-bY, 2) )<radius );
    }
    
    public void collision( int block )
    {
        switch(block)
        {
            case 1: // blue
                player.setX(1.0);
                player.setY(1.0);
                player.resetVelocity();
                break;
            case 2: // green
                double prefWidth = map.getPrefWidth();
                double prefHeight = map.getPrefHeight();
                map = new Mapa("mapa2", this);
                map.setSize(prefWidth, prefHeight);
                player.setMap(map);
                player.setX(6.0);
                player.setY(1.0);   
                player.resetVelocity();             
                break;
            default:
                System.out.println("Błąd w rysowanie.collision()");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        
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
