/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.ihc;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AnaIsabel
 */
public class AjudaController implements Initializable {

    @FXML
    private AnchorPane background;
    @FXML
    private Button sair;
    @FXML
    private Button irDefinicoes;
    @FXML
    private Button retorna;
    @FXML
    private Button camaras;
    
    private String robot;
    
    private CommonButtons comBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comBtn = new CommonButtons();
        robot = "";
    }    

    @FXML
    /**
     * Este método fecha a interface
     */
    private void sairInterface(MouseEvent event) {
        comBtn.closeApp(sair);
    }

    @FXML
    /**
     * Este método muda para o ecrã das definições
     */
    private void IrParaDefinicoes(MouseEvent event) throws IOException {
        comBtn.goToSettings(irDefinicoes, background, robot);
        
    }

    @FXML
     /**
     * Este método muda para o ecrã home
     */
    private void voltarAtras(MouseEvent event) throws IOException   
    {    //carrega tudo o que existe no ficheiro xml para puder ser utilizador na transição
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = (Parent) fxmlLoader.load(); 
        //muda de ecrã, é á cena e muda de ecrã, retorna oara o home
        retorna.getScene().setRoot(root);
        background.getChildren().clear();
        HomeController home = fxmlLoader.<HomeController>getController();
        if (robot.equals("Waterbot")) {
            
            home.setRobot("Waterbot");
        }
        else if (robot.equals("Airbot")) {
            home.setRobot("Airbot");
        }
        else {
            home.setRobot("EarthBot");
        }
        
    }
    
    @FXML
    /**
     * Permite ao utilizador ir para o ecrã de ajuda das câmaras.
     */
    private void IrParaAjudaCamaras(MouseEvent event) throws IOException 
    {
        //carrega tudo o que existe no ficheiro xml para puder ser utilizador na transição
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("AjudaCamaras.fxml"));
        Parent root = (Parent) fxmlLoader.load(); 
        //mudança de ecrã, vai á cena e muda de ecra, vai para o ecra onde tem as camaras
        camaras.getScene().setRoot(root);
        background.getChildren().clear();
         
    }
    
    public void setRobot (String name) {
        robot = name;
    }
}
