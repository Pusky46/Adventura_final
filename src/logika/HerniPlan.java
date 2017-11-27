package logika;

import UI.PanelVychodu;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import utils.Observer;
import utils.Subject;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan implements Subject{
    
    private Prostor aktualniProstor;
    
    private Batoh batoh;
    private boolean vyhra = false;
    private boolean prohra = false;
    private Hra hra;
    
    private List<Observer> seznamPozorProstoru;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        seznamPozorProstoru = new ArrayList<>();
        this.hra = hra;
        batoh = new Batoh();

    }
    
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor luka = new Prostor("luka","Lúka, tu si sa ocitol a hľadáš cestu domov.", 260,450);
        Prostor jazero = new Prostor("jazero", "strieborné jazero s horkým pokladom.", 380,350);
        Prostor dedina = new Prostor("dedina","konečne sa môžeš najesť.",130,350);
        Prostor les = new Prostor("les","les s jahodami, malinami a pramenem vody.", 190,200);
        Prostor cistinka = new Prostor("cistinka","čistinka, na ktorej sa môžeš rozhodnúť kam ďalej.",350,200);
        Prostor nadvorie = new Prostor("nadvorie","nádvorie, už len kúsok domov.",260,120);
        Prostor hrad = new Prostor("hrad","hrad, konečne si v svojej svätini.",260,30);
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        luka.setVychod(jazero);
        luka.setVychod(dedina);
        jazero.setVychod(luka);
        jazero.setVychod(cistinka);
        dedina.setVychod(luka);
        dedina.setVychod(les);
        dedina.setVychod(jazero);
        cistinka.setVychod(les);
        cistinka.setVychod(jazero);
        cistinka.setVychod(dedina);
        les.setVychod(dedina);
        les.setVychod(cistinka);
        les.setVychod(nadvorie);
        nadvorie.setVychod(les);
        nadvorie.setVychod(hrad);
        hrad.setVychod(nadvorie);
                
        aktualniProstor = hrad;  // hra začíná v domečku
        
        //vytváram veci
        Vec mince = new Vec("mince",true,"mince.jpg");
        Vec krv = new Vec("krv",true,"krv.jpg");
        Vec zlato = new Vec("zlato",true,"zlato.png");
        Vec dyka = new Vec ("dyka",true,"dyka.jpg");
        Vec klic = new Vec("klic",true,"kluc.jpg");
        Vec kamen = new Vec("kamen",false,"kamen.jpg");
        
        // vložíme věci do prostorů
        jazero.vlozVec(mince);
        dedina.vlozVec(krv);
        dedina.vlozVec(zlato);
        dedina.vlozVec(dyka);
        les.vlozVec(klic);
        jazero.vlozVec(kamen);
        
        aktualniProstor = luka;  // hra začína na lúke  
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       notifyAllObservers();
    }

    public Hra getHra() {
        return hra;
    }
   
    
    /**
     * Metoda vrací odkaz na batoh.
     */
    public Batoh getBatoh() {
        return batoh;
     }
     
     /**
     *  Metoda vrací odkaz na výhru.
     *
     *@return     vyhra
     */
    public boolean isVyhra() {
        return vyhra;
    }

    public void setVyhra(boolean stav) {
        this.vyhra = stav;
    }
    
    /**
     *  Metoda vrací odkaz na prohru.
     *
     *@return     prohra
     */
    public boolean isProhra() {
        return prohra;
    }

    public void setProhra(boolean stav) {
        this.prohra = stav;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
        
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
        
    }

}
