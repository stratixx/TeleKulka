package graetap3;

import java.awt.Dimension;
import javax.swing.JFrame;

/**glowny program, wywo�ywanie poszczeg�lnych element�w, pojawienie si� menu i gry */

public class Ramka extends JFrame {

	public Ramka(){
            
		setSize(600,600);
		setTitle("KULKA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		Panel panel= new Panel(this);
		add(panel);
		panel.setVisible(true);
		//Rysowanie rys= new Rysowanie();
		//add(rys);
		//rys.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Ramka ramka = new Ramka();
		ramka.setVisible(true);
	}
}
