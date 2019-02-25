package memoryBo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GameBo {
	
	public static int counter = 0;
	public int id_Game;
	private List<PlayerBo> listPlayer = new ArrayList<PlayerBo>();
	public String gameName;
	public String gameDate;
	
	
	
	public GameBo(String gameName) {
		super();
		this.id_Game = counter;
		this.gameName = gameName;
		this.gameDate =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		++counter;
	}
	
	public void creationListPlayer(int nbPlayer) {
		 nbPlayer = listPlayer.size() + 1;
	}

	public int getId_Game() {
		return id_Game;
	}

	public void setId_Game(int id_Game) {
		this.id_Game = id_Game;
	}

	public String getGameDate() {
		return gameDate;
	}

	public void setGameDate(String gameDate) {
		this.gameDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

	
}
