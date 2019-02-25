package memoryBo;

public class PlayerBo 
{
	
	private static int counter = 0;
	private int id_Player;
	private int totalPoint = 0;			// a deplacer dans ParticipationBo
	private int chooseCard1;			// idem
	private int chooseCard2;			// idem
	private String pseudo;
	
	
	
	public PlayerBo(String player) 
	{
		super();
		this.pseudo = player;
		this.id_Player = counter;
		counter++;
	}
	
	public int getId_Player() 
	{
		return id_Player;
	}

	public void setId_Player(int id_Player) 
	{
		this.id_Player = id_Player;
	}

	public int getTotalPoint() 
	{
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) 
	{
		this.totalPoint = totalPoint;
	}
	
	public String getPseudo() 
	{
		return pseudo;
	}

	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}

	public int getChooseCard1() 
	{
		return chooseCard1;
	}

	public int getChooseCard2() 
	{
		return chooseCard2;
	}

	public void takePoint() 
	{
		++totalPoint;
	}
	
	public String getPlayer() 
	{
		return pseudo;
	}

	@Override
	public String toString() {
		return " [pseudo=" + pseudo + "]";
	}
	
}
