/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazZahod představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "zahod";
    private HerniPlan hPlan;
    private Batoh batoh;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor 
     *  
     *  @param plan herní plán, ve kterém se bude ve hře odehráva.
     *  @param batoh batoh, z ktorého hráč vyhadzuje predmety
     */
    public PrikazZahod(HerniPlan hPlan, Batoh batoh)
    {
        this.hPlan = hPlan;
        this.batoh = batoh;
    }

    /**
     *  Prevádza príkaz "zahod". Zkouší sa zahodiť zadaný predmet z batohu. Ak je zadaný predmetv
     *  batohu tak se vloží do aktuálního prostoru.
     *  Ak požadovaný predmět v batoh neni vypíše sa chybové hlášení.
     *
     *@param parametr - jmeno veci, kterou chceme zahodiť
     *@return správa, která se vypíše hráči
     */     
    public String proved(String... parametry) {
        if (parametry.length <1) {
            return "Ak sa rozhodneš zadať, co mám vyhodit, skúsim to odhodiť, \n" +
                    "pokud to nespravíš, tak sa len zbytočne zdržuješ.";
        }

        String nazev = parametry[0];       
        this.batoh = hPlan.getBatoh();
        Vec vec = batoh.odeberVecZBatohu(nazev);
        
        if (vec == null) {
            return "Sklamem ťa, to tvuj batoh neobsahuje!";
        }
        else {
            hPlan.getAktualniProstor().vlozVec(vec);
            return "Zahodil si " + nazev;
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

}
