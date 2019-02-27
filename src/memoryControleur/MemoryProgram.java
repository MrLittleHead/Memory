package memoryControleur;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import memoryBo.DistributionBo;
import memoryBo.GameBo;
import memoryBo.PlayerBo;
import memoryBo.ScorePlayerBo;

public class MemoryProgram {

	private PlayerBo currentPlayer = null;
	private GameBo currentGame = null;
	private ScorePlayerBo currentScore;
	private Scanner scanGame = new Scanner(System.in);
	
	private int nbFindCard = 0;
	private int nbPlayer;
	private int nbCard;
	
	private List<PlayerBo> listPlayerBo = new ArrayList<PlayerBo>(nbPlayer);
	private List<ScorePlayerBo> listScore = new ArrayList<ScorePlayerBo>(nbPlayer);
	private static List<GameBo> listGame = new ArrayList<GameBo>();
	

	//je suis pas mal embrouiller avec les scanners j'ai du transformer mes string en scanner dans mes constructeur :/
	//le code casse lors de la creation des joueurs : 
	//mes intentions =  - creer une liste de joueurs 
					//	- creer une liste de score associ� (un tableau serai sans doute mieux) :s
					//	- on passe au joueur et score suivant de la liste en cas d'echec findCard
					// il manque les conditions de victoire et je dois refaire le CRUD + BD (Anglais oblige)
	//a terme je veux supprimer les scan et faire une IHM

	private void gameCreation() {

		System.out.println("entrez un nom de partie :");
		scanGame.next();

		GameBo game = new GameBo(scanGame);

		listGame.add(game);
		currentGame = game;
	}

	private void playerCreation() {

		System.out.print("Saisissez votre nombre de Joueurs : ");

		while (!scanGame.hasNextInt())
		{
			System.out.print(" Caract�re inconnu, rechoisissez un nombre de joueurs  : ");
			scanGame.next();
		}

		nbPlayer = scanGame.nextInt();
		for (int i = 0; i < nbPlayer; i++) {
			System.out.print("Saisissez le nom du player " + i + " :");
			String name = scanGame.next();
			PlayerBo player = new PlayerBo(name);
			listPlayerBo.add(player);
			ScorePlayerBo score = new ScorePlayerBo(player, currentGame.getId_Game(), 0);
			listScore.add(score);
		}
		currentPlayer = listPlayerBo.get(0);
		currentScore  = listScore.get(0);
	}

	private void listeCardCreation() {

		System.out.print("Saisissez votre nombre de cartes : ");
		while (!scanGame.hasNextInt())
		{
			System.out.print(" Caract�re inconnu, rechoisissez votre nombre de cartes  : ");
			scanGame.next();
		}
		nbCard = scanGame.nextInt();
		while (nbCard % 2 == 1)
		{
			System.out.println("Nombre de Cartes invalide (impaire)!");
			System.out.print("Ressaisissez votre nombre de cartes : ");
			while (!scanGame.hasNextInt())
			{
				System.out.print(currentPlayer + "Caract�re inconnu, rechoisissez votre nombre de cartes  : ");
				scanGame.next();
			}
			nbCard = scanGame.nextInt();
		}
	}
	
	private int ChooseCard(PlayerBo player, DistributionBo listCard) 
	{
		int choosenCard;
		
		System.out.print(player.getPseudo() + " Choisissez une carte : ");
		while (!scanGame.hasNextInt()) 
		{
			System.out.print(player.getPseudo() + " Caract�re inconnu, rechoisissez une premiere carte  : ");
			scanGame.next();
		}
		choosenCard = scanGame.nextInt() - 1;
		while (choosenCard >= nbCard || choosenCard < 0 || listCard.get(choosenCard).isVisible() ) 
		{
			System.out.print(player.getPseudo() + "Numero de carte invalide, rechoisissez de nouveau carte : ");
			while (!scanGame.hasNextInt()) 
			{
				System.out.print(player.getPseudo() + " Caract�re inconnu, rechoisissez de nouveau une carte  : ");
				scanGame.next();
			}
			choosenCard = scanGame.nextInt() - 1; // -1 car pour plus de facilit� pour choisir la carte 0 on designe la carte 1
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
		if (!listCard.get(choosenCard).equals(listCard.get(choosenCard2))) 
		{
			listCard.get(choosenCard).setVisible(false);
			listCard.get(choosenCard2).setVisible(false);
			
			if ((currentPlayer.getId_Player() + 1) < nbPlayer)
				currentPlayer = listPlayerBo.get(currentPlayer.getId_Player() + 1);
			else
				currentPlayer = listPlayerBo.get(0);
			currentScore = listScore.get(currentPlayer.getId_Player()); //idem pour le score
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
		scanGame.close();
	}
}
