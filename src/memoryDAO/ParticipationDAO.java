package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.CardBo;
import memoryBo.GameBo;
import memoryBo.ParticipationBo;
import memoryBo.PlayerBo;
import memoryBo.ScorePlayerBo;

public class ParticipationDAO extends DAO<ParticipationBo> {
	
	private static final String TABLE = "Participe";
	private static final String CLE_PRIMAIRE = "id_Game, id_Player";

	private static ParticipationDAO instance = null; //création d'un singleton

	public static ParticipationDAO getInstance() {         
		if(instance == null);         
		return instance = new ParticipationDAO();     }

	@Override
	public boolean create(ParticipationBo part) {
		boolean succes = true;
		
		try {

			String requete = ("INSERT INTO "+TABLE+" (symboleCarte) VALUES (?, ?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, GameBo.getId_Game()); 
			pst.setInt(2, PlayerBo.getId_Player());
			pst.setInt(3, ScorePlayerBo.getScore());
			pst.setBoolean(4, part.hand);
			pst.setInt(5, part.getPositionTour());
			pst.executeUpdate();

			//Récupérer la clé qui a été générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				GameBo.setId_Game(rs.getInt(1));
				PlayerBo.setId_Player(rs.getInt(2));
				
			}

		} 
		catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}
	

	@Override
	public ParticipationBo read(int idGame) {
		ParticipationBo part = null;
		try {
			ResultSet res = Connection.executeQuery("SELECT * FROM "+TABLE+" where id_Game =?") ;
			if(res.next()) {
				part = new ParticipationBo(res.getInt(1));
				part.setId_Game(res.getInt(1));
				part.setId_Player(res.getInt(1));
			}
		} 
		catch (SQLException e) {
			System.out.println("Echec"+e.getMessage());
			e.printStackTrace();
		}
		return part;
		
	}

	@Override
	public boolean update(ParticipationBo part) {
		boolean succes = true;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set symbole = ?  where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, GameBo.getId_Game());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.setInt(2, PlayerBo.getId_Player());
			pst.setInt(3, ScorePlayerBo.getScore());
			pst.setBoolean(4, part.hand);
			pst.setInt(5, part.getPositionTour());
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(ParticipationBo part) {
		boolean succes = true;
		try {
			String requeteDelete = (" delete from "+ TABLE +" where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteDelete, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, part.getId_Game());
			pst.setInt(2, getId_Player());
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}


}
