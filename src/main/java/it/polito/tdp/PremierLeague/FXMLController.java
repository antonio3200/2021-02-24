
/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.GiocatoreMigliore;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnGiocatoreMigliore"
    private Button btnGiocatoreMigliore; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMatch"
    private ComboBox<Match> cmbMatch; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	Match selezionato= cmbMatch.getValue();
    	if(selezionato==null) {
    		txtResult.setText("Selezionare un match dal box predisposto");
    		return;
    	}
    	this.model.creaGrafo(selezionato);
    	txtResult.appendText("GRAFO CREATO! \n");
    	txtResult.appendText("Numero Vertici : "+this.model.numeroVertici()+"\n");
    	txtResult.appendText("Numero Archi : "+this.model.numeroArchi()+"\n");
    	
    }

    @FXML
    void doGiocatoreMigliore(ActionEvent event) {  
    	txtResult.clear();
    	GiocatoreMigliore g= this.model.getGiocatoreMigliore();
    	if(g==null) {
    		txtResult.setText("ERRORE: NON TROVATO NESSUN GIOCATORE MIGLIORE");
    		return;
    	}
    	txtResult.appendText(g.toString());
    	
    	
    }
    
    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGiocatoreMigliore != null : "fx:id=\"btnGiocatoreMigliore\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMatch != null : "fx:id=\"cmbMatch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	List<Match> partite=this.model.getMatches();
    	Collections.sort(partite);
    	this.cmbMatch.getItems().addAll(partite);
    }
}
