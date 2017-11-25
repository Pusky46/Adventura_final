/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazVypi představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class PrikazVypi implements IPrikaz
{
   private static final String NAZEV = "vypi";
    private HerniPlan hPlan;   
    
    /**
     *  Konstruktor třídy
     *  
     * @param plan herní plán, ve vktorom sa bude "piť" 
     */    
    public PrikazVypi(HerniPlan plan) {
        this.hPlan = plan;
    }

    /**
     * Provádí příkaz "vypi". Zkouší vypiť krv, ktorá je v batohu.
     * Ak krv nie je v batohu vypíše sa chybové hlášení.
     * 
     * @param - nazov veci, ktorú chce vypít
     */
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // ak chýba druhé slovo (sousední prostor), tak ....
            return "Čo mám vypiť?";
        }

        if(parametry.length == 1) {
            switch (parametry[0]) {
                //vyhodnocuje se, či hodnota odpovedá nasledovní
                case "krv":  return krv();
                default: return "Čo chceš vypiť, ked nemáš čo!"; //a se nenašla krv v batohu
            }
        }
        return "Nic neprobehlo vo vypi.";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

    /**
     * Metoda prevádza, že vec (krv) ide vypít pouze pokud je již v batohu.
     */
    private String krv(){
        if(hPlan.getBatoh().obsahujeVecVBatohu("krv")){
            hPlan.getBatoh().odeberVecZBatohu("krv");
            return "Posilnil/-a si sa krvou.";
        }
        return "Nemas krv.";
    }

      

}
