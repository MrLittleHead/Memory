package memoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import memoryBo.CardBo;
import memoryBo.GameBo;
import memoryBo.ParticipationBo;
import memoryBo.PlayerBo;


public class ParticipationDAO extends DAO<ParticipationBo> {

	private static final String TABLE = "Participation";
	private static final String CLE_PRIMAIRE = "id_Game, id_Player";

	private static ParticipationDAO instance = null; //cr�ation d'un singleton


	public static ParticipationDAO getInstance() 
	{         
		if(instance == null);         
		return instance = new ParticipationDAO();     
	}

	@Override
	public boolean create(ParticipationBo part) 
	{
		boolean succes = true;
		try 
		{
			String requete = ("INSERT INTO "+TABLE+" (id_Game, id_Player, scorePlayer, hand, positionTour) VALUES (?, ?, ?, ?, ?)");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, part.getGame().getId_Game()); 
			pst.setInt(2, part.getPlayer().getId_Player());
			pst.setInt(3, part.getScorePlayer());
			pst.setBoolean(4, part.isHand());
			pst.setInt(5, part.getPositionTour());

			pst.executeUpdate();					
		} 
		catch (SQLException e) 
		{
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public ParticipationBo read(int id_game) 
	{
		List<ParticipationBo> listParticipation = null;
		ParticipationBo part = null;
		try 
		{
			String requete = "SELECT COUNT(*) AS nb FROM " + TABLE + " WHERE id_Game = " + id_game;
			PreparedStatement pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = pst.executeQuery();
			int nbPlayers = 0;
			if (rs.next() != false)
			{
				 nbPlayers= rs.getInt("nb");
				 listParticipation = new ArrayList<ParticipationBo>(nbPlayers);				
			}
			else
			{
				//TODO erreur pas de joueurs
			}
			requete = "SELECT id_Player, scorePlayer, hand, playerPosition WHERE id_Game = " + id_game;
			pst = Connection.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			rs = pst.executeQuery();
			if (rs.next() != false)
			{
				do
				{					
					part = new ParticipationBo(id_game, rs.getInt("id_Player"), rs.getInt("scorePlayer"), rs.getBoolean("hand"), rs.getInt("playerPosition"));
					listParticipation.add(part);
				} while (rs.next() != false);				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return part;
	}

	@Override
	public boolean update(ParticipationBo part) 
	{
		boolean succes = true;
		try 
		{
			String requeteUpdate = ("update "+ TABLE +" set scorePlayer = ?,  hand = ?, positionTour = ?  where id_Game = ? and id_Player = ?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteUpdate, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, part.getId_Game());		// transformation de l'enum str en enum int (enum.ordinal?)
			pst.setInt(2, part.getId_Player());
			pst.setInt(3, part.getScorePlayer());
			pst.setBoolean(4, part.isHand());
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
	public boolean delete(ParticipationBo part) 
	{
		boolean succes = true;
		try 
		{
			String requeteDelete = (" delete from "+ TABLE +" where id_Game = ? and id_Player = ?");
			PreparedStatement pst = Connection.getInstance().prepareStatement(requeteDelete, Statement.RETURN_GENERATED_KEYS);		
			pst.setInt(1, part.getId_Game());
			pst.setInt(2, part.getId_Player());
			pst.executeUpdate();
		} 
		catch (SQLException e) 
		{
			succes =false;
			e.printStackTrace();
		}
		return succes;
	}


}
