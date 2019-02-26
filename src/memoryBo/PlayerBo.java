package memoryBo;

import java.util.Scanner;

public class PlayerBo 
{
	
	private static int counter = 0;
	private int id_Player;
	private Scanner pseudo;
	
	
	
	public PlayerBo(Scanner scanPseudoPlayer) 
	{
		super();
		this.pseudo = scanPseudoPlayer;
		this.id_Player = counter;
		++ counter;
		
	}
	
	public int getId_Player() 
	{
		return id_Player;
	}

	public void setId_Player(int id_Player) 
	{
		this.id_Player = id_Player;
	}
	
	public Scanner getPseudo() 
	{
		return pseudo;
	}

	public void setPseudo(Scanner pseudo) 
	{
		this.pseudo = pseudo;
	}
	
	public Scanner getPlayer() 
	{
		return pseudo;
	}

	@Override
	public String toString() {
		return " [pseudo=" + pseudo + "]";
	}
	
}
