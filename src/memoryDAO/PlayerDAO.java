package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import memoryBo.PlayerBo;

public class PlayerDAO extends DAO <PlayerBo> 
{
	
	private static final String TABLE = "Player";
	private static final String CLE_PRIMAIRE = "id_Player";

	private static PlayerDAO instance = null; 

	public static PlayerDAO getInstance() {         
		if(instance == null);         
		return instance = new PlayerDAO();     }

	@Override
	public boolean create(PlayerBo player) {
		boolean succes = true;
		
		try {

			String requete = ("INSERT INTO "+TABLE+" (id_Player, pseudo) VALUES (?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(2, player.getId_Player()); //
			pst.setString(2, player.getPseudo());
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) 
			{
				player.setId_Player(rs.getInt(1));
			}

		} 
		catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}

		return succes;
	}
	

	@Override
	public PlayerBo read(int id) {
		PlayerBo player = null;
		try 
		{
			ResultSet res = Connection.executeQuery("SELECT * FROM player where "+ CLE_PRIMAIRE +"= "+ id) ;
			if(res.next())
			{
				player = new PlayerBo(res.getString(1));
				player.setId_Player(res.getInt(1)); 
			}
		} 
		catch (SQLException e) {
			System.out.println("Echec"+e.getMessage());
			e.printStackTrace();
		}
		return player;
		
	}

	@Override
	public boolean update(PlayerBo player) {
		boolean succes = true;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set pseudo = ?  where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, player.getId_Player());		
			pst.setString(2, player.getPseudo());
			pst.executeUpdate();
			
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(PlayerBo player) {
		boolean succes = true;
		try 
		{
			String requeteDelete = (" delete from "+ TABLE +" where "+CLE_PRIMAIRE+" =?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteDelete, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, player.getId_Player());			
			pst.executeUpdate();
		} 
		catch (SQLException e) {
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}

}
