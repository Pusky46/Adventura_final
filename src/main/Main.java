/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.Mapa;
import UI.MenuPole;
import UI.PanelBatohu;
import UI.PanelVeci;
import UI.PanelVychodu;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 *
 */
public class Main extends Application {
    
    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    private TextArea centerText;
    private Stage primaryStage;
    private PanelVychodu panelVychodu;
    private PanelBatohu panelBatohu;
    private PanelVeci panelVeci;

    //
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
        
        BorderPane borderPane = new BorderPane();
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                
                centerText.appendText("\n" + zadanyPrikaz + "\n");
                centerText.appendText("\n" + odpoved + "\n");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                }
                
            }
        });
        
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        BorderPane pravy = new BorderPane();
        
        panelBatohu = new PanelBatohu(hra.getHerniPlan(),centerText);
        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centerText,zadejPrikazTextField);
        panelVeci = new PanelVeci(hra.getHerniPlan(),centerText);
        
        //pravá čast okna
        pravy.setLeft(panelBatohu.getList());
        pravy.setCenter(panelVychodu.getList());
        pravy.setRight(panelVeci.getList());
        pravy.setTop(mapa);
        
        borderPane.setCenter(pravy);      
        borderPane.setBottom(dolniPanel);
        borderPane.setTop(menu);
        borderPane.setLeft(centerText);
        
       
//        TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
//        textoveRozhrani.hraj();
        Scene scene = new Scene(borderPane, 1400, 900);

        primaryStage.setTitle("Upírí potulky");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
        start(primaryStage);
    }

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

   
    }


