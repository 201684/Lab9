package it.polito.tdp.porto.model;

import it.polito.tdp.porto.db.PortoDAO;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Model {

	public List<Creator> getAllCreators(){
		PortoDAO dao = new PortoDAO();
		return dao.getAllCreators();
	}
	
	public List<Creator> getCoautori(Creator c){
		PortoDAO dao = new PortoDAO();
		return dao.getCoautori(c);
	}
	
	public SimpleGraph<Creator,DefaultEdge> generaGrafo(List<Creator> autori){
		SimpleGraph<Creator,DefaultEdge> grafo = new SimpleGraph<Creator,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(grafo, autori);
		for(Creator a : grafo.vertexSet()){
			for(Creator c : getCoautori(a)){
				grafo.addEdge(a,c);
			}
		}
		
		return grafo;
		
	}
	
	public List<Article> getArticoli(Creator c1){
		PortoDAO dao = new PortoDAO();
		return dao.getArticoli(c1);
	}
	
	public List<Article> trovaArticoliComuni(Creator c1, Creator c2){
		List<Article> articoliComuni = new ArrayList<Article>();
		
		List<Article> lista1 = getArticoli(c1);
		List<Article> lista2 = getArticoli(c2);
		for(Article a1 : lista1){
			for(Article a2 : lista2){
				if(a1.equals(a2))
					articoliComuni.add(a1);
					
			}
		}
		return articoliComuni;
	}
}
