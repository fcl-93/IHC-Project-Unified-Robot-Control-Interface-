/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.ihc;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author fabio
 */
public class HomeController implements Initializable {
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY, curJoyAngle, curJoySize;
    private Label label;
    @FXML
    private AnchorPane background;
    @FXML
    private Pane map;
    @FXML
    private Button helpButton;
    @FXML
    private Button sensorButton;
    @FXML
    private Button garrasButton;
    @FXML
    private Button powerButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button backButton;
    
    MyBrowser myBrowser;
    @FXML
    private ImageView cam2;
    @FXML
    private ImageView cam3;
    @FXML
    private ImageView cam4;
    @FXML
    private ImageView cam1;;
    @FXML
    private AnchorPane paneGarraBaixo;
    @FXML
    private AnchorPane paneGarraControl;
    @FXML
    private Circle controlGarra;
    @FXML
    private Circle circleBaixoGarra;
    @FXML
    private AnchorPane paneMovBaixo;
    @FXML
    private AnchorPane paneMovControl;
    @FXML
    private Circle controlMov;
    @FXML
    private Circle circleBaixoMov;
    
    private Image camFrente;
    private Image camTras;
    private Image camEsquerda;
    private Image camDireita;
    
    
    private int joyOutputRange = 100;
    private double joySizeGarras, joySizeMov, posicaoX, posicaoY;
    @FXML
    private Text tempoMissao;
    
    private ApresentaTempo apresentaTempo;
    @FXML
    private RadioButton robotCommunication;
    @FXML
    private RadioButton teamCommunication;
    @FXML
    private RadioButton turnOffCommunication;
    @FXML
    private RadioButton powerOnRobo;
    @FXML
    private RadioButton powerOffRobo;
    @FXML
    private RadioButton lightOn;
    @FXML
    private RadioButton lightOff;
@FXML
    private TabPane tabPane;
    @FXML
    private Button mapButton;
    @FXML
    private Text idEcra;
    @FXML
    private Tab mapTab;
    @FXML
    private Tab funcTab;
    @FXML
    private Text recInfo;
    @FXML
    private Button recButton;    
    private boolean gravar;
    private String robot;
    @FXML
    private Text profAlt;
    private Button plusProf;
    private Button minusProf;
    @FXML
    private TextFlow notificacoesPan;
    @FXML
    private Slider velControl;
    @FXML
    private Text indicaVelocidade;
    @FXML
    private Slider profAltSlide;
    
    private CommonButtons comBtn;
    
