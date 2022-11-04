import java.text.Normalizer;

/**
 * Třída HledaneSlovo popisuje slovo, které se má hádat a nápovědu pro toto slovo
 * 
 * @author  Luboš Pavlíček, pavlicek @ vse.cz
 * @version 1.0
 */
public class HledaneSlovo {
    private String slovo;           // slovo k hádání
    private String napoveda;        // napověda k tomuto slovu
    private char [] odhalenaPismena;    // obsahuje odhalená písmena
    
    /**
     * Konstruktor pro vytváření slova/výrazu k hádání spojeného s nápovědou pro toto slovo.
     * Slovo nesmí obsahovat číslice a speciální znaky (tečky, pomlčky, zavináče ..). Přípustné
     * jsou pouze znaky české abecedy a mezera. Mezera se nehádá, ale ihned zobrazí.
     * 
     * @param   slovo       slovo, které se má hádat,
     * @param   napoveda    nápověda, která se zobrazí uživateli, který hádá toto slovo
     */
    public HledaneSlovo(String slovo, String napoveda) {
        this.slovo=slovo;
        this.napoveda=napoveda;
        odhalenaPismena=new char [slovo.length()];
        for (int i=0; i<slovo.length(); i++) {
            if (slovo.charAt(i) == ' ') {
                odhalenaPismena[i]=' ';
            }
            else {
                odhalenaPismena[i]='_';
            }
        }
    }

    /**
     * Metoda vrací nápovědu ke slovu
     * 
     * @return      nápověda ke slovu
     */
    public String getNapoveda() {
        return napoveda;
    }
    
    /**
     * metoda vrací pole znaků k zobrazení v grafické rozhraní. Tato metoda se volá 
     * z grafické třídy vždy na začátku hádání slova a poté vždy po metodě stisknutoPismeno
     * 
     * @return   pole znaků k zobrazení
     */
    public char [] kZobrazeni () {
        return (char []) odhalenaPismena.clone();
    }
    
    /**
     * metoda zjišťuje, zda uživatel již uhodl všechny písmena ve slově/výrazu. Volá se obvykle po
     * metodě kZobrazeni
     *
     * @return   true, pokud jsou již uhodnuty všechny písmena, jinak false
     */
    public boolean nalezenoVse() {
        boolean nalezeno=true;
        for (int i=0; i<odhalenaPismena.length; i++) {
            if (odhalenaPismena[i]=='_') {
                nalezeno=false;
                break;
            }
        }
        return nalezeno;
    }
    
    /**
     * metoda se volá vždy, když uživatel v grafickém rozhraní si vybere nějaké písmeno
     * 
     * @param pismeno  - znak (písmeno), který zvolil uživatel
     * @return           true, pokud písmeno bylo nalezeno v hledaném slově
     */
    
    public boolean stisknutoPismeno(char pismeno) {
        String temp = Normalizer.normalize(slovo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        temp = temp.toLowerCase();
        boolean nalezeno = false;
        for (int i=0; i< temp.length(); i++) {
            if (((temp.charAt(i) == pismeno) || (slovo.charAt(i) == pismeno))& (odhalenaPismena[i]=='_')) {
                odhalenaPismena[i]=slovo.charAt(i);
                nalezeno = true;
            }
        }
        return nalezeno;
    }
    
    /**
     * Metoda vrací minimální potřebný počet stisku písmen pro odhalení slova/výrazu. Např. pro slovo
     * "velbloud" je to 7, pro slovo "veverka" je to 5, pro slovo "Olomouc" je to 5, pro výraz 
     * "Hradec Králové" je to 10.
     * 
     * @return     minimální počet písmen pro uhodnutí slova/výrazu
     */
    
    public int getMinPokusu() {
        return 1;
    }
    
}
