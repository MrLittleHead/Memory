package memoryBo;

import java.util.Date;

public class GameBo {
	
	private java.util.Date date  = new java.util.Date();
	public static int counter = 0;
	public int id_Game;
	public String gameName;
	public java.sql.Date gameDate;
	
	public GameBo(String gameName) {
		super();
		this.id_Game = counter;
		this.gameName = gameName;
		this.gameDate =  new java.sql.Date(date.getTime());
		++counter;
	}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getId_Game() {
		return id_Game;
	}

	public void setId_Game(int id_Game) {
		this.id_Game = id_Game;
	}

	public java.sql.Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(java.sql.Date gameDate) {
		this.gameDate = new java.sql.Date(date.getTime());
	}

	
}
