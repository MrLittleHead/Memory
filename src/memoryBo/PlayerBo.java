package memoryBo;

public class PlayerBo 
{
	

	private int id_Player;
	private String pseudo;
	
	
	public PlayerBo() {
		super();
	}
	
	public PlayerBo(String playerPseudo) 
	{
		super();
		this.pseudo = playerPseudo;		
	}
	
	public int getId_Player() 
	{
		return id_Player;
	}

	public void setId_Player(int id_Player) 
	{
		this.id_Player = id_Player;
	}
	
	public String getPseudo() 
	{
		return pseudo;
	}

	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}
	
	public String getPlayer() 
	{
		return pseudo;
	}

	@Override
	public String toString() {
		return " [" + pseudo + "]";
	}
	
}
