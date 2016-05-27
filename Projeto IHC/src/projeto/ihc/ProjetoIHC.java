/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.ihc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
/**
 * 
 * @author fabio
 */
public class ProjetoIHC extends Application {
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("Selecao.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/resources/abc.css");
        
        stage.setScene(scene);
        stage.show();
        //stage.setFullScreen(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
