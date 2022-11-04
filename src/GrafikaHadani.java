import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Třída zajištuje grafické rozhraní pro hádání slov
 * 
 * @author  Luboš Pavlíček
 * @version 1.0
 */
public class GrafikaHadani
{
    private PoskytovatelSlov poskytovatel;
    private HledaneSlovo slovo;
    private int pocetPokusu;
    private int pocetChyb;

    private JFrame okno;
    private JTextField[] pismenaNaPlose;
    private JLabel labelNapoveda;
    private JTextField poleSPoctemPokusu;
    private JTextField poleSPoctemChyb;
    private JPanel panelSeSlovem;

    /**
     * Konstruktor vytvari instanci teto tridy s grafickym rozhranim.
     * 
     * @param poskytovatel instance, ktera bude poskytovat tride slova k hadani.
     */
    public GrafikaHadani(PoskytovatelSlov poskytovatel)
    {
        this.poskytovatel=poskytovatel;
        okno = new JFrame();
        labelNapoveda = new JLabel();
        JPanel horniPanel    = new JPanel();
        JPanel prostredniPanel=new JPanel();
        panelSeSlovem = new JPanel();
        JPanel panelSTlacitky= new JPanel();
        
        JPanel hlavniPanel = (JPanel)okno.getContentPane();
        hlavniPanel.add(horniPanel,BorderLayout.NORTH);
        hlavniPanel.add(prostredniPanel);
        hlavniPanel.add(panelSTlacitky,BorderLayout.SOUTH);
        
        prostredniPanel.add(panelSeSlovem);
        prostredniPanel.add(labelNapoveda);
        prostredniPanel.setLayout(new GridLayout(2,1));
        prostredniPanel.setBorder(BorderFactory.createTitledBorder("hledané slovo"));
        
        poleSPoctemPokusu = new JTextField(5);
        poleSPoctemPokusu.setHorizontalAlignment(JTextField.RIGHT);
        poleSPoctemPokusu.setEditable(false);
        poleSPoctemPokusu.setBackground(Color.WHITE);
        poleSPoctemChyb = new JTextField(5);
        poleSPoctemChyb.setHorizontalAlignment(JTextField.RIGHT);
        poleSPoctemChyb.setEditable(false);
        poleSPoctemChyb.setBackground(Color.WHITE);
        
        horniPanel.add(new JLabel(" Počet pokusů "));
        horniPanel.add(poleSPoctemPokusu);
        horniPanel.add(new JLabel("     Počet chyb "));
        horniPanel.add(poleSPoctemChyb);
        
        String [] popiskyTlacitek = { "a","b","c","č","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","ř","s",
            "š","t","u","v","w","x","y","z","ž"};
        ActionListener obsluhaPismen = new StiskPismena();
        for (int i=0; i<popiskyTlacitek.length; i++) {
            JButton tlacitko = new JButton(popiskyTlacitek[i]);
            panelSTlacitky.add(tlacitko);
            tlacitko.addActionListener(obsluhaPismen);
        }
        panelSTlacitky.setLayout(new GridLayout(popiskyTlacitek.length/8 , 8));
        panelSTlacitky.setBorder(BorderFactory.createTitledBorder("písmena"));
        
        okno.setTitle("Hádání slov,   autor: "+poskytovatel.getAutor()+", verze: "+
                poskytovatel.getVerze());
                
        //okno.pack();
        okno.setSize(600,250);
            
    }
    
    /**
     * Metoda slouží pro inicializaci stavu pro hádání nového slova. Metoda se volá na začátku
     * a poté vždy, kdy uživatel uhodne slovo.
     */
    
    private void noveSlovo() {
        pocetChyb=0;
        pocetPokusu=0;
        
        slovo = poskytovatel.getSlovo();
        if (slovo == null) {
            JOptionPane.showMessageDialog(null, 
                "Bohužel již nejsou další slova k hádání",
                "Konec", JOptionPane.INFORMATION_MESSAGE );
            return;
        }
        
        labelNapoveda.setText(" nápověda: "+slovo.getNapoveda());
        
        char [] zobrazit = slovo.kZobrazeni();
        pismenaNaPlose = new JTextField [ zobrazit.length ];
        panelSeSlovem.removeAll();
        for (int i=0; i<zobrazit.length; i++) {
            pismenaNaPlose[i]=new JTextField(2);
            pismenaNaPlose[i].setEditable(false);
            pismenaNaPlose[i].setHorizontalAlignment(JTextField.CENTER);
            panelSeSlovem.add(pismenaNaPlose[i]);
        }
        zobrazStavSlova();
        panelSeSlovem.updateUI();
    }

    /**
     * Metoda zobrazí grafické okno na obrazovce.
     */
    void setVisible()
    {
	if (slovo == null) {
	    noveSlovo();
	}
        okno.setVisible(true);
    }
    
    /**
     * ovladač pro tlačítka s písmeny. Po stisknutí tlačítka se vyvolá metoda actionPerformed
     * a provedou v ní uvedené akce.
     */
    class StiskPismena implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            pocetPokusu++;
            char pismeno= event.getActionCommand().charAt(0);
            if (!slovo.stisknutoPismeno(pismeno)) {
                pocetChyb++;
            }
            zobrazStavSlova();
            if (slovo.nalezenoVse()) {
                JOptionPane.showMessageDialog(null, 
                    "Uhodl jste slovo na "+pocetPokusu+" pokusu,\n"+
                    "udělal jste "+pocetChyb+" chyb\n"+
                    "pro uhodnutí postačovalo " + slovo.getMinPokusu()+" pokusu",
                    "Vitezstvi", JOptionPane.INFORMATION_MESSAGE );
                noveSlovo();
            }
        }
    }
    
    
    /**
     * metoda zobrazí aktuální stav hádání slova
     */
    private void zobrazStavSlova() {
        poleSPoctemPokusu.setText(""+pocetPokusu);
        poleSPoctemPokusu.setText(""+pocetPokusu);
        poleSPoctemChyb.setText(""+pocetChyb);
        char [] zobrazit = slovo.kZobrazeni();
        for (int i=0; i<zobrazit.length; i++) {
            if (zobrazit[i] != ' ') {
                pismenaNaPlose[i].setText(Character.toString(zobrazit[i]));
                pismenaNaPlose[i].setBackground(Color.WHITE);
            }
        }
    }
    
}
