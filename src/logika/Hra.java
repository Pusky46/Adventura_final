package logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan,
 * která inicializuje místnosti hry a vytváří seznam platných příkazů a instance tříd
 * provádějící jednotlivé příkazy. Vypisuje uvítací a ukončovací text hry. Také
 * vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRuksak(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazVypi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan, getBatoh()));
        platnePrikazy.vlozPrikaz(new PrikazOdomkni(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vitajte!\n" +
               "Nachádzate sa v príbehu o upírovi, ktorý sa stratil a potrebuje sa vrátiť späť do svojej svätine v hrade.\n" +
               "Napíšte 'nápoveda', ak si neviete poradiť, ako hrať ďalej.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Prívrženci gotiky Ti ďakujú za hru.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        if (herniPlan.isVyhra()){
            konecHry = true;
            textKVypsani = "Tramtadááá, úspešne si prešiel až do svojho hradu a konečne vidíš svoju rakev. Dobrú noc!";
        }
        if (herniPlan.isProhra()){
            konecHry = true;
            textKVypsani = "Nemáš klíč, bohužial zase sa nevyspíš, preto si rýchlo hladaj úkryt v tmavej uličke.";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní IPrikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     * @return the batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
    
    
}
