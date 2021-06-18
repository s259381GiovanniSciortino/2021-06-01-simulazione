package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interazione;


public class GenesDao {
	
	public List<Interazione> getArchi(){
		String sql = "select i.GeneID1 as id1, i.GeneID2 as id2,abs(i.Expression_Corr) as peso "
				+ "FROM interactions i, genes g1, genes g2 "
				+ "where g1.GeneID = i.GeneID1 "
				+ "and g2.GeneID = i.GeneID2 "
				+ "and g2.Essential = 'Essential' "
				+ "and g1.Essential = 'Essential' "
				+ "group by i.GeneID1 , i.GeneID2";
		List<Interazione> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Interazione interazione = new Interazione(res.getString("id1"),res.getString("id2"),res.getDouble("peso"));
				result.add(interazione);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genes> getAllEssentialGenes(){
		String sql = "select DISTINCT GeneID, Essential, Chromosome "
				+ "FROM Genes "
				+ "where Essential ='Essential'";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	


	
}
