package memoryBo;

public class ParticipationBo {
	
	public int positionTour;	
	public int scorePlayer = 0;
	public boolean hand = false;

	public ParticipationBo(int id_Game, int id_Player, int scorePlayer, boolean hand, int positionTour ) {
		super();		
	}
	
	public int getPositionTour() {
		return positionTour;
	}

	public void setPositionTour(int positionTour) {
		this.positionTour = positionTour;
	}

	public boolean isHand() {
		return this.hand;
	}
	
	public void setHand(boolean hand) {
		this.hand = hand;
	}
	
	public int getScorePlayer() {
		return scorePlayer;
	}

	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}
	
	public void findPairCard() {
		scorePlayer++;
	}

	public int getId_Game() {
		return this.getId_Game();
	}

	public int getId_Player() {
		return this.getId_Player();
	}



}
