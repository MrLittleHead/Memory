package memoryBo;

public class ParticipationBo {
	
	private int game;
	private int player; 
	private int positionTour;	
	private int scorePlayer = 0;
	private boolean hand = false;

	public ParticipationBo(int game, int player, int scorePlayer, boolean hand, int positionTour ) 
	{
		super();
		this.game = game;
		this.player = player;
		this.hand = hand;
		this.scorePlayer = scorePlayer;
		this.positionTour = positionTour;	
	}
	

	public GameBo getGame() {
		return game;
	}


	public void setGame(GameBo game) {
		this.game = game;
	}


	public PlayerBo getPlayer() {
		return player;
	}


	public void setPlayer(PlayerBo player) {
		this.player = player;
	}


	public int getPositionTour() {
		return positionTour;
	}


	public void setPositionTour(int positionTour) {
		this.positionTour = positionTour;
	}


	public int getScorePlayer() {
		return scorePlayer;
	}


	public void setScorePlayer(int scorePlayer) {
		this.scorePlayer = scorePlayer;
	}


	public boolean isHand() {
		return hand;
	}


	public void setHand(boolean hand) {
		this.hand = hand;
	}


	public void findPairCard() {
		scorePlayer++;
	}





}
