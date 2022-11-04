
/**
 * Základní třída aplikace pro Hádání slov. Vytvoří instance tříd PoskytovatelSlov a GrafikaHadani
 * a spustí celou aplikaci
 * 
 * @author  Luboš Pavlíček
 * @version 19. května 2004
 */
public class HadaniSlov
{
	private GrafikaHadani gui;
	private PoskytovatelSlov poskytovatel;

	/**
	 * Konstruktor pro vytváření instance třídy HadaniSlov. Pro zobrazení je potřeba zavolat
	 * metodu show()
	 */
	public HadaniSlov()
	{
		poskytovatel = new PoskytovatelSlov();
		gui = new GrafikaHadani(poskytovatel);
	}

	/**
	 * metoda show zobrazí grafikou aplikace pro hádání slov
	 * 
	 */
	public void show ()	{
		gui.setVisible();
	}

}
