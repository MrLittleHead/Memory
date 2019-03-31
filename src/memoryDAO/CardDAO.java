package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.CardBo;

public class CardDAO extends DAO <CardBo> 
{
	
	private static final String TABLE = "Carte";
	private static final String CLE_PRIMAIRE = "id_carte";

	private static CardDAO instance = null; //création d'un singleton

	public static CardDAO getInstance() {         
		if(instance == null);         
		return instance = new CardDAO();     }

	@Override
	public boolean create(CardBo card) {
		boolean succes = true;
		
		try 
		{
			String requete = ("INSERT INTO "+TABLE+" (symboleCarte) VALUES (?, ?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, card.getId_Card()); 
			pst.setInt(2, card.getSymbole().ordinal()); 
			pst.executeUpdate();						// on exécute la mise à jour

			
			ResultSet rs = pst.getGeneratedKeys();		//Récupérer la clé qui a été générée et la pousser dans l'objet initial
			if (rs.next()) 
			{
				card.setId_Card(rs.getInt(1));
			}

		} 
		catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}
	

	@Override
	public CardBo read(int id) {
		CardBo card = null;
		try {
			ResultSet res = Connection.executeQuery("SELECT * FROM "+TABLE+" where id_carte ="+ id) ;
			if(res.next()) {
				card = new CardBo(res.getInt(1));
				card.setId_Card(res.getInt(1)); // creation du set avec implementation auto ?
			}
		} 
		catch (SQLException e) {
			System.out.println("Echec"+e.getMessage());
			e.printStackTrace();
		}
		return card;
		
	}

	@Override
	public boolean update(CardBo card) {
		boolean succes = true;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set symbole = ?  where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, card.getId_Card());
			pst.setInt(2, card.getSymbole().ordinal());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(CardBo car) {
		boolean succes = true;
		try {
			String requeteDelete = (" delete from "+ TABLE +" where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteDelete, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, car.getId_Card());			
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

}
