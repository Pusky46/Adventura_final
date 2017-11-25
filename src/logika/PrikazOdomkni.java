/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazOdomkni představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class PrikazOdomkni implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odomkni";
    private HerniPlan hPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  @param plan, herní plán, po kterém se ve hře prochází
     */
    public PrikazOdomkni(HerniPlan plan)
    {
        this.hPlan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda prevádza príkaz "odomkni". Tento príkaz sa dá použiť len v priestore hrad.
     * Ak batoh hráča obsahuje kľúč hru vyhral.
     * Ak hráč nemá kľúč, hra končí a prehral.
     *
     *@param parametry - názov veci
     *@return text, ktorý sa hráčovi vypíše
     */ 
    public String proved(String... parametry) {
        Prostor aktualniProstor = hPlan.getAktualniProstor();
        if (aktualniProstor.getNazev().equals("hrad")) {
            if(hPlan.getBatoh().obsahujeVecVBatohu("klíč")){
             hPlan.setVyhra(true); 
             return "Vyhral jsi";
            }            
            else{   
             hPlan.setProhra(true);
             return "Nemáš klíč.";
            }
        }
       
        return "Nejsi v svojom milovanom hrade.";
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
