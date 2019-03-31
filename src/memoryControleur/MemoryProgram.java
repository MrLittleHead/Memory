package memoryControleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import memoryBo.DistributionBo;
import memoryBo.GameBo;
import memoryBo.ParticipationBo;
import memoryBo.PlayerBo;

public class MemoryProgram {

	private PlayerBo currentPlayer = null;
	private GameBo currentGame = null;
	private Scanner scanGame = new Scanner(System.in);

	private int nbFindCard = 0;
	private int nbPlayer;
	private int nbCard;

	private List<PlayerBo> listPlayer = new ArrayList<PlayerBo>(nbPlayer);
	private List<ParticipationBo> listParticipation = new ArrayList<ParticipationBo>(nbPlayer);
	private static List<GameBo> listGame = new ArrayList<GameBo>();

	private void menu() {
		int choice;

		System.out.println("---------------Welcome to MemoryGame---------------");
		System.out.println("NEWGAME : press 1");
		System.out.println("LOAD    : press 2");
		System.out.println("LEAVE   : press 3");

		System.out.print("Saisissez votre choix : ");
		choice = scanGame.nextInt();

		while (!(choice == (int)choice)) // le choix ne peut pas etre autre chose qu'un int
		{
			System.out.print(" Caract�re invalide. Ressaissez votre choix : ");
			scanGame.next();
		}
		
		while  (choice > 3 || choice < 1) // le choix doit etre compris entre 1 et 3
		{
			System.out.print(" Choix invalide. Ressaissez votre choix : ");
			scanGame.next();
		}
		
		System.out.println(choice);

		if (choice == 1) {				//on demarre une nouvelle partie
			gameCreation();
			playerCreation();
			listeCardCreation();	
		}
		if (choice == 2) {				//on charge une partie
			System.out.println("choix 2");
		}

		if (choice == 3) {				//on quitte l'application
			System.out.println("choix 3");
		}

	}

	private void gameCreation() 
	{
		GameBo game = new GameBo("Test");

		listGame.add(game);
		currentGame = game;

	}

	private void playerCreation() 
	{
		System.out.print("Saisissez votre nombre de Joueurs : ");

		while (!scanGame.hasNextInt())
		{
			System.out.print(" Caractère inconnu, rechoisissez un nombre de joueurs  : ");
			scanGame.next();
		}

		nbPlayer = scanGame.nextInt();
		for (int i = 0; i < nbPlayer; i++) {
			System.out.print("Saisissez le nom du player " + i + " :");
			String name = scanGame.next();
			PlayerBo player = new PlayerBo(name);
			listPlayer.add(player);
			ParticipationBo part = new ParticipationBo(player.getId_Player(), currentGame.getId_Game(), 0, false, i);
			listParticipation.add(part);
		}
		currentPlayer = listPlayer.get(0);
		listParticipation.get(0).setHand(true);
	}

	private void listeCardCreation() {

		System.out.print("Saisissez votre nombre de cartes : ");
		while (!scanGame.hasNextInt())
		{
			System.out.print(" Caractère inconnu, rechoisissez votre nombre de cartes  : ");
			scanGame.next();
		}
		nbCard = scanGame.nextInt();
		while (nbCard % 2 == 1)
		{
			System.out.println("Nombre de Cartes invalide (impaire)!");
			System.out.print("Ressaisissez votre nombre de cartes : ");
			while (!scanGame.hasNextInt())
			{
				System.out.print(currentPlayer + "Caractère inconnu, rechoisissez votre nombre de cartes  : ");
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
			System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
			scanGame.next();
		}
		choosenCard = scanGame.nextInt() - 1;
		while (choosenCard >= nbCard || choosenCard < 0 || listCard.get(choosenCard).isVisible() ) 
		{
			System.out.print(player.getPseudo() + "Numero de carte invalide, rechoisissez de nouveau carte : ");
			while (!scanGame.hasNextInt()) 
			{
				System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez de nouveau une carte  : ");
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
			listParticipation.get(currentPlayer.getId_Player()).setHand(false);


			if ((currentPlayer.getId_Player() + 1) < nbPlayer) 
			{
				currentPlayer = listPlayer.get(currentPlayer.getId_Player() + 1);
				listParticipation.get(currentPlayer.getId_Player()).setHand(true);
			}
			else 
			{
				currentPlayer = listPlayer.get(0);
			listParticipation.get(currentPlayer.getId_Player()).setHand(true);
			}
		}
		else
		{
			listParticipation.get(currentPlayer.getId_Player()).findPairCard();
			System.out.println("Total Score " + currentPlayer + " : " + listParticipation.get(currentPlayer.getId_Player()).getScorePlayer());
			nbFindCard += 2;
		}
	}

	public MemoryProgram()
	{
		menu();

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
