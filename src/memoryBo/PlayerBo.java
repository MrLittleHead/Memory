package memoryBo;

public class PlayerBo {
	private int totalpoint = 0;
	private int chooseCard1;
	private int chooseCard2;
	private String joueur;
	
	public PlayerBo(String joueur) 
	{
		super();
		this.joueur = joueur;
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
	
	public String getJoueur() 
	{
		return joueur;
	}
	
	public void setJoueur(String joueur) 
	{
		this.joueur = joueur;
	}
}
