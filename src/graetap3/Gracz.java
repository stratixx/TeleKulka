package graetap3;

import java.awt.*;

/** lokalizacja gracza na planszy 
 * 
 * */

public class Gracz {
 
    private double x = 20.0;
    private double y = 20.0;
    private int width;
    private int height;
    
    private double velocity_x = 1.0;
    private double velocity_y = 0.0;
    private double acceleration_x = 0.0;
    private double acceleration_y = 0.2;
    
 
    private Mapa map;
 
    /**konstruktor gracza
     * 
     * @param _map
     */
    public Gracz(Mapa _map){
 
        map = _map;
        width = 10;
        height = 10;
    
    }
    
    public void move( double axisX, double axisY)
    {
        velocity_x += axisX;
        velocity_y += axisY;
    }
    
    public void update(){
    	x += velocity_x;
        y += velocity_y;
        velocity_x += acceleration_x - 0.1*velocity_x;
        velocity_y += acceleration_y - 0.1*velocity_y;
        
        if( x>(map.getUnscaledWidth()-width) )   
        {
            x = map.getUnscaledWidth()-width;
            velocity_x = 0;
        }
        if( y>(map.getUnscaledHeight()-height) )  
        {
            y = map.getUnscaledHeight()-height;
            velocity_y = 0;
        }
        if( x<0 )     
        {
            x = 0;
            velocity_x = 0;
        }
        if( y<0 )     
        {
            y = 0;
            velocity_y = 0;
        }
        
        map.update();
    }
  /**metoda kolorujaca gracza (kulke)
   * 
   * @param g
   */
    public void draw(Graphics g){
        double scale = map.getTileSize();
        g.setColor(Color.RED);
        g.fillOval((int)(x*scale), (int)(y*scale), (int)(width*scale), (int)(height*scale));
    }
 
}
 
