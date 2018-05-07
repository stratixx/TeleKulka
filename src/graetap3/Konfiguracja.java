package graetap3;
import java.io.FileNotFoundException;
import java.io.IOException;
 
/**
 * Klasa czytaj�ca i prasuj�ca dane z pliku konfiguracyjnego
 *
 */
 
public class Konfiguracja {
    
    public int width, height;

    public int BallWidth;
    public int BallHeight;
    
    public double BallVelocity_x;
    public double BallVelocity_y;
    public double BallAcceleration_x;
    public double BallAcceleration_y;
    public double BallWallHitLossFactor;
    public double BallFrictionFactor;
    
    public double AnimationPeriod;
 
    public Konfiguracja(){
        ObslugaPlikow config2 = null;
 
        try {
            config2 = new ObslugaPlikow("konfiguracja.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        
        try {
            this.width = Integer.parseInt(config2.getSetting("PREFERRED WINDOW WIDTH"));
            this.height = Integer.parseInt(config2.getSetting("PREFERRED WINDOW HEIGHT"));
            this.BallWidth = Integer.parseInt(config2.getSetting("PREFERRED BALL WIDTH"));
            this.BallHeight = Integer.parseInt(config2.getSetting("PREFERRED BALL HEIGHT"));
            this.BallVelocity_x = Double.parseDouble(config2.getSetting("BALL VELOCITYX"));
            this.BallVelocity_y = Double.parseDouble(config2.getSetting("BALL VELOCITYY"));
            this.BallAcceleration_x = Double.parseDouble(config2.getSetting("BALL ACCELERATIONX"));
            this.BallAcceleration_y = Double.parseDouble(config2.getSetting("BALL ACCELERATIONY"));
            this.AnimationPeriod = Double.parseDouble(config2.getSetting("ANIMATION PERIOD"));
            this.BallWallHitLossFactor = Double.parseDouble(config2.getSetting("BallWallHitLossFactor"));
            this.BallFrictionFactor = Double.parseDouble(config2.getSetting("BallFrictionFactor"));
            
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
 
}