package memoryBo;

public class ScorePlayerBo {

	
	public int score = 0;

	public ScorePlayerBo(PlayerBo pseudo, GameBo id_Game, int score) {
		super();
		this.score = score;
	}
	
	public void findPairCard() {
		++score;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
