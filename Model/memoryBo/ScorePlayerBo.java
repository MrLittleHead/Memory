package memoryBo;

public class ScorePlayerBo {

	
	public int score = 0;

	public ScorePlayerBo(PlayerBo id_Player, GameBo id_Gale, int score) {
		super();
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
