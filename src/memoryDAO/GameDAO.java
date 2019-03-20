package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.GameBo;

public class GameDAO extends DAO<GameBo> {
	
	private static final String TABLE = "Game";
	private static final String CLE_PRIMAIRE = "id_Game";

	private static GameDAO instance = null; //création d'un singleton

	public static GameDAO getInstance() {         
		if(instance == null);         
		return instance = new GameDAO();     }

	@Override
	public boolean create(GameBo game) {
		boolean succes = true;
		
		try {

			String requete = ("INSERT INTO "+TABLE+" (symboleCarte) VALUES (?, ?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, game.getId_Game()); 
			pst.setString(2, game.getGameName());
			pst.setDate(3, game.getGameDate());//
			// on exécute la mise à jour
			pst.executeUpdate();

			//Récupérer la clé qui a été générée et la pousser dans l'objet initial
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				game.setId_Game(rs.getInt(1));//auto-incrementation ???????
			}

		} 
		catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}
	

	@Override
	public GameBo read(int id) {
		GameBo game = null;
		try {
			ResultSet res = Connection.executeQuery("SELECT * FROM Carte where id_carte ="+ id) ;
			if(res.next()) {
				game = new GameBo(res.getString(1));
				game.setId_Game(res.getInt(2));
				game.setGameName(res.getString(3));
				game.setGameDate(res.getDate(4));
			}
		} 
		catch (SQLException e) {
			System.out.println("Echec"+e.getMessage());
			e.printStackTrace();
		}
		return game;
		
	}

	@Override
	public boolean update(GameBo game) {
		boolean succes = true;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set symbole = ?  where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, game.getId_Game());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.setString(2, game.getGameName());
			pst.setDate(3, game.getGameDate());
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(GameBo game) {
		boolean succes = true;
		try {
			String requeteDelete = (" delete from "+ TABLE +" where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteDelete, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, game.getId_Game());			
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}


	
}
