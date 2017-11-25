/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazRuksak představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class PrikazRuksak implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "ruksak";
    
    private HerniPlan hPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  
     *  @param herní plán, v ktorom sa bude vypisovať obsah batohu.
     */
    public PrikazRuksak(HerniPlan plan)
    {
        this.hPlan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
   /**
    * Metoda provádi príkaz "ruksak". Vypisuje veci, ktoré hráč zobral a sú v batohu.
    * 
    * @param ak zadá špatní príkaz vypíše sa chybové hlášení
    */
   public String proved(String... parametry) {
       Batoh batoh = hPlan.getBatoh();
       
        if (parametry.length > 0) {
            return "Cože? Nechápu tě.";
        } else {            
            return batoh.nazvyVeciVBatohu();
        }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}