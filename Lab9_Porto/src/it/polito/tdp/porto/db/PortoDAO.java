package it.polito.tdp.porto.db;

import it.polito.tdp.porto.db.DBConnect;
import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class PortoDAO {
	
	public List<Creator> getAllCreators(){
		List<Creator> autori = new ArrayList<Creator>();
		
		Connection conn = DBConnect.getConnection();
		
		try { 
			String sql = "SELECT id_creator,family_name,given_name FROM creator";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while( res.next() ){
				autori.add(new Creator(res.getInt("id_creator"), res.getString("family_name"), res.getString("given_name")));
			}
			res.close();
			conn.close();
			return autori;
			
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autori;
	}
	
	public List<Creator> getCoautori(Creator c){
		List<Creator> coautori = new ArrayList<Creator>();
		
	Connection conn = DBConnect.getConnection();
		
		try { 
			String sql = "SELECT DISTINCT c.id_creator,c.family_name,c.given_name FROM creator c, authorship a "
					+ "WHERE a.id_creator = c.id_creator AND a.eprintid IN "
					+ "(SELECT eprintid FROM authorship  WHERE id_creator = ?) AND a.id_creator <> ? ";
			PreparedStatement st = conn.prepareStatement(sql);
					
			st.setInt(1, c.getIdCreator());
			st.setInt(2, c.getIdCreator());
			
			ResultSet res = st.executeQuery();
			
			while( res.next() ){
				coautori.add(new Creator(res.getInt("id_creator"), res.getString("family_name"), res.getString("given_name")));
			}
			res.close();
			conn.close();
			return coautori;
		
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coautori;
	}
	
	
	public List<Article> getArticoli(Creator c1){
		List<Article> articoli = new ArrayList<Article>();
		
        Connection conn = DBConnect.getConnection();
		
		try { 
			String sql = "SELECT DISTINCT a.eprintid, a.year,a.title FROM article a, authorship au "
					+ "WHERE a.eprintid = au.eprintid AND au.id_creator = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, c1.getIdCreator());
			
			ResultSet res = st.executeQuery();
			
			while( res.next() ){
				articoli.add(new Article(res.getInt("eprintid"), res.getInt("year"), res.getString("title")));
			}
			res.close();
			conn.close();
			return articoli;
			
		}catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articoli;
		
	}
	
	

}