    private boolean pilotoAutomatico;
    @FXML
    private Label cam2Label;
    @FXML
    private Label cam3Label;
    @FXML
    private Label cam4Label;
    @FXML
    private Label cam1Label;
    @FXML
    private Pane camPane2;
    @FXML
    private Pane camPane3;
    @FXML
    private Pane camPane4;
    @FXML
    private Pane camPane1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        camFrente = cam1.getImage();
        camTras = cam4.getImage();
        camEsquerda = cam3.getImage();
        camDireita = cam2.getImage();
        pilotoAutomatico = false;
        comBtn = new CommonButtons();
        robot = "";
        gravar = false;
        tabPane.getSelectionModel().select(2);
        apresentaTempo = new ApresentaTempo(tempoMissao);
        myBrowser = new MyBrowser();
        map.getChildren().add(myBrowser);
        cam1.fitWidthProperty().bind(camPane1.widthProperty());
        cam2.fitWidthProperty().bind(camPane2.widthProperty());
        cam3.fitWidthProperty().bind(camPane3.widthProperty());
        cam4.fitWidthProperty().bind(camPane4.widthProperty());
        cam1.fitHeightProperty().bind(camPane1.heightProperty());
        cam2.fitHeightProperty().bind(camPane2.heightProperty());
        cam3.fitHeightProperty().bind(camPane3.heightProperty());
        cam4.fitHeightProperty().bind(camPane4.heightProperty());
        ToggleGroup groupComunicacao = new ToggleGroup();
        ToggleGroup groupOnOff = new ToggleGroup();
        ToggleGroup groupLuzes = new ToggleGroup();
        robotCommunication.setToggleGroup(groupComunicacao);
        teamCommunication.setToggleGroup(groupComunicacao);
        turnOffCommunication.setToggleGroup(groupComunicacao);
        powerOnRobo.setToggleGroup(groupOnOff);
        powerOffRobo.setToggleGroup(groupOnOff);
        lightOn.setToggleGroup(groupLuzes);
        lightOff.setToggleGroup(groupLuzes);
        turnOffCommunication.setSelected(true);
        lightOff.setSelected(true);
        powerOnRobo.setSelected(true);
        recInfo.setOpacity(0);
        mapButton.setOpacity(0);
        mapButton.setDisable(true);
        circleBaixoGarra.setFill(new ImagePattern(new Image("/img/joystick.png")));
        circleBaixoMov.setFill(new ImagePattern(new Image("/img/joystick.png")));
        joySizeGarras = circleBaixoGarra.getRadius()/2;
        joySizeMov = circleBaixoMov.getRadius()/2;
        notificacoesPan.setTextAlignment(TextAlignment.CENTER);
        indicaVelocidade.textProperty().bind(Bindings.format("%.2f Km/h", velControl.valueProperty()));
    }
    
    @FXML
    /**
     * This method switches the atual screen to the help screen
     */
    private void sensorButtonClicked(MouseEvent event) throws IOException {
        apresentaTempo.setRunning(false);
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("Sensores.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        helpButton.getScene().setRoot(root);
        background.getChildren().clear();
        SensoresController sens = fxmlLoader.<SensoresController>getController();
        sens.setRobot(robot);
        
    }
    
    @FXML
    /**
     * This method switches the atual screen to the help screen
     */
    private void garrasButtonClicked(MouseEvent event) throws IOException {
        apresentaTempo.setRunning(false);
        FXMLLoader  fxmlLoader = new FXMLLoader(getClass().getResource("Garras.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        helpButton.getScene().setRoot(root);
        background.getChildren().clear();
        GarrasController garras = fxmlLoader.<GarrasController>getController();
        garras.setRobot(robot);
    }
    
    @FXML
    /**
     * This method switches the atual screen to the help screen
     */
    private void helpButtonClicked(MouseEvent event) throws IOException {
        apresentaTempo.setRunning(false);
        comBtn.askHelp(helpButton, background, robot);
    }

    @FXML
    /**
     * This method closes the application
     */
    private void powerButtonClicked(MouseEvent event) {
        apresentaTempo.setRunning(false);
        comBtn.closeApp(powerButton);
    }

    @FXML
    /**
     * This method switches the atual screen to the settings screen
     */
    private void settingsButtonClicked(MouseEvent event) throws IOException {
        apresentaTempo.setRunning(false);
        comBtn.goToSettings(settingsButton, background, robot);
    }

    @FXML
    /**
     * This method switches the atual screen to the previous one
     */
    private void backButtonClicked(MouseEvent event) throws IOException {
        apresentaTempo.setRunning(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(backButton.getScene().getWindow());
        alert.setHeaderText("Está prestes a voltar ao ecrã de Seleção de Robôs.\nO estado atual não será guardado.");
        alert.setContentText("Deseja Continuar?");
        
        Button okButton = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
        okButton.setText("Sim");
        Button noButton = (Button) alert.getDialogPane().lookupButton( ButtonType.CANCEL );
        noButton.setText("Não");  

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            alert.close();
            Parent root = FXMLLoader.load(getClass().getResource("Selecao.fxml"));
            settingsButton.getScene().setRoot(root);
            background.getChildren().clear();
        } else {
            // ... user chose CANCEL or closed the dialog
            alert.close();
        }
    }

    @FXML
    /**
     * This method willl allow the virtual joystick to work 
     */
    private void controlaGarra(MouseEvent event) {
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
        
        curJoyAngle = (float) Math.atan2(newTranslateY, newTranslateX);
        curJoySize = (float) Point.distance(event.getSceneX(), event.getSceneY(),
                orgSceneX, orgSceneY);
        
        if (curJoySize < joySizeGarras) {
            ((Circle)(event.getSource())).setTranslateX(newTranslateX);
            ((Circle)(event.getSource())).setTranslateY(newTranslateY);   
        }
        
        posicaoX = (int) (joyOutputRange * (Math.cos(curJoyAngle)
                * curJoySize) / joySizeGarras);
        posicaoY = (int) (joyOutputRange * (-(Math.sin(curJoyAngle)
                * curJoySize) / joySizeGarras));
        
        System.out.println("X:" + posicaoX + " Y: " + posicaoY);
        
    }
    
    @FXML
    /**
     * This method makes the smallest ball to move to the place where i'm moving the mouse
     */
    private void tocaControlGarra(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Circle)(event.getSource())).getTranslateX();
        orgTranslateY = ((Circle)(event.getSource())).getTranslateY();
    }

    @FXML
    /**
     * This method makes the ball that fallow the mouse to return to the center when the mouse isn't being pressed.
     */
    private void libertaControlGarra(MouseEvent event) {
        ((Circle)(event.getSource())).setTranslateX(orgTranslateX);
        ((Circle)(event.getSource())).setTranslateY(orgTranslateY);
    }
    
     @FXML
     /**
     * This method willl allow the virtual joystick to work 
     */
    private void controlaMovimento(MouseEvent event) {
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
        
        curJoyAngle = (float) Math.atan2(newTranslateY, newTranslateX);
        curJoySize = (float) Point.distance(event.getSceneX(), event.getSceneY(),
                orgSceneX, orgSceneY);
        
        if (curJoySize < joySizeMov) {
            ((Circle)(event.getSource())).setTranslateX(newTranslateX);
            ((Circle)(event.getSource())).setTranslateY(newTranslateY);   
        }
        
        posicaoX = (int) (joyOutputRange * (Math.cos(curJoyAngle)
                * curJoySize) / joySizeMov);
        posicaoY = (int) (joyOutputRange * (-(Math.sin(curJoyAngle)
                * curJoySize) / joySizeMov));
        
        System.out.println("X:" + posicaoX + " Y: " + posicaoY);
        
    }
    
    @FXML
    /**
     * This method makes the smallest ball to move to the place where i'm moving the mouse
     */
    private void tocaControlMovimento(MouseEvent event) {
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Circle)(event.getSource())).getTranslateX();
        orgTranslateY = ((Circle)(event.getSource())).getTranslateY();
    }

    @FXML
    /**
     * This method makes the ball that fallow the mouse to return to the center when the mouse isn't being pressed.
     */
    private void libertaControlMovimento(MouseEvent event) {
        ((Circle)(event.getSource())).setTranslateX(orgTranslateX);
        ((Circle)(event.getSource())).setTranslateY(orgTranslateY);
    }

    @FXML
    /**
     * This method will change the main camera image1 with the image that comes from the camera 2
     */
    private void alternaCamara2(MouseEvent event) {
        Image cam1Atual = cam1.getImage();
        Image cam2Atual = cam2.getImage();
        mudaIamgens (cam1, cam2, cam1Label, cam2Label, cam1Atual, cam2Atual);
    }
    
    /**
     * Makes it possible to change the cameras image and labels the cameras when they're changed.
     * @param camA
     * @param camB
     * @param labelA
     * @param labelB
     * @param camAAtual
     * @param camBAtual 
     */
    private void mudaIamgens (ImageView camA, ImageView camB, Label labelA, Label labelB, Image camAAtual, Image camBAtual) {
        camA.setImage(camBAtual);
        camB.setImage(camAAtual);
        if (camBAtual.equals(camEsquerda)) {
            if (camAAtual.equals(camFrente)) {
                labelB.setText("Frente");
                labelA.setText("Esquerda");
            }
            else if (camAAtual.equals(camTras)) {
                labelB.setText("Trás");
                labelA.setText("Esquerda");
            }
            else if (camAAtual.equals(camDireita)) {
                labelB.setText("Direita");
                labelA.setText("Esquerda");
            }
        }
        else if (camBAtual.equals(camFrente)) {
            if (camAAtual.equals(camEsquerda)) {
                labelB.setText("Esquerda");
                labelA.setText("Frente");
            }
            else if (camAAtual.equals(camTras)) {
                labelB.setText("Trás");
                labelA.setText("Frente");
            }
            else if (camAAtual.equals(camDireita)) {
                labelB.setText("Direita");
                labelA.setText("Frente");
            }
        }
        else if (camBAtual.equals(camDireita)) {
            if (camAAtual.equals(camFrente)) {
                labelB.setText("Frente");
                labelA.setText("Direita");
            }
            else if (camAAtual.equals(camTras)) {
                labelB.setText("Trás");
                labelA.setText("Direita");
            }
            else if (camAAtual.equals(camEsquerda)) {
                labelB.setText("Esquerda");
                labelA.setText("Direita");
            }
        }
        else if (camBAtual.equals(camTras)) {
            if (camAAtual.equals(camFrente)) {
                labelB.setText("Frente");
                labelA.setText("Trás");
            }
            else if (camAAtual.equals(camEsquerda)) {
                labelB.setText("Esquerda");
                labelA.setText("Trás");
            }
            else if (camAAtual.equals(camDireita)) {
                labelB.setText("Direita");
                labelA.setText("Trás");
            }
        }
    }

    @FXML
    /**
     * This will change the image from the main camera1 with the camera3
     */
    private void alternaCamara3(MouseEvent event) {
        Image cam1Atual = cam1.getImage();
        Image cam3Atual = cam3.getImage();
        mudaIamgens (cam1, cam3, cam1Label, cam3Label, cam1Atual, cam3Atual);
    }

    @FXML
    /**
     * This method will change the camera 1 image with the camera number 4 image
     */
    private void alternaCamara4(MouseEvent event) {
        Image cam1Atual = cam1.getImage();
        Image cam4Atual = cam4.getImage();
        mudaIamgens (cam1, cam4, cam1Label, cam4Label, cam1Atual, cam4Atual);
    }

    @FXML
    /**
     * This method will allow a user to activate the go back to base functionality
     */
    private void goBackToBase(MouseEvent event) {
        tabPane.getSelectionModel().select(1);
        mapButton.setOpacity(100);
        mapButton.setDisable(false);
        idEcra.setText("Retorno à Base");
        mapButton.setText("Retornar à Base");
        pilotoAutomatico = false;
    }

    @FXML
    /**
     * This method will allow the user to activate the autopilot functionality
     */
    private void goPilotoAutomatico(MouseEvent event) {
        tabPane.getSelectionModel().select(1);
        mapButton.setOpacity(100);
        mapButton.setDisable(false);
        idEcra.setText("Piloto Automático");
        mapButton.setText("Ativar Piloto Automático");
        pilotoAutomatico = true;
    }

    @FXML
    /**
     * This method changes the title of the screnn when you click in the cameras tab
     */
    private void camTabClicked(Event event) {
        idEcra.setText("Home");
    }

    @FXML
     /**
     * This method changes the title of the screnn when you click in the map tab
     */
    private void mapTabClicked(Event event) {
        idEcra.setText("Mapa");
        mapButton.setOpacity(0);
        mapButton.setDisable(true);
    }

    @FXML
     /**
     * This method changes the title of the screnn when you click in the functions tab
     */
    private void funcTabClicked(Event event) {
        idEcra.setText("Funções");
    }

    @FXML
     /**
     * This method turn on or off the recording functionality
     */
    private void recButtonClicked(MouseEvent event) {
        if (!gravar) {
            recInfo.setOpacity(100);
            gravar = true;
        } else {
            recInfo.setOpacity(0);
            gravar = false;
        }
        
    }
    
    /**
     * This method ajusts the interface to the selected robot
     * @param name 
     */
    public void setRobot (String name) {
        robot = name;
        if (robot.equals("Waterbot")) {
            cam1.setImage(new Image("/img/cam 1 water.jpg"));
            cam2.setImage(new Image("/img/cam 2 water.jpg"));
            cam3.setImage(new Image("/img/cam 3 water.jpg"));
            cam4.setImage(new Image("/img/cam 4 water.jpg"));
            profAltSlide.setOpacity(100);
            profAlt.setOpacity(100);
            profAlt.setText("Profundidade");
            
        }
        else if (robot.equals("Airbot")){
            profAltSlide.setOpacity(100);
            profAlt.setText("Altitude");
        }
        else {
            profAltSlide.setOpacity(0);
            profAlt.setOpacity(0);
        }
    }
    
    /**
     * This creates a fade out effect in the notifications
     * @param node
     * @return 
     */
    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(5), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }
    
    /**
     * This method writes the given notification on the screen
     */
    public void escreveNotificacao(Text notificacao) {
        notificacao.setFont(Font.font("Balsamiq Sans", 18));
        FadeTransition fader = createFader(notificacao);
        notificacoesPan.getChildren().add(notificacao);
        fader.play();
        fader.setOnFinished(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			notificacoesPan.getChildren().remove(notificacao);
		}
	});
    }

    @FXML
    /**
     * This method turn on the robot
     */
    private void ligaRobo(MouseEvent event) {
        cam1.setOpacity(100);
        cam2.setOpacity(100);
        cam3.setOpacity(100);
        cam4.setOpacity(100);
        Text notificacao = new Text ("O Robô foi ligado.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method turn off the robot
     */
    private void desligaRobo(MouseEvent event) {
        cam1.setOpacity(0);
        cam2.setOpacity(0);
        cam3.setOpacity(0);
        cam4.setOpacity(0);
        Text notificacao = new Text ("O Robô foi desligado.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method turn on the lights
     */
    private void ligaLuzes(MouseEvent event) {
        Text notificacao = new Text ("As luzes do robô foram ligadas.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method turn off the lights
     */
    private void desligaLuzes(MouseEvent event) {
        Text notificacao = new Text ("As luzes do robô foram desligadas.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method starts the team communication
     */
    private void comunicaEquipa(MouseEvent event) {
        Text notificacao = new Text ("A comunicação com a equipa foi ativada.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method turn off the communication
     */
    private void desligaComunicacao(MouseEvent event) {
        Text notificacao = new Text ("A comunicação foi desativada.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method turn on with the robot
     */
    private void comunicaRobo(MouseEvent event) {
        Text notificacao = new Text ("A comunicação via Robô foi ativada.\n");
        escreveNotificacao(notificacao);
    }

    @FXML
    /**
     * This method activates or cancelas the autopilot and the goback to base functionalities
     */
    private void mapButtonClicked(MouseEvent event) {
        if (mapButton.getText().equals("Retornar à Base") || mapButton.getText().equals("Ativar Piloto Automático")) {
            mapButton.setText("Cancelar");
        }
        else if (pilotoAutomatico){
            mapButton.setText("Ativar Piloto Automático");
        }
        else {
            mapButton.setText("Retornar à Base");
        }
    }
    
}

/**
 * Google maps integration in the maps tab
 * @author vmcba
 */
class MyBrowser extends Region{

    HBox toolbar;

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public MyBrowser(){

        final URL urlGoogleMaps = getClass().getResource("/resources/maps.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

        getChildren().add(webView);

    }    
    
}
