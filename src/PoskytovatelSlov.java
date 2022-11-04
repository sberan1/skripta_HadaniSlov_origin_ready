import java.util.ArrayList;
import java.util.Random;

/**
 * Tato třída zpřístupňuje grafickému rozhraní slovo, které se má hádat. Slovo je
 * instancí třídy HledaneSlovo a obsahuje vedle vlastního slova i nápovědu,
 * která se zobrazí.
 * Třída dále poskytuje informace o autorovy a o verzi.
 * 
 * @author  Luboš Pavlíček 
 * @version 1.0
 */
public class PoskytovatelSlov {
   
    // private HledaneSlovo slovo;
     private ArrayList<HledaneSlovo> slova;
     private int index;
     private Random nahoda;
     
    /**
     * konstruktor pro vytvoření instance třídy pro poskytování slov
     */
    public PoskytovatelSlov() {

        slova = new ArrayList<HledaneSlovo>();
        slova.add(0, new HledaneSlovo("nápad","něco co mi prijde na mysl"));
        slova.add(1, new HledaneSlovo("žirafa", "leze po stromech"));
        slova.add(2, new HledaneSlovo("Praha", "Hlavni mesto CR"));

        index = 0;

        nahoda = new Random();
    }

    /**
     * Metoda vrací slovo k hádání jako instanci třídy HledaneSlovo (tj. ke
     * slovu je připojena nápověda). Pokud není již další slovo k dispozici, vráti
     * hodnotu null
     * 
     * @return     slovo k hádání
     */
    public HledaneSlovo getSlovo() {
      // return slovo;
        HledaneSlovo slovo = null;
       /* if (index < slova.size()) {
            slovo = slova.get(index);
            index++;
        }
        */

        if (slova.size() > 0){
            int index1 = nahoda.nextInt(slova.size());
            slovo = slova.get(index1);
            slova.remove(index1);
        }

        return slovo;
    }
    
    /**
     * Metoda vrací jméno autora programu - jméno se zobrazuje v grafickém rozhraní
     * 
     * @return     jméno autora, např. "L. Pavlicek"
     */ 
    public String getAutor() {
        return "bers06";
    }
    
    /**
     * Metoda vrací verzi programu - ta se zobrazuje v grafickém rozhraní
     * 
     * @return     verze programu, např. "1.0"
     */ 
    public String getVerze() {
        return "1.1";
    }
   
}
