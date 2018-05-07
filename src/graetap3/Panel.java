package graetap3;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
 /**klasa zawieraj�ca menu gry*/
public class Panel extends JPanel implements ActionListener, ComponentListener {
	
        private Ramka masterJFrame;
	private JButton bGraj,bInstrukcja,bWyniki,bWyjdz;
	//
	private JMenuBar menu;
	private JMenu menuGra,menuInformacje; 
	private JMenuItem mGraj,mWyniki,mInstrukcja,mWyjdz,mRestart;
	private Rysowanie oknoGry;
	//
	private JFrame OInstrukcja,OWyniki,OUkonczonaGra;
	//
	private JLabel lWyniki,lInstrukcja,lGra,lUkonczonaGra;
	private JButton bWynikiOk,bInsturkcjaOk,bUkonczonaGraWprowadz;
	private JTextField tUkonczonaGraWprowadzImie;
	private Object sImie;
	//
        
        private Konfiguracja konfiguracja;
        //private double scale = 1;
	
	Panel( Ramka masterJFrame )
        {
            this.masterJFrame = masterJFrame;
            
            konfiguracja = masterJFrame.getConfig();
		setSize(konfiguracja.width, konfiguracja.height);
		
	 /**okno instrukcji*/
		
		OInstrukcja = new JFrame();
		OInstrukcja.setSize(200,100);
		OInstrukcja.setTitle("Instrukcja");
		OInstrukcja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OInstrukcja.setLayout(null);
		OInstrukcja.setLocationRelativeTo(null);
		OInstrukcja.setVisible(false);
		
		lInstrukcja=new JLabel("Instrukcja...");
		lInstrukcja.setBounds(10, 10, 150, 20);
		OInstrukcja.add(lInstrukcja);
		
		bInsturkcjaOk=new JButton("Ok");
		bInsturkcjaOk.setBounds(10, 40, 150, 20);
		OInstrukcja.add(bInsturkcjaOk);
		bInsturkcjaOk.addActionListener(this);
		
		/** okno wynikow*/
		
		OWyniki=new JFrame();
		OWyniki.setSize(200,100);
		OWyniki.setTitle("Wyniki");
		OWyniki.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OWyniki.setLayout(null);
		OWyniki.setLocationRelativeTo(null);
		OWyniki.setVisible(false);
		
		lWyniki=new JLabel("Najlepsze Wyniki:....");
		lWyniki.setBounds(10, 10, 150, 20);
		OWyniki.add(lWyniki);
		
		bWynikiOk=new JButton("Ok");
		bWynikiOk.setBounds(10, 40, 150, 20);
		OWyniki.add(bWynikiOk);
		bWynikiOk.addActionListener(this);
		
		/**okno konca gry i wprowadzenia imienia */
		OUkonczonaGra = new JFrame();
		OUkonczonaGra.setSize(240,200);
		OUkonczonaGra.setTitle("Ukonczona gra!");
		OUkonczonaGra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OUkonczonaGra.setLayout(null);
		OUkonczonaGra.setLocationRelativeTo(null);
		OUkonczonaGra.setVisible(false);
		
		lUkonczonaGra=new JLabel("Ukonczyles gre z wynikiem: ...!");
		lUkonczonaGra.setBounds(10, 10, 200, 20);
		OUkonczonaGra.add(lUkonczonaGra);
		
		bUkonczonaGraWprowadz=new JButton("Wprowadz imie");
		bUkonczonaGraWprowadz.setBounds(10, 80, 200, 20);
		OUkonczonaGra.add(bUkonczonaGraWprowadz);
		bUkonczonaGraWprowadz.addActionListener(this);
		
		tUkonczonaGraWprowadzImie=new JTextField();
		tUkonczonaGraWprowadzImie.setBounds(10, 40,200, 20);
		OUkonczonaGra.add(tUkonczonaGraWprowadzImie);
		tUkonczonaGraWprowadzImie.addActionListener(this);
		tUkonczonaGraWprowadzImie.setToolTipText("Wprowadz imie");
		
	    /**ustawienie guzikow*/
		
		bGraj=new JButton("Graj");
		bGraj.setBounds(getWidth()/2-50, 10, 100, 30);
		add(bGraj);
		bGraj.addActionListener(this);
		
		bInstrukcja=new JButton("Instrukcja");
		bInstrukcja.setBounds(getWidth()/2-50, 40, 100, 30);
		add(bInstrukcja);
		bInstrukcja.addActionListener(this);
		
		bWyniki=new JButton("Wyniki");
		bWyniki.setBounds(getWidth()/2-50, 70, 100, 30);
		add(bWyniki);
		bWyniki.addActionListener(this);
		
		bWyjdz=new JButton("Wyjdz");
		bWyjdz.setBounds(getWidth()/2-50, 100, 100, 30);
		add(bWyjdz);
		bWyjdz.addActionListener(this);
		
	
		lGra=new JLabel("Gramy...");
		lGra.setBounds(10, 10, 100, 20);
		add(lGra);
		lGra.setVisible(false);
		
		
	}
        
        /**opisanie zachowania programu po wci�ni�ciu poszczeg�lnych guzik�w*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object zrodlo = arg0.getSource();
		
		if(zrodlo==bGraj )
		{
			setLayout(null);
			oknoGry = new Rysowanie( masterJFrame, konfiguracja);
                        masterJFrame.addComponentListener(oknoGry);
                        masterJFrame.addComponentListener(this);
			add(oknoGry);
			bGraj.setVisible(false);
			bInstrukcja.setVisible(false);
			bWyniki.setVisible(false);
			bWyjdz.setVisible(false);
                        
			oknoGry.start(); // Poczatek gry
                        
			//lGra.setVisible(true);
			
		    //this.setVisible(false);
			//oknoGry = new Rysowanie();
			//oknoGry.setVisible(true);
		}
		else if(zrodlo==tUkonczonaGraWprowadzImie )
		{
			//OUkonczonaGra.setVisible(false);
			String w=tUkonczonaGraWprowadzImie.getText();
			System.out.println(w);
			sImie = JOptionPane.showInputDialog("Wprowadz imie");
			
			OWyniki.setVisible(true);
								
		}
		else if(zrodlo==bInstrukcja )
			OInstrukcja.setVisible(true);
		else if(zrodlo==bWyniki || zrodlo==mWyniki) {
			JOptionPane.showMessageDialog(null, sImie);
			OWyniki.setVisible(true);
		    
		}
		else if(zrodlo==bWyjdz )
		{
			int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz opuscic gre?", "Pytanie", JOptionPane.YES_NO_OPTION);
			if ( odp==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			else if (odp == JOptionPane.NO_OPTION)
				
				bGraj.setVisible(true);
			
		}
		else if(zrodlo==bInsturkcjaOk)
			OInstrukcja.setVisible(false);
		else if(zrodlo==bWynikiOk)
			OWyniki.setVisible(false);
		else 
			System.out.println("actionListener: else: "+zrodlo.toString());
		
		System.out.println("actionListener: ");
		
	}

    @Override
    public void componentResized(ComponentEvent e) {
        JFrame frame = (JFrame)e.getComponent();
        Rectangle fBounds = frame.getBounds();         
        Insets fInsets = frame.getInsets();
        //setSize(frame.getSize());
        
        setSize(fBounds.width-fInsets.left-fInsets.right, fBounds.height-frame.getInsets().top-frame.getInsets().bottom);
                
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
