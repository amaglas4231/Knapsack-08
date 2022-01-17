package UI;

import java.net.URL;

import java.util.ResourceBundle;
import GeneticA.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

public class OurMenu implements Initializable {


    @FXML
    private Button parcelDsBtn;

    @FXML
    private Button parcelEcBtn;

    @FXML
    private Button parcelSilBtn;

    @FXML
    private Button pentDsBtn;

    @FXML
    private Button pentEcBtn;

    @FXML
    private Button pentSimBtn;

    @FXML
    void display(ActionEvent event) {
           
       DSParcelsExactCoverVisual visual = new DSParcelsExactCoverVisual();
       visual.begin(App.getStage());

    }
    

    @FXML
    void display2(ActionEvent event) {
        DSParcelsMaxVisual visual = new DSParcelsMaxVisual();
        visual.begin(App.getStage());
    }

    @FXML
    void display3(ActionEvent event) {
        SAParcelMaxVisual visual = new SAParcelMaxVisual();
        visual.begin(App.getStage());
    }

    @FXML
    void display4(ActionEvent event) {
        DSPentosExactCoverVisual visual = new DSPentosExactCoverVisual();
        visual.begin(App.getStage());
    }

    @FXML
    void display5(ActionEvent event) {
        DSPentosMaxVisual visual = new DSPentosMaxVisual();
        visual.begin(App.getStage());
    }

    @FXML
    void display6(ActionEvent event) {
        SAPentosMaxVisual visual = new SAPentosMaxVisual();
        visual.begin(App.getStage());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
