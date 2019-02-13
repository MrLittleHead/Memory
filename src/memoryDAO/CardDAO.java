package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.CardBo;

public class CardDAO extends DAO <CardBo> {
	
	private static final String TABLE ="Carte";
	private static final String CLE_PRIMAIRE ="id_carte";

	private static CardDAO instance=null; //cr�ation d'un singleton

	public static CardDAO getInstance() {         
		if(instance==null);         
		return instance = new CardDAO();     }

	@Override
	public boolean create(CardBo carte) {
		boolean succes = true;
		try {
			String requete = ("insert into "+ TABLE +"(motif) values (?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(2, carte.getMotif());
			pst.executeUpdate();
			//R�cup�rer la cl� qui a �t� g�n�r�e et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				//car.setId_carte(rs.getInt(1));
			}


		} catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public CardBo read(int id) {
		CardBo car = null;
		try {
			ResultSet res = Connection.executeQuery("SELECT * FROM Carte where id_carte ="+ id) ;
			if(res.next()){
				car = new CardBo(res.getInt(2));
				car.setId_carte(res.getInt(1));
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
			pst.setInt(1, car.getMotif());
			pst.setInt(2, car.getId_carte());
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
			pst.setInt(1, car.getId_carte());			
			pst.executeUpdate();
		} catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

}
