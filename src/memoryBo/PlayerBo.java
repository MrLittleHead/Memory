package memoryBo;

public class PlayerBo {
	private int totalpoint = 0;
	private int chooseCard1;
	private int chooseCard2;
	private String player;
	private int id_Player;
	private int counter = 0;
	
	public PlayerBo(String player) 
	{
		super();
		this.player = player;
		this.setId_Player(counter);
		counter++;
	}
	
	public void takePoint() 
	{
		++totalpoint;
	}
	
	public int getTotaldepoints() 
	{
		return totalpoint;
	}
	
	public int getChoisirCarte1() 
	{
		return chooseCard1;
	}
	
	public int getCarteChoisie2() 
	{
		return chooseCard2;
	}
	
	public void setChoisirCarte2(int carteChoisie2) 
	{
		this.chooseCard2 = carteChoisie2;
	}
	
	public void setChoisirCarte1(int numeroCarte) 
	{
		this.chooseCard1 = numeroCarte;
	}
	
	public String getPlayer() 
	{
		return player;
	}
	
	public void setJoueur(String joueur) 
	{
		this.player = joueur;
	}

	public void setId_player(int int1) {
		// TODO Auto-generated method stub
		
	}

	public int getId_Player() {
		return id_Player;
	}

	public void setId_Player(int id_Player) {
		this.id_Player = id_Player;
	}
}
