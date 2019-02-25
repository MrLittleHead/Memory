package memoryControleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import memoryBo.DistributionBo;
import memoryBo.PlayerBo;
import memoryBo.ScorePlayerBo;

public class MemoryProgram {

	private PlayerBo joueurActuel = null;
	private int nbPlayer;
	private List<PlayerBo> listPlayerBo = new ArrayList<PlayerBo>(nbPlayer);
	private int nbrCartesRetournees = 0;
	private int cartechoisie1;
	private int cartechoisie2;
	private int nbrCartes;



	public int saisirCarte(PlayerBo player, Scanner scan, DistributionBo p) 
	{
		System.out.print(player.getPseudo() + " Choisissez une carte : ");
		while (!scan.hasNextInt()) 
		{
			System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
			scan.next();
		}
		int carte =	scan.nextInt() - 1;
		while (carte >= nbrCartes || carte < 0 || p.get(carte).isVisible() ) 
		{
			System.out.print(player.getPseudo() + "Numero de carte invalide, rechoisissez une premiere carte : ");
			while (!scan.hasNextInt()) 
			{
				System.out.print(player.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
				scan.next();
			}
			carte =	scan.nextInt()-1;
		}
		return carte;
	}

	public void jouer(Scanner scan, DistributionBo p) 

	{
		cartechoisie1 = saisirCarte(joueurActuel, scan, p);

		joueurActuel.setChoisirCarte1(cartechoisie1);
		p.retournerCarte(cartechoisie1);
		System.out.println(p.toString());


		cartechoisie2 = saisirCarte(joueurActuel, scan, p);

		joueurActuel.setChoisirCarte2(cartechoisie2);
		p.retournerCarte(cartechoisie2);
		System.out.println(p.toString());
		if (! p.get(cartechoisie1).equals(p.get(cartechoisie2))) 
		{
			p.get(cartechoisie1).setVisible(false);
			p.get(cartechoisie2).setVisible(false);

			if (joueurActuel == listPlayerBo.get(0)) {
				joueurActuel = listPlayerBo.get(1);
			}
			else 
			{
				joueurActuel = listPlayerBo.get(0);
			}
		}
		else 
		{
			joueurActuel.gagnerdespoints();
			System.out.println("Totaldepoints " + joueurActuel + " " + joueurActuel.getTotaldepoints());
			nbrCartesRetournees += 2;
		}
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
		for (int i = 1; i < listPlayerBo.size(); i++) {
			System.out.print("Saisissez le nom du player" + i +":");
			Scanner scanPseudoPlayer = new Scanner(System.in);
			PlayerBo p = new PlayerBo("Joueur ");
			listPlayerBo.add(p);
		}
		
		joueurActuel = listPlayerBo.get(0);
		

		System.out.print("Saisissez votre nombre de cartes : ");
		Scanner scanNbCard = new Scanner(System.in);
		System.out.println("toujours rien lu");
		while (!scanNbCard.hasNextInt()) 
		{
			System.out.print(PlayerBo.getPseudo() + " Caractère inconnu, rechoisissez une premiere carte  : ");
			scanNbCard.next();
		}
		nbrCartes =	scanNbCard.nextInt(); 
		while (nbrCartes % 2 == 1) 
		{
			System.out.println("Nombre de Cartes invalide (impaire)!");
			System.out.print("Ressaisissez votre nombre de cartes : ");
			while (!scanNbCard.hasNextInt()) 
			{
				System.out.print(pseudo.getPseudo() + "Caractère inconnu, rechoisissez une premiere carte  : ");
				scanNbCard.next();
			}
			nbrCartes =	scanNbCard.nextInt();
		}

		System.out.println("            C'est parti");
		DistributionBo p = new DistributionBo(nbrCartes);
		System.out.println(p.toString());
		//update
		while (nbrCartesRetournees != nbrCartes) 
		{
			sauvegarde();
			this.jouer(scanNbCard, p);
		}
		/*if (joueur1.getTotaldepoints() > joueur2.getTotaldepoints())
		{
			System.out.println("Joueur 1 GAGNE !!!!!!!! ");
		}
		else if (joueur2.getTotaldepoints() > joueur1.getTotaldepoints())
		{
			System.out.println("Joueur 2 GAGNE !!!!!!!! ");
		}*/
		else 
		{
			System.out.println("Match NUL ! ");
		}
		System.out.println("Player "+ PlayerBo.getPseudo() + " à : " + ScorePlayerBo.getScore());

		scanNbCard.close();
	}

	private void sauvegarde() {


	}

}
