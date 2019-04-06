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
		try 
		{
			String requete = ("INSERT INTO "+TABLE+" (gameName, gameDate) VALUES (?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, game.getGameName());
			pst.setDate(2, game.getGameDate());//
			
			pst.executeUpdate();					// on exécute la mise à jour

			ResultSet rs = pst.getGeneratedKeys();	//Récupérer la clé qui a été générée et la pousser dans l'objet initial
			if (rs.next()) 
			{
				game.setId_Game(rs.getInt(1));		
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
			ResultSet res = Connection.executeQuery("SELECT * FROM Game where "+CLE_PRIMAIRE+" ="+ id) ;
			if(res.next()) {
				game = new GameBo();
				game.setId_Game(res.getInt(1));
				game.setGameName(res.getString(2));
				game.setGameDate(res.getDate(3));
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
			String requeteUpdate = ("update "+ TABLE +" set gameName = ?, set gameDate = ?  where "+CLE_PRIMAIRE+" = ?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(3, game.getId_Game());		
			pst.setString(1, game.getGameName());
			pst.setDate(2, game.getGameDate());
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
			String requeteDelete = (" delete from "+ TABLE +" where "+CLE_PRIMAIRE+" = ?");
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
