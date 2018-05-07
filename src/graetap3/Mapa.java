package graetap3;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

 
public class Mapa{
 
    /**klasa , kt�ra wczytuje map� z pliku 
     * 
     * 
     * */
 
    private int[][] map;
    private int mapWidth;
    private int mapHeight;
    
    private Rysowanie masterWindow;
 
    private Konfiguracja conf;
    private double gravitation;
    private double prefWidth;
    private double prefHeight;
    
    //private double scaleWidth;
    //private double scaleHeight;
    
    /**
     * konstruktor mapy
     * @param s nazwa mapy
     */
    public Mapa(String s, Rysowanie masterWindow)
    {        
        this.masterWindow = masterWindow;
        conf = masterWindow.getConfig();
 
        prefWidth = conf.width;
        prefHeight = conf.height;
        
         try{
        	 FileInputStream fstream = new FileInputStream("mapy/"+s+".txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
             gravitation = Double.parseDouble(br.readLine());
             mapWidth = Integer.parseInt(br.readLine());
             mapHeight = Integer.parseInt(br.readLine());        
             
            //scaleWidth = conf.width/mapWidth;
            //scaleHeight = conf.height/mapHeight;
 
             map = new int[mapHeight][mapWidth];
             
             
             
             String delimiters = " ";
             for(int row = 0; row <mapHeight; row++){
                 String line = br.readLine();
                 String[] tokens = line.split(delimiters);
                 for(int col = 0; col <mapHeight; col++){
                     map[row][col] = Integer.parseInt(tokens[col]);
                 }
             }
 
         }
         catch(Exception e){
        	 //System.out.println("Map error: mordeczko, masz nullPointerException w Mapa.java w linii 58");
                 //e.printStackTrace();
         }
    }
    
    public void setSize( double width, double height)
    {
        prefWidth = width;
        prefHeight = height;
        //scaleWidth = prefWidth/mapWidth;
        //scaleHeight = prefHeight/mapHeight;
    }
    
    public void update(){
    }
 
    /** metoda kolorujaca mape 
     * w zale�no�ci jak� cyfr� zawiera mapa
     * 0-czarny
     * 1-niebieski
     * 2-zielony
     * @param g
     */
    public void draw(Graphics g){
        for(int row = 0; row <mapHeight; row++){
            for(int col = 0; col <mapHeight; col++){
                int rc = map[row][col];
                if(rc == 0){
                    g.setColor(Color.BLACK);
                }
                if(rc == 1){
                    g.setColor(Color.BLUE);
                }
                if( rc==2) {
                	g.setColor(Color.GREEN);
                }
                g.fillRect((int)((col*prefWidth)/mapWidth), (int)((row*prefHeight)/mapHeight), (int)(prefWidth/mapWidth+1.0), (int)(prefHeight/mapHeight+1.0));
             
            }
        }
    }
    
    
    public double getGravity()
    {
        return gravitation;
    }
    
    public int getMapWidth()
    {
        return mapWidth;
    }
    
    public int getMapHeight()
    {
        return mapHeight;
    }
    
    public double getPrefWidth()
    {
        return prefWidth;
    }
    
    public double getPrefHeight()
    {
        return prefHeight;
    }
}
 