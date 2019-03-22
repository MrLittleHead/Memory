package memoryBo;

public class ParticipationBo {
	
	public int positionTour;
	public boolean hand = true;

	

	public ParticipationBo(GameBo id_Game, PlayerBo id_Player, ScorePlayerBo score, boolean hand, int positionTour ) {
		super();
		
	}

	public int getPositionTour() {
		return positionTour;
	}

	public void setPositionTour(int positionTour) {
		this.positionTour = positionTour;
	}

	public boolean isMain() {
		return hand;
	}

	public void setHand(boolean hand) {
		this.hand = hand;
	}

}
