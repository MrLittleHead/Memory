package memoryBo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DistributionBo {
	
private List<CardBo> listCard = new ArrayList<CardBo>();
	
	public DistributionBo(int nb) 
	{ 
		int symb = 1;
		for (int i = 0; i < (nb / 2); i++) 
		{
			CardBo c1 = new CardBo(symb);
			CardBo c2 = new CardBo(symb);
			listCard.add(c1);
			listCard.add(c2);
			symb++;
			if (symb == (SymboleBo.NBR_SYMBOLES-1)) 
			{
				symb = 1;
			}
		}
		Collections.shuffle(listCard);
	}
	
	public int size() {
		return this.listCard.size();
	}
	
	public boolean add(CardBo c) {
		return this.listCard.add(c);
	}

	public CardBo get(int i) {
		return this.listCard.get(i);
	}
	
	public void retournerCarte(int numeroCarte) {
		this.get(numeroCarte).setVisible(true);
	}

	@Override
	public String toString() 
	{
		int i = 1;
		String rep = "";
		for (Iterator<CardBo> iterator = listCard.iterator(); iterator.hasNext();) {
			CardBo carte = (CardBo) iterator.next();

			if (i < 10)	{
				rep += i + " ";
			}
			else {
				rep += i;
			}

			rep += carte.toString();
			if (i % 5 == 0) {
				rep += "\n";
			}
			else {
				rep += " ";
			}
			++i;
		}
		return  rep;
	}

}
