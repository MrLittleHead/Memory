package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.CardBo;

public class CardDAO extends DAO <CardBo> {
	
	private static final String TABLE ="Carte";
	private static final String CLE_PRIMAIRE ="id_carte";

	private static CardDAO instance=null; //création d'un singleton

	public static CardDAO getInstance() {         
		if(instance==null);         
		return instance = new CardDAO();     }

	@Override
	public boolean create(CardBo card) {
		boolean succes = true;
		
		try {

			String requete = ("INSERT INTO "+TABLE+" (symboleCarte) VALUES (?, ?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			// on pose un String en paramètre 1 -1er '?'- et ce String est le nom de l'avion
			//pst.setInt(1, card.getId_Card()); //autoincrémentation
			pst.setString(2, card.getSymbole()); //
			// on exécute la mise à jour
			pst.executeUpdate();

			//Récupérer la clé qui a été générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				card.setId_Card(rs.getInt(1));//auto-incrementation ???????
			}

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}
	}

	@Override
	public CardBo read(int id) {
		CardBo car = null;
		try {
			ResultSet res = Connection.executeQuery("SELECT * FROM Carte where id_carte ="+ id) ;
			if(res.next()){
				car = new CardBo(res.getInt(1));
				car.setId_Card(res.getInt(1)); // creation du set avec implementation auto ?
			}
		} catch (SQLException e) {
			System.out.println("Echec"+e.getMessage());
			e.printStackTrace();
		}
		return car;
		
	}

	@Override
	public boolean update(CardBo car) {
		boolean succes = true;
		try {
			String requeteUpdate = ("update "+ TABLE +" set motif = ?  where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, car.getMotif());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.setInt(2, car.getId_Card());
			pst.executeUpdate();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

}
