package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import com.sun.java_cup.internal.runtime.Symbol;

import memoryBo.CardBo;
import memoryBo.SymboleBo;

public class CardDAO extends DAO <CardBo> 
{
	
	private static final String TABLE = "Card";
	private static final String CLE_PRIMAIRE = "id_Card";

	private static CardDAO instance = null; //création d'un singleton

	public static CardDAO getInstance() {         
		if(instance == null);         
		return instance = new CardDAO();     }

	public boolean create(CardBo card) {
		boolean succes = true;
		
		try 
		{
			String requete = ("INSERT INTO "+TABLE+" (symbole) VALUES (?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, card.getSymbole().ordinal()); 
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
			ResultSet res = Connection.executeQuery("SELECT * FROM "+TABLE+" where " +CLE_PRIMAIRE+"="+ id) ;
			if(res.next()) {
				card = new CardBo();
				card.setId_Card(res.getInt(1));
				card.setSymbole(SymboleBo.get(res.getInt(2)));
				//card.set(res.getInt(1)); 
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
		if (card == null)
			return false;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set symbole = ?  where "+CLE_PRIMAIRE+" = ?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, card.getSymbole().ordinal());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.setInt(2, card.getId_Card());
			pst.executeUpdate();
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
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
