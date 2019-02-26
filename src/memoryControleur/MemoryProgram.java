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
	private int nbPlayer;
	private List<PlayerBo> listPlayerBo = new ArrayList<PlayerBo>(nbPlayer);
	public static List<GameBo> listGame = new ArrayList<GameBo>();
	private int nbChoosenCard = 0;
	private int choosenCard;
	private int choosenCard2;
	private int nbCard;



	public int ChooseCard(PlayerBo player, Scanner scan, DistributionBo listCard) 
	{
		System.out.print(player.getPseudo() + " Choisissez une carte : ");
		while (!scan.hasNextInt()) 
		{
			System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
			scan.next();
		}
		int card =	scan.nextInt() - 1;
		while (card >= nbCard || card < 0 || listCard.get(card).isVisible() ) 
		{
			System.out.print(player.getPseudo() + "Numero de carte invalide, rechoisissez une premiere carte : ");
			while (!scan.hasNextInt()) 
			{
				System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
				scan.next();
			}
			card =	scan.nextInt() - 1; // -1 car pour plus de facilité pour choisir la carte 0 on designe la carte 1
		}
		return card;
	}

	public void play(Scanner scan, DistributionBo listCard) 
	{
		currentPlayer.ChooseCard(choosenCard);
		listCard.returnChoosenCard(choosenCard);
		currentPlayer.ChoosenCard(choosenCard2);
		listCard.returnChoosenCard(choosenCard2);
		System.out.println(listCard.toString());
		
		if (! listCard.get(choosenCard).equals(listCard.get(choosenCard2))) 
		{
			listCard.get(choosenCard).setVisible(false);
			listCard.get(choosenCard2).setVisible(false);

			currentPlayer = listPlayerBo.get(+1); // a Tester
			if (currentPlayer == listPlayerBo.get(nbPlayer + 1)) {
				currentPlayer = listPlayerBo.get(1);		
			}
		}
		else 
		{
			ScorePlayerBo.findPairCard(currentPlayer, currentGame, ScorePlayerBo.getScore(currentPlayer, currentGame);
			System.out.println("Total Score : " + currentPlayer + " " + ScorePlayerBo.getScore(currentPlayer, currentGame));
			nbChoosenCard += 2;
		}
	}

	public GameBo creationGame() {
		
		System.out.println("entrez un nom de partie :");
		Scanner scanNameGame = new Scanner(System.in);
		scanNameGame.next();
		GameBo game = new GameBo(scanNameGame);
		listGame.add(game);
		currentGame = game;
		return currentGame;
	}
	
	
	public MemoryProgram() 
	{

		System.out.println("---------------Memory---------------");


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
			System.out.print("Saisissez le nom du player" + i +":");
			Scanner scanPseudoPlayer = new Scanner(System.in);
			PlayerBo p = new PlayerBo("");
			listPlayerBo.add(p);
		}
		
		currentPlayer = listPlayerBo.get(0);
		

		System.out.print("Saisissez votre nombre de cartes : ");
		Scanner scanNbCard = new Scanner(System.in);
		System.out.println("toujours rien lu");
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
				System.out.print(currentPlayer + "Caractère inconnu, rechoisissez une premiere carte  : ");
				scanNbCard.next();
			}
			nbCard =	scanNbCard.nextInt();
		}

		System.out.println("            C'est parti");
		DistributionBo p = new DistributionBo(nbCard);
		System.out.println(p.toString());
		//update
		while (nbChoosenCard != nbCard) 
		{
			
			this.play(scanNbCard, p);
		}
		if (joueur1.getTotaldepoints() > joueur2.getTotaldepoints())
		{
			System.out.println("Joueur 1 GAGNE !!!!!!!! ");
		}
	
		else 
		{
			System.out.println("Match NUL ! ");
		}
		scanNbCard.close();
	}

}
