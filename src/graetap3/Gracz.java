package graetap3;

import java.awt.*;

/** lokalizacja gracza na planszy 
 * 
 * */

public class Gracz {
 
    private double x = 1.0;
    private double y = 1.0;
    private int width;
    private int height;
    
    private double velocity_x ;
    private double velocity_y ;
    private double acceleration_x ;
    private double acceleration_y ;
    
    private Konfiguracja conf;
 
    private Mapa map;
 
    /**konstruktor gracza
     * 
     * @param _map
     */
    public Gracz(Konfiguracja conf){
 
        this.conf = conf;
        width = conf.BallWidth;
        height = conf.BallHeight;
       
        velocity_x  = conf.BallVelocity_x;
        velocity_y = conf.BallVelocity_y ;
        acceleration_x = conf.BallAcceleration_x;
        
    
    }
    
    public void move( double axisX, double axisY)
    {
        velocity_x += axisX;
        velocity_y += axisY;
    }
    
    public void update(){
    	x += velocity_x*conf.AnimationPeriod/1000;
        y += velocity_y*conf.AnimationPeriod/1000;
        velocity_x += acceleration_x*conf.AnimationPeriod/1000 - velocity_x*conf.BallFrictionFactor;
        velocity_y += map.getGravity()*conf.AnimationPeriod/1000 - velocity_y*conf.BallFrictionFactor;
        
        if( x>(map.getMapWidth()-width) )   
        {
            x = map.getMapWidth()-width;
            velocity_x *= -conf.BallWallHitLossFactor;
        }
        if( y>(map.getMapHeight()-height) )  
        {
            y = map.getMapHeight()-height;
            velocity_y *= -conf.BallWallHitLossFactor;
        }
        if( x<0.0 )     
        {
            x = 0;
            velocity_x *= -conf.BallWallHitLossFactor;
        }
        if( y<0.0 )     
        {
            y = 0;
            velocity_y *= -conf.BallWallHitLossFactor;
        }
    }
    
    public void setMap( Mapa newMap )
    {
        map = newMap;
        acceleration_y = map.getGravity();
    }
  /**metoda kolorujaca gracza (kulke)
   * 
   * @param g
   */
    public void draw(Graphics g){
        double scaleHeight = map.getPrefHeight()/map.getMapHeight();
        double scaleWidth = map.getPrefWidth()/map.getMapWidth();
        
        g.setColor(Color.RED);
        g.fillOval((int)(x*scaleWidth), (int)(y*scaleHeight), (int)(width*scaleWidth), (int)(height*scaleHeight));
    }
 
}
 
