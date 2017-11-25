/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Instance třídy {@code Batoh} představují ...
 *
 * @author    Petra Puškárová
 * @version   ZS 2016/2017
 */
public class Batoh
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    private static final int SLOTS = 4;
    
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Map<String, Vec> seznamVeci;
    
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
    
    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *
     */
    public Batoh()
    {
        seznamVeci = new HashMap<>();
    }

    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    /**
     * Přidá věc do batohu, pokud sa tam vojde
     * @return 1 ked sa vec vloží
     * @return 2 ak je neprenositelná
     * @return 3 ak neni dostatok miesta v batohu
     */
    public int vlozVec (Vec vec) {
        if (seznamVeci.size() < SLOTS && vec.isPrenositelna()) {
            seznamVeci.put(vec.getNazev(), vec);
            return 1;
        } else if (!vec.isPrenositelna()) {
            return 2;
        } else {
            return 3;
        }
    }
    
     /**
     * Metoda odebere vec z batohu.
     */
    public Vec odeberVecZBatohu(String nazev) {
        
        Vec zahozenaVec = null;
        if (seznamVeci.containsKey(nazev)) {
            zahozenaVec = seznamVeci.get(nazev);
            seznamVeci.remove(nazev);
        }
        return zahozenaVec;
  
    }

    public Map<String, Vec> getSeznamVeci() {
        return seznamVeci;
    }
    
    

    /**
     * Napíše informace o veciach v batohu
     */
    public boolean obsahujeVecVBatohu (String jmenoVeci) {
        return seznamVeci.containsKey(jmenoVeci);
    }

    /**
     * Vrátí vec z batohu
     */
    public Vec vyberVecVBatohu (String jmenoVeci) {
        Vec nalezenaVec;
        if (seznamVeci.containsKey(jmenoVeci)) {
            nalezenaVec = seznamVeci.get(jmenoVeci);
            seznamVeci.remove(jmenoVeci);
            return nalezenaVec;
        }
        return null;
    }

    /**
     * Vypíše veci, které jsou v batohu
     */
    public String nazvyVeciVBatohu() {
        String nazvy = "veci v batohu: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;

    }

    /**
     * Vypíše kapacitu batohu (4)
     */
    public int getKapacitaBatohu() {
        return SLOTS;

    }
    
    /**
     * Vrací počet volných míst v batohu
     * 
     * @ return vrátí číslo, kolik věcí ještě může hráč přidat
     */
    public int jeVolneMisto(){
        return 1 - seznamVeci.size();
    }
    
    
     
    
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A TŘÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         Batoh instance = new Batoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
