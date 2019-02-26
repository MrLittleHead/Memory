package memoryBo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameBo {
	
	public static int counter = 0;
	public int id_Game;
	public Scanner gameName;
	public String gameDate;
	
	public GameBo(Scanner scanNameGame) {
		super();
		this.id_Game = counter;
		this.gameName = scanNameGame;
		this.gameDate =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		++counter;
	}
	
	public Scanner getGameName() {
		return gameName;
	}

	public void setGameName(Scanner gameName) {
		this.gameName = gameName;
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
