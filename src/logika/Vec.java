/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelna;
    private String obrazek;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelna,String obrazek)
    {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.obrazek = obrazek;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Vrací název veci.
     */
    public String getNazev() {
        return nazev;
    }

    public String getObrazek() {
        return obrazek;
    }
    
    
       
    /**
     * Vec je prenositelna.
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
