package memoryBo;

public class GameBo {
	
	private java.util.Date date  = new java.util.Date();
	private int id_Game;
	private String gameName;
	private java.sql.Date gameDate;
	
	
	public GameBo() {
		super();
	}
	
	public GameBo(String gameName) {
		super();
		this.gameName = gameName;
		this.gameDate =  new java.sql.Date(date.getTime());
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
