package it.polito.tdp.porto.controller;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Model;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private SimpleGraph<Creator,DefaultEdge> grafo;	
	private Model model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> box1;

    @FXML
    private ComboBox<String> box2;

    @FXML
    private Button btnCoautori;

    @FXML
    private Button btnCluster;

    @FXML
    private Button btnArticoli;
    
    @FXML
    private Button btnReset;

    @FXML
    private TextArea txtResult;


    @FXML
    void doArticoli(ActionEvent event) {
    	
    	String[] creator1 = box1.getValue().split(" ");
    	String[] creator2 = box2.getValue().split(" ");
    	
    	Creator c1 = new Creator(Integer.parseInt(creator1[0]),creator1[1], creator1[2]);
    	Creator c2 = new Creator(Integer.parseInt(creator2[0]),creator2[1], creator2[2]);
    	
    	List<Article> articoli = model.trovaArticoliComuni(c1, c2);
    	if(articoli.isEmpty())
    		txtResult.setText("I due autori non hanno articoli in comune.");
    	else{
    		String result = "";
    		for(Article a : articoli){
        		result = result + a.getTitle() + "\n";
        	}
        	txtResult.setText("Gli autori " + c1.getFamilyName() + " " + c1.getGivenName() + " e " + 
        	                c2.getFamilyName() + " " + c2.getGivenName() + " hanno in comune gli articoli:\n" + result);
    	}
    	

    }

    @FXML
    void doCluster(ActionEvent event) {
    	List<Creator> autori = model.getAllCreators();
    	grafo = model.generaGrafo(autori);
    	
    	Set<DefaultEdge> edges = grafo.edgeSet();
    	String result = "";
    	for(DefaultEdge e : edges){
    		result = result + e + "\n";
    	}
    	
    	txtResult.setText("Grafo con " + grafo.vertexSet().size() + " autori e " + 
    	              grafo.edgeSet().size() + " connessioni." + "\n" + result);
    }

    @FXML
    void doCoautori(ActionEvent event) {
    	if(box1.getValue() != null){
    		String[] creator = box1.getValue().split(" ");
    		findResult(creator);
    	}
    	else if(box2.getValue()!= null){
    		String[] creator = box2.getValue().split(" ");
    		findResult(creator);
    	}
    	else
    		txtResult.setText("Seleziona un autore da uno dei due menu a tendina.");
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	box1.getItems().clear();
    	box2.getItems().clear();

    }
    
    void findResult(String[] creator){
    	Creator c = new Creator(Integer.parseInt(creator[0]),creator[1], creator[2]);
    	List<Creator> coautori = model.getCoautori(c);
    	String result = "";
    	for(Creator c1 : coautori){
    		result = result + c1.toString() + "\n";
    	}
    	txtResult.setText("I coautori di " + c.getFamilyName() + " " + c.getGivenName() + " sono: \n" + result);
    	
    }

    @FXML
    void initialize() {
        assert box1 != null : "fx:id=\"box1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert box2 != null : "fx:id=\"box2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCluster != null : "fx:id=\"btnCluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnArticoli != null : "fx:id=\"btnArticoli\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Porto.fxml'.";
        
    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		
		for(Creator c : model.getAllCreators()){
			box1.getItems().addAll(c.toString());
			box2.getItems().addAll(c.toString());
		}
		
	}
}
