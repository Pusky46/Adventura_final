/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazVezmi představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    
    private HerniPlan hPlan;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazSeber(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda preváza príkaz "seber". Zbiera veci z priestoru, ktoré sa tam nachádzajú a sú prenositelné. 
     * Následne ich vkladá do batohu/ruksaku. 
     * 
     * @parametry - názvy vecí v priestore
     * @return - text, ktorý se hráčovi vypíše
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "neviem, čo mám uchmatnúť";
        }

        String nazev = parametry[0];
        Vec vec = hPlan.getAktualniProstor().odeberVec(nazev);
        Batoh batoh = hPlan.getBatoh();
        
        if (vec == null) {
            return "vec '" + nazev + "' tu není";
        }
        
        if (!vec.isPrenositelna()) {
            hPlan.getAktualniProstor().vlozVec(vec);
            return "vec " + nazev + " je nad tvoje sily.";
        }
          
        if (batoh.vlozVec(vec) == 1) {
            return "vec " + nazev + " si vložil do ruksaku";
        } else if (batoh.vlozVec(vec) == 4){
            hPlan.getAktualniProstor().vlozVec(vec);
            return "Vec " + nazev + " si nemožeš zobrať";          
        } else {
            hPlan.getAktualniProstor().vlozVec(vec);
            return "Tvôj batúžek je plný. Niečo vyhoď.";
        }
    }
    
	/**
	 * Metoda vrací název príkazu (slovo které používa hráč pro jeho vyvoláni)
	 * 
	 * @return natev prikazu
	 */
	public String getNazev() {
	    return NAZEV;
	}

    //== Soukromé metody (instancí i třídy) ========================================

}
