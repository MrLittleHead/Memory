package memoryControleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import memoryBo.DistributionBo;
import memoryBo.GameBo;
import memoryBo.PlayerBo;
import memoryBo.ScorePlayerBo;

public class MemoryProgram {

	private PlayerBo currentPlayer = null;
	private GameBo currentGame = null;
	private ScorePlayerBo currentScore ;
	
	private int nbFindCard = 0;
	private int nbPlayer;
	private int nbCard;
	
	private List<PlayerBo> listPlayerBo = new ArrayList<PlayerBo>(nbPlayer);
	private List<ScorePlayerBo> listScore = new ArrayList<ScorePlayerBo>(nbPlayer);
	private static List<GameBo> listGame = new ArrayList<GameBo>();
	

	//je suis pas mal embrouiller avec les scanners j'ai du transformer mes string en scanner dans mes constructeur :/
	//le code casse lors de la creation des joueurs : 
	//mes intentions =  - creer une liste de joueurs 
					//	- creer une liste de score associé (un tableau serai sans doute mieux) :s
					//	- on passe au joueur et score suivant de la liste en cas d'echec findCard
	
	//a terme je veux supprimer les scan et faire une IHM

	private void gameCreation() {

		System.out.println("entrez un nom de partie :");
		Scanner scanNameGame = new Scanner(System.in);
		scanNameGame.next();

		GameBo game = new GameBo(scanNameGame);

		listGame.add(game);
		currentGame = game;
	}

	private void playerCreation() {

		System.out.print("Saisissez votre nombre de Joueurs : ");
		Scanner scanNbPlayer = new Scanner(System.in);
		System.out.println("toujours rien lu");

		while (!scanNbPlayer.hasNextInt()) 
		{
			System.out.print( " Caractère inconnu, rechoisissez un nombre de joueurs  : ");
			scanNbPlayer.next();
		}

		nbPlayer =	scanNbPlayer.nextInt();
		scanNbPlayer.close();

		for (int i = 0; i < listPlayerBo.size(); i++) {
			System.out.print("Saisissez le nom du player" + i +" :");
			Scanner scanPseudoPlayer = new Scanner(System.in);
			PlayerBo pseudo = new PlayerBo(scanPseudoPlayer);
			listPlayerBo.add(pseudo);
			ScorePlayerBo score = new ScorePlayerBo(pseudo, currentGame.getId_Game(), 0);
			listScore.add(score);
		}
		currentPlayer = listPlayerBo.get(0);
		currentScore  = listScore.get(0);
	}

	private int listeCardCreation() {

		System.out.print("Saisissez votre nombre de cartes : ");
		Scanner scanNbCard = new Scanner(System.in);
		while (!scanNbCard.hasNextInt()) 
		{
			System.out.print( " Caractère inconnu, rechoisissez une premiere carte  : ");
			scanNbCard.next();
		}
		nbCard =	scanNbCard.nextInt(); 
		while (nbCard % 2 == 1) 
		{
			System.out.println("Nombre de Cartes invalide (impaire)!");
			System.out.print("Ressaisissez votre nombre de cartes : ");
			while (!scanNbCard.hasNextInt()) 
			{
				System.out.print(currentPlayer + "Caractère inconnu, rechoisissez votre nombre de cartes  : ");
				scanNbCard.next();
			}
			nbCard =	scanNbCard.nextInt();

		}
		return nbCard;
	}
	private int ChooseCard(PlayerBo player, DistributionBo listCard) 
	{
		int choosenCard;
		
		System.out.print(player.getPseudo() + " Choisissez une carte : ");
		Scanner scanChooseCard = new Scanner(System.in);
		while (!scanChooseCard.hasNextInt()) 
		{
			System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
			scanChooseCard.next();
		}
		choosenCard =	scanChooseCard.nextInt() - 1;
		while (choosenCard >= nbCard || choosenCard < 0 || listCard.get(choosenCard).isVisible() ) 
		{
			System.out.print(player.getPseudo() + "Numero de carte invalide, rechoisissez de nouveau carte : ");
			while (!scanChooseCard.hasNextInt()) 
			{
				System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez de nouveau une carte  : ");
				scanChooseCard.next();
			}
			choosenCard =	scanChooseCard.nextInt() - 1; // -1 car pour plus de facilité pour choisir la carte 0 on designe la carte 1
		}
		return choosenCard;
	}

	public void play(int nbCard, DistributionBo listCard) 
	{
		int choosenCard = 0;
		int choosenCard2 = 0;
		
		for (int i = 0; i <= 1; i++) {
			if (i == 0) {
				choosenCard = ChooseCard(currentPlayer, listCard);
				listCard.returnChoosenCard(choosenCard);
				System.out.println(listCard.toString());
			}
			else {
				choosenCard2 = ChooseCard(currentPlayer, listCard);
				listCard.returnChoosenCard(choosenCard2);
				System.out.println(listCard.toString());
			}
		}

		if (! listCard.get(choosenCard).equals(listCard.get(choosenCard2))) 
		{
			listCard.get(choosenCard).setVisible(false);
			listCard.get(choosenCard2).setVisible(false);

			currentPlayer = listPlayerBo.get(+1);	// a Tester vraiment pas sur de moi j'essaye de faire passer au joueur next
			currentScore = listScore.get(+1);		//idem pour le score
			
			if (currentPlayer == listPlayerBo.get(nbPlayer + 1)) {
				currentPlayer = listPlayerBo.get(1);		
			}
		}
		else 
		{
			currentScore.findPairCard();
			System.out.println("Total Score " + currentPlayer + " : " + currentScore.getScore());
			nbFindCard += 2;
		}
	}




	public MemoryProgram() 
	{
		System.out.println("---------------Memory---------------");

		gameCreation();
		playerCreation();
		listeCardCreation();

		System.out.println("            C'est parti");
		DistributionBo p = new DistributionBo(nbCard);
		System.out.println(p.toString());

		while (nbFindCard != nbCard) 
		{
			this.play(nbCard, p); // tant qu'il y a des cartes on joue
		}
		/*if () // condition victoire
		{
			System.out.println("Joueur 1 GAGNE !!!!!!!! ");
		}

		else 
		{
			System.out.println("Match NUL ! ");
		}*/

	}

}
